package ru.bsu.yellowguitarbend.infrastructure.persistance.repository.adapter.mapper;

import org.mapstruct.Mapper;
import ru.bsu.yellowguitarbend.domain.agregate.Order;
import ru.bsu.yellowguitarbend.infrastructure.config.SpringComponentMapper;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.OrderEntity;

@Mapper(
  config = SpringComponentMapper.class,
  uses = InstrumentMapper.class
)
public interface OrderMapper {

  Order toDomain(OrderEntity entity);

  OrderEntity toEntity(Order domain);

}
