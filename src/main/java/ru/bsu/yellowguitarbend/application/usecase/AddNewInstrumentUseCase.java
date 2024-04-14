package ru.bsu.yellowguitarbend.application.usecase;

import lombok.extern.slf4j.Slf4j;
import ru.bsu.yellowguitarbend.application.external.direction.from.dto.request.AddNewInstrumentRequest;
import ru.bsu.yellowguitarbend.application.persistance.InstrumentRepository;
import ru.bsu.yellowguitarbend.domain.entity.Instrument;
import ru.bsu.yellowguitarbend.domain.value.InstrumentName;

@Slf4j
public class AddNewInstrumentUseCase {
  private final InstrumentRepository repository;

  public AddNewInstrumentUseCase(InstrumentRepository repository) {
    this.repository = repository;
  }


  public void execute(AddNewInstrumentRequest request) {
    repository.save(Instrument.of(
      request.getSeriesNumber(),
      InstrumentName.of(request.getName()),
      request.getInstrumentType(),
      request.getPrice()
    ));
  }
}
