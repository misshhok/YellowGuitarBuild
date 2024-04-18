package ru.bsu.yellowguitarbend.infrastructure.interfaces.to;

import ru.bsu.yellowguitarbend.infrastructure.interfaces.to.request.CheckInstrumentsRequest;

public interface ToStorageInterface {
  boolean checkInstruments(CheckInstrumentsRequest request);
}
