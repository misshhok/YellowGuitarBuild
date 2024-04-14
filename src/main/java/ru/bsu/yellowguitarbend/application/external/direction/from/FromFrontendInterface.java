package ru.bsu.yellowguitarbend.application.external.direction.from;

import ru.bsu.yellowguitarbend.application.external.direction.from.dto.request.AddNewInstrumentRequest;

public interface FromFrontendInterface {
  void addNewInstrument(AddNewInstrumentRequest request);
}
