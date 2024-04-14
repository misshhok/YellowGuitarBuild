package ru.bsu.yellowguitarbend.application.usecase;

import lombok.extern.slf4j.Slf4j;
import ru.bsu.yellowguitarbend.application.external.direction.from.dto.request.OrderDeliveredRequest;
import ru.bsu.yellowguitarbend.application.persistance.OrderRepository;
import ru.bsu.yellowguitarbend.domain.agregate.Order;

@Slf4j
public class FinishDeliveringUseCase {
  private final OrderRepository repository;

  public FinishDeliveringUseCase(OrderRepository repository) {
    this.repository = repository;
  }

  public void execute(OrderDeliveredRequest request) {
    Order order = repository.findByUuid(request.getOrderUuid());
    order.finishDelivering();
    repository.save(order);
    log.info("Order with UUID {} successfully delivered", order.getUuid());
  }
}
