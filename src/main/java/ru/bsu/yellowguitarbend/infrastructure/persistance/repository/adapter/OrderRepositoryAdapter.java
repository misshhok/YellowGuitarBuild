package ru.bsu.yellowguitarbend.infrastructure.persistance.repository.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.bsu.yellowguitarbend.application.persistance.OrderRepository;
import ru.bsu.yellowguitarbend.domain.agregate.Order;
import ru.bsu.yellowguitarbend.infrastructure.persistance.repository.OrderJpaRepository;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

  private final OrderJpaRepository repository;
  @Override
  public Order save(Order order) {
    return null;
  }

  @Override
  public Order findByUuid(UUID uuid) {
    return null;
  }
}
