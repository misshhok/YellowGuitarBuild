package ru.bsu.yellowguitarbend.infrastructure.interfaces.from.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.FromStorageInterface;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request.CheckInstrumentsResultRequest;
import ru.bsu.yellowguitarbend.infrastructure.usecase.StartDeliveryUseCase;

@RestController
@RequestMapping("/api/v1/storage/")
@RequiredArgsConstructor
public class StorageSystemController implements FromStorageInterface {
  private final StartDeliveryUseCase startDeliveryUseCase;

  @PostMapping("checkResult/")
  public ResponseEntity<Void> handleInstrumentsCheckResult(@RequestBody CheckInstrumentsResultRequest request) {
    startDeliveryUseCase.execute(request);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
