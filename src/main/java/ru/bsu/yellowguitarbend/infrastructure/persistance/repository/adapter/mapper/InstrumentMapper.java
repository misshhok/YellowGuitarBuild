package ru.bsu.yellowguitarbend.infrastructure.persistance.repository.adapter.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.bsu.yellowguitarbend.domain.entity.Instrument;
import ru.bsu.yellowguitarbend.domain.value.InstrumentName;
import ru.bsu.yellowguitarbend.infrastructure.config.SpringComponentMapper;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.InstrumentEntity;

@Mapper(
  config = SpringComponentMapper.class
)
public interface InstrumentMapper {

  @Mapping(target = "commercialName", source = "commercialName", qualifiedByName = "getCommercialName")
  Instrument toDomain(InstrumentEntity entity);

  @Mapping(target = "commercialName", source = "commercialName.value")
  InstrumentEntity toEntity(Instrument domain);


  @Named("getCommercialName")
  default InstrumentName mapCommercialName(String commercialName) {
    return InstrumentName.of(commercialName);
  }
}
