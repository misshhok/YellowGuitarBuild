package ru.bsu.yellowguitarbend.infrastructure.interfaces.from.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.response.OrderResponse;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.OrderEntity;
import java.util.List;

@Mapper(
  componentModel = ComponentModel.SPRING,
  uses = InstrumentMapper.class
)
public interface OrderMapper {

  OrderResponse toResponse(OrderEntity entity);

  List<OrderResponse> toResponseList(List<OrderEntity> entities);
}
