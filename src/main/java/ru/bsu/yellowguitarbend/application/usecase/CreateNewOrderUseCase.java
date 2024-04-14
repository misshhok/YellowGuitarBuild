package ru.bsu.yellowguitarbend.application.usecase;

import lombok.extern.slf4j.Slf4j;
import ru.bsu.yellowguitarbend.application.external.direction.from.dto.request.CreateNewOrderRequest;
import ru.bsu.yellowguitarbend.application.external.direction.from.dto.request.OrderItemDto;
import ru.bsu.yellowguitarbend.application.external.direction.to.ToStorageInterface;
import ru.bsu.yellowguitarbend.application.external.direction.to.dto.request.CheckInstrumentsRequest;
import ru.bsu.yellowguitarbend.application.external.direction.to.dto.request.InstrumentDto;
import ru.bsu.yellowguitarbend.application.persistance.InstrumentRepository;
import ru.bsu.yellowguitarbend.application.persistance.OrderRepository;
import ru.bsu.yellowguitarbend.domain.agregate.Order;
import ru.bsu.yellowguitarbend.domain.entity.Instrument;
import ru.bsu.yellowguitarbend.domain.value.InstrumentName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class CreateNewOrderUseCase {
  private final OrderRepository orderRepository;
  private final InstrumentRepository instrumentRepository;
  private final ToStorageInterface storageExternalSystem;


  public CreateNewOrderUseCase(OrderRepository orderRepository, InstrumentRepository instrumentRepository, ToStorageInterface storageExternalSystem) {
    this.orderRepository = orderRepository;
    this.instrumentRepository = instrumentRepository;
    this.storageExternalSystem = storageExternalSystem;
  }


  public void execute(CreateNewOrderRequest request) {
    List<Instrument> instrumentsToOrder = new ArrayList<>();
    // Собираем инструменты из хранилища данных
    for (OrderItemDto item : request.getItems()) {
      List<Instrument> instrumentsByNameAndQuantity = getInstrumentsByNameAndQuantity(
        item.getInstrumentName(), item.getQuantity()
      ); // Берем столько инструментов по названию сколько укзано в заказе
      // (у каждого из инструментов будет разный серйник)
      instrumentsToOrder.addAll(instrumentsByNameAndQuantity);
      // добавляем в итоговую коллекцию
    }

    // обогащаем заказ данными
    Order newOrder = Order.createNew(request.getAddress(), request.getCustomerName(), instrumentsToOrder);

    // отправляем запрос во внешнюю систему для проверки наличии и готовности инструментов в к доставке получателю
    storageExternalSystem.checkInstruments(getStorageRequest(newOrder));

    //сохраняем заказ
    newOrder = orderRepository.save(newOrder);

    // сохраняем состояние инструменов после сборки заказа
    instrumentRepository.saveAll(newOrder.getInstruments());
    log.info("Oder with UUID {} successfully created", newOrder.getUuid() );
  }

  private List<Instrument> getInstrumentsByNameAndQuantity(String instrumentName, int quantity) {

    InstrumentName name = InstrumentName.of(instrumentName);

    if (quantity == 1) {
      // если данный инструмент в заказе один то просто находим первый попавшийся
      return Collections.singletonList(instrumentRepository.findFirstByName(name));
    } else {
      // если несколько то берем все инструменты согласно лимиту ввиде quantity
      return instrumentRepository.findAllByNameLimited(name, quantity);
    }
  }

  private CheckInstrumentsRequest getStorageRequest(Order order) {
    CheckInstrumentsRequest request = new CheckInstrumentsRequest();

    List<InstrumentDto> instrumentsToCheck = order.getInstruments().stream().map(instrument -> {
      return new InstrumentDto(instrument.getSeriesNumber());
    }).toList();

    request.setOrderId(order.getUuid());
    request.setInstrumentsToCheck(instrumentsToCheck);

    return request;
  }
}
