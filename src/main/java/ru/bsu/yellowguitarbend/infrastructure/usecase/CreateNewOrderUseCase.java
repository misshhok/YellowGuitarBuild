package ru.bsu.yellowguitarbend.infrastructure.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request.CreateNewOrderRequest;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request.OrderItemDto;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.to.ToStorageInterface;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.to.request.CheckInstrumentsRequest;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.to.request.InstrumentDto;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.InstrumentEntity;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.OrderEntity;
import ru.bsu.yellowguitarbend.infrastructure.persistance.repository.InstrumentJpaRepository;
import ru.bsu.yellowguitarbend.infrastructure.persistance.repository.OrderJpaRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateNewOrderUseCase {
  private final OrderJpaRepository orderRepository;
  private final InstrumentJpaRepository instrumentRepository;
  private final ToStorageInterface storageExternalSystem;

  public void execute(CreateNewOrderRequest request) {
    List<InstrumentEntity> instrumentsToOrder = new ArrayList<>();
    // Собираем инструменты из хранилища данных
    for (OrderItemDto item : request.getItems()) {
      List<InstrumentEntity> instrumentsByNameAndQuantity = getInstrumentsByNameAndQuantity(
        item.getInstrumentName(), item.getQuantity()
      ); // Берем столько инструментов по названию сколько укзано в заказе
      // (у каждого из инструментов будет разный серйник)
      instrumentsToOrder.addAll(instrumentsByNameAndQuantity);
      // добавляем в итоговую коллекцию
    }

    // обогащаем заказ данными
    OrderEntity newOrder = OrderEntity.createNew(request.getAddress(), request.getCustomerName(), instrumentsToOrder);

    // отправляем запрос во внешнюю систему для проверки наличии и готовности инструментов в к доставке получателю
    storageExternalSystem.checkInstruments(getStorageRequest(newOrder));

    //сохраняем заказ
    newOrder = orderRepository.save(newOrder);

    // сохраняем состояние инструменов после сборки заказа
    instrumentRepository.saveAll(newOrder.getInstruments());
    log.info("Oder with UUID {} successfully created", newOrder.getUuid());
  }

  private List<InstrumentEntity> getInstrumentsByNameAndQuantity(String instrumentName, int quantity) {


    if (quantity == 1) {
      // если данный инструмент в заказе один то просто находим первый попавшийся
      return Collections.singletonList(instrumentRepository.findFirstByCommercialName(instrumentName)
        .orElseThrow(() -> new IllegalArgumentException("Instrument not found")
        ));
    } else {
      // если несколько то берем все инструменты согласно лимиту ввиде quantity
      PageRequest pageRequest = PageRequest.of(0, quantity);
      return instrumentRepository.findAllByCommercialName(instrumentName, pageRequest).getContent();
    }
  }

  private CheckInstrumentsRequest getStorageRequest(OrderEntity order) {
    CheckInstrumentsRequest request = new CheckInstrumentsRequest();

    List<InstrumentDto> instrumentsToCheck = order.getInstruments().stream().map(instrument -> {
      return new InstrumentDto(instrument.getSeriesNumber());
    }).toList();

    request.setOrderId(order.getUuid());
    request.setInstrumentsToCheck(instrumentsToCheck);

    return request;
  }
}
