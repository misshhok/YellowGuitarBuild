package ru.bsu.yellowguitarbend.infrastructure.interfaces.to.stub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.bsu.yellowguitarbend.application.external.direction.to.ToStorageInterface;
import ru.bsu.yellowguitarbend.application.external.direction.to.dto.request.CheckInstrumentsRequest;

@Slf4j
@Component
public class StorageStub implements ToStorageInterface {
  @Override
  public void checkInstruments(CheckInstrumentsRequest request) {
    log.info("Going to storage external service via HTTP or Message Broker");
  }
}
