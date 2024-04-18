package ru.bsu.yellowguitarbend.infrastructure.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request.AddNewInstrumentRequest;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.InstrumentEntity;
import ru.bsu.yellowguitarbend.infrastructure.persistance.repository.InstrumentJpaRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddNewInstrumentUseCase {
  private final InstrumentJpaRepository repository;


  public void execute(AddNewInstrumentRequest request) {
    repository.save(InstrumentEntity.of(
      request.getSeriesNumber(),
      request.getName(),
      request.getInstrumentType(),
      request.getPrice()
    ));
  }
}
