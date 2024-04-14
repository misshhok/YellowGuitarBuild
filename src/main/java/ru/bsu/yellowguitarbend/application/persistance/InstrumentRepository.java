package ru.bsu.yellowguitarbend.application.persistance;

import ru.bsu.yellowguitarbend.domain.entity.Instrument;
import ru.bsu.yellowguitarbend.domain.value.InstrumentName;
import java.util.List;

public interface InstrumentRepository {
  Instrument findFirstByName(InstrumentName name);

  List<Instrument> findAllByNameLimited(InstrumentName name, int limit);

  List<Instrument> findAllBySeriesNumberIn(List<String> seriesNumbers);

  Instrument findBySeriesNumber(String seriesNumber);

  Instrument save(Instrument instrument);

  List<Instrument> saveAll(List<Instrument> instruments);
}
