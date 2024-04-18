package ru.bsu.yellowguitarbend.infrastructure.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.OrderEntity;
import java.util.Optional;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {
  Optional<OrderEntity> findByUuid(String uuid);
}
