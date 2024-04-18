package ru.bsu.yellowguitarbend.infrastructure.interfaces.from.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.FromDeliveryInterface;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request.OrderDeliveredRequest;
import ru.bsu.yellowguitarbend.infrastructure.usecase.FinishDeliveryUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery/")
public class DeliverySystemController implements FromDeliveryInterface {
  private final FinishDeliveryUseCase finishDeliveryUseCase;


  @PostMapping("finish/")
  public ResponseEntity<Void> finishDelivery(@RequestBody OrderDeliveredRequest request) {
    finishDeliveryUseCase.execute(request);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
