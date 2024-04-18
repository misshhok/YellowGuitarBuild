package ru.bsu.yellowguitarbend.infrastructure.interfaces.from.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.response.InstrumentResponse;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.InstrumentEntity;
import java.util.List;

@Mapper(componentModel = ComponentModel.SPRING)
public interface InstrumentMapper {

  @Mapping(target = "orderUuid", source = "order.uuid")
  InstrumentResponse toResponse(InstrumentEntity entity);

  List<InstrumentResponse> toResponseList(List<InstrumentEntity> entities);
}
