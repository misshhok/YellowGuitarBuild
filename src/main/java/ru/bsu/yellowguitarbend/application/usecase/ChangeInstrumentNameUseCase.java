package ru.bsu.yellowguitarbend.application.usecase;

import lombok.extern.slf4j.Slf4j;
import ru.bsu.yellowguitarbend.application.external.direction.from.dto.request.ChangeInstrumentNameRequest;
import ru.bsu.yellowguitarbend.application.persistance.InstrumentRepository;
import ru.bsu.yellowguitarbend.domain.entity.Instrument;

@Slf4j
public class ChangeInstrumentNameUseCase {

  private final InstrumentRepository repository;

  public ChangeInstrumentNameUseCase(InstrumentRepository repository) {
    this.repository = repository;
  }

  public void execute(ChangeInstrumentNameRequest request) {
    Instrument instrument = repository.findBySeriesNumber(request.getSeriesNumber());
    instrument.changeName(request.getNewName());
    repository.save(instrument);
  }
}
