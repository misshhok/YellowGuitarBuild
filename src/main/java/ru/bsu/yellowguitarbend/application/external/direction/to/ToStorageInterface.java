package ru.bsu.yellowguitarbend.application.external.direction.to;

import ru.bsu.yellowguitarbend.application.external.direction.to.dto.request.CheckInstrumentsRequest;

public interface ToStorageInterface {
  void checkInstruments(CheckInstrumentsRequest request);
}
