package ru.bsu.yellowguitarbend.infrastructure.interfaces.to.stub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.to.ToStorageInterface;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.to.request.CheckInstrumentsRequest;

@Slf4j
@Component
public class StorageStub implements ToStorageInterface {
  @Override
  public boolean checkInstruments(CheckInstrumentsRequest request) {
    log.info("Going to storage external service via HTTP or Message Broker");
    return true;
  }
}
