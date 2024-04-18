package ru.bsu.yellowguitarbend.infrastructure.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request.CheckInstrumentsResultRequest;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request.CheckedInstrumentDto;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.to.ToDeliveryInterface;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.to.request.InstrumentDto;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.to.request.StartDeliveryRequest;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.InstrumentEntity;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.OrderEntity;
import ru.bsu.yellowguitarbend.infrastructure.persistance.repository.InstrumentJpaRepository;
import ru.bsu.yellowguitarbend.infrastructure.persistance.repository.OrderJpaRepository;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StartDeliveryUseCase {
  private final OrderJpaRepository orderRepository;
  private final InstrumentJpaRepository instrumentRepository;
  private final ToDeliveryInterface toDeliveryInterface;


  public void execute(CheckInstrumentsResultRequest request) {
    OrderEntity order = orderRepository.findByUuid(request.getOrderUuid()).orElseThrow(
      () -> new IllegalArgumentException("Order not found")
    );

    final List<InstrumentEntity> instrumentsFromOrder = getInstrumentEntitiesFromRequest(request);

    if (resolveInstrumentsAvailability(request, instrumentsFromOrder)) {
      log.info("Order with UUID {} started to delivery", order.getUuid());
      order.startDelivering();
      toDeliveryInterface.startDelivery(getDeliveryRequest(order));
    } else {
      log.info("Order with UUID {} has not available instruments, failed to start delivery", order.getUuid());
      order.failed();
    }

    log.debug("Instruments from order - {}", instrumentsFromOrder);

    orderRepository.save(order);
    instrumentRepository.saveAll(instrumentsFromOrder);
  }

  private List<InstrumentEntity> getInstrumentEntitiesFromRequest(CheckInstrumentsResultRequest request) {
    return instrumentRepository.findAllBySeriesNumberIn(
      request.getCheckedInstruments().stream().map(CheckedInstrumentDto::getSeriesNumber).collect(Collectors.toList())
    );
  }

  private boolean resolveInstrumentsAvailability(CheckInstrumentsResultRequest request,
                                                 List<InstrumentEntity> instrumentsFromOrder) {
    boolean result = true;

    for (InstrumentEntity instrument : instrumentsFromOrder) {
      Boolean instrumentAvailability = request.getCheckedInstruments()
        .stream()
        .filter(checkedInstrument -> instrument.getSeriesNumber().equalsIgnoreCase(checkedInstrument.getSeriesNumber()))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Instrument not found"))
        .getReadyForDelivery();
      if (instrumentAvailability) {
        instrument.readyForDelivery();
      } else {
        instrument.notAvailable();
        result = false;
      }
    }
    return result;
  }

  private StartDeliveryRequest getDeliveryRequest(OrderEntity order) {
    StartDeliveryRequest request = new StartDeliveryRequest();
    request.setOrderUuid(order.getUuid());
    request.setInstrumentsToDelivery(
      order.getInstruments()
        .stream()
        .map(this::mapInstrumentToDto)
        .collect(Collectors.toList()))
    ;
    return request;
  }

  private InstrumentDto mapInstrumentToDto(InstrumentEntity entity) {
    return new InstrumentDto(entity.getSeriesNumber());
  }
}
