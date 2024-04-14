package ru.bsu.yellowguitarbend.application.usecase;

import lombok.extern.slf4j.Slf4j;
import ru.bsu.yellowguitarbend.application.external.direction.from.dto.request.ChangeInstrumentPriceRequest;
import ru.bsu.yellowguitarbend.application.persistance.InstrumentRepository;
import ru.bsu.yellowguitarbend.domain.entity.Instrument;

@Slf4j
public class ChangeInstrumentPriceUseCase {
  private final InstrumentRepository repository;

  public ChangeInstrumentPriceUseCase(InstrumentRepository repository) {
    this.repository = repository;
  }

  public void execute(ChangeInstrumentPriceRequest request) {
    Instrument instrument = repository.findBySeriesNumber(request.getSeriesNumber());
    instrument.changePrice(request.getNewPrice());
    repository.save(instrument);
  }
}
