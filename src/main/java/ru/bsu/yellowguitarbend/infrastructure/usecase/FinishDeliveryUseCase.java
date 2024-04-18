package ru.bsu.yellowguitarbend.infrastructure.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request.OrderDeliveredRequest;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.OrderEntity;
import ru.bsu.yellowguitarbend.infrastructure.persistance.repository.OrderJpaRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class FinishDeliveryUseCase {
  private final OrderJpaRepository repository;

  public void execute(OrderDeliveredRequest request) {
    OrderEntity order = repository.findByUuid(request.getOrderUuid()).orElseThrow(
      () -> new IllegalArgumentException("Order not found")
    );
    order.finishDelivering();
    repository.save(order);
    log.info("Order with UUID {} successfully delivered", order.getUuid());
  }
}
