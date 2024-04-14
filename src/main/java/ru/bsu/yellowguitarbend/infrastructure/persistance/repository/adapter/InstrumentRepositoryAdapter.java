package ru.bsu.yellowguitarbend.infrastructure.persistance.repository.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.bsu.yellowguitarbend.application.persistance.InstrumentRepository;
import ru.bsu.yellowguitarbend.domain.entity.Instrument;
import ru.bsu.yellowguitarbend.domain.value.InstrumentName;
import ru.bsu.yellowguitarbend.infrastructure.persistance.repository.InstrumentJpaRepository;
import ru.bsu.yellowguitarbend.infrastructure.persistance.repository.adapter.mapper.InstrumentMapper;
import java.awt.print.Pageable;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InstrumentRepositoryAdapter implements InstrumentRepository {

  private final InstrumentJpaRepository jpaRepository;
  private final InstrumentMapper mapper;

  @Override
  public Instrument findFirstByName(InstrumentName name) {
    return mapper.toDomain(jpaRepository.findFirstByCommercialName(name.getValue()).orElseThrow(
      () -> new IllegalArgumentException("Instrument not found")
    ));
  }

  @Override
  public List<Instrument> findAllByNameLimited(InstrumentName name, int limit) {

    PageRequest pageable = PageRequest.of(0, limit);

    return mapper.;
  }

  @Override
  public List<Instrument> findAllBySeriesNumberIn(List<String> seriesNumbers) {
    return null;
  }

  @Override
  public Instrument findBySeriesNumber(String seriesNumber) {
    return null;
  }

  @Override
  public Instrument save(Instrument instrument) {
    return null;
  }

  @Override
  public List<Instrument> saveAll(List<Instrument> instruments) {
    return null;
  }
}
