package ru.bsu.yellowguitarbend.infrastructure.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request.ChangeInstrumentPriceRequest;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.InstrumentEntity;
import ru.bsu.yellowguitarbend.infrastructure.persistance.repository.InstrumentJpaRepository;


@Slf4j
@Service
@RequiredArgsConstructor
public class ChangeInstrumentPriceUseCase {
  private final InstrumentJpaRepository repository;

  public void execute(ChangeInstrumentPriceRequest request) {
    InstrumentEntity instrument = repository.findBySeriesNumber(request.getSeriesNumber())
      .orElseThrow(() -> new IllegalArgumentException("Instrument not found"));
    instrument.changePrice(request.getNewPrice());
    repository.save(instrument);
  }
}
