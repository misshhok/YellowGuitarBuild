package ru.bsu.yellowguitarbend.infrastructure.interfaces.from;


import org.springframework.http.ResponseEntity;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request.AddNewInstrumentRequest;

public interface FromFrontendInterface {
  ResponseEntity<Void> addNewInstrument(AddNewInstrumentRequest request);
}
