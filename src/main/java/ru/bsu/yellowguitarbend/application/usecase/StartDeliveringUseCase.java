package ru.bsu.yellowguitarbend.application.usecase;

import lombok.extern.slf4j.Slf4j;
import ru.bsu.yellowguitarbend.application.external.direction.from.dto.request.CheckInstrumentsResultRequest;
import ru.bsu.yellowguitarbend.application.external.direction.from.dto.request.CheckedInstrumentDto;
import ru.bsu.yellowguitarbend.application.persistance.InstrumentRepository;
import ru.bsu.yellowguitarbend.application.persistance.OrderRepository;
import ru.bsu.yellowguitarbend.domain.agregate.Order;
import ru.bsu.yellowguitarbend.domain.entity.Instrument;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class StartDeliveringUseCase {
  private final OrderRepository orderRepository;
  private final InstrumentRepository instrumentRepository;

  public StartDeliveringUseCase(OrderRepository orderRepository,
                                InstrumentRepository instrumentRepository) {
    this.orderRepository = orderRepository;
    this.instrumentRepository = instrumentRepository;
  }

  public void execute(CheckInstrumentsResultRequest request) {
    Order order = orderRepository.findByUuid(request.getOrderUuid());

    List<Instrument> instrumentsFromOrder = instrumentRepository.findAllBySeriesNumberIn(
      request.getCheckedInstruments().stream().map(CheckedInstrumentDto::getSeriesNumber).collect(Collectors.toList())
    );

    if (resolveInstrumentsAvailability(request, instrumentsFromOrder)) {
      order.startDelivering();
      log.info("Order with UUID {} started to delivery", order.getUuid());
    } else {
      order.failed();
      log.info("Order with UUID {} has not available instruments, failed to start delivery", order.getUuid());
    }

    log.debug("Instruments from order - {}", instrumentsFromOrder);

    orderRepository.save(order);
    instrumentRepository.saveAll(instrumentsFromOrder);
  }

  private boolean resolveInstrumentsAvailability(CheckInstrumentsResultRequest request,
                                                 List<Instrument> instrumentsFromOrder) {
    boolean result = true;

    for (Instrument instrument : instrumentsFromOrder) {
      Boolean instrumentAvailability = request.getCheckedInstruments()
        .stream()
        .filter(checkedInstrument -> instrument.getSeriesNumber().equalsIgnoreCase(checkedInstrument.getSeriesNumber()))
        .findFirst()
        .get()
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
}
