package ru.bsu.yellowguitarbend.application.persistance;

import ru.bsu.yellowguitarbend.domain.agregate.Order;
import java.util.UUID;

public interface OrderRepository {
  Order save(Order order);
  Order findByUuid(UUID uuid);
}
