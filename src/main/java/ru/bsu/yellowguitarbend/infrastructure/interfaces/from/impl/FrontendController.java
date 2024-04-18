package ru.bsu.yellowguitarbend.infrastructure.interfaces.from.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.FromFrontendInterface;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request.AddNewInstrumentRequest;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request.ChangeInstrumentNameRequest;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request.ChangeInstrumentPriceRequest;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request.CreateNewOrderRequest;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.response.InstrumentResponse;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.response.OrderResponse;
import ru.bsu.yellowguitarbend.infrastructure.service.FrontendService;
import ru.bsu.yellowguitarbend.infrastructure.usecase.AddNewInstrumentUseCase;
import ru.bsu.yellowguitarbend.infrastructure.usecase.ChangeInstrumentNameUseCase;
import ru.bsu.yellowguitarbend.infrastructure.usecase.ChangeInstrumentPriceUseCase;
import ru.bsu.yellowguitarbend.infrastructure.usecase.CreateNewOrderUseCase;
import java.util.List;

@RestController
@RequestMapping("/api/v1/front/")
@RequiredArgsConstructor
public class FrontendController implements FromFrontendInterface {
  private final AddNewInstrumentUseCase addNewInstrumentUseCase;
  private final CreateNewOrderUseCase createNewOrderUseCase;
  private final ChangeInstrumentPriceUseCase changeInstrumentPriceUseCase;
  private final ChangeInstrumentNameUseCase changeInstrumentNameUseCase;
  private final FrontendService frontendService;


  @PostMapping("instruments/")
  @Override
  public ResponseEntity<Void> addNewInstrument(@RequestBody  AddNewInstrumentRequest request) {
    addNewInstrumentUseCase.execute(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("instruments/{seriesNumber}/")
  public ResponseEntity<InstrumentResponse> getInstrument(@PathVariable String seriesNumber) {
    InstrumentResponse response = frontendService.getInstrument(seriesNumber);
    return ResponseEntity.ok().body(response);
  }

  @GetMapping("instruments/")
  public ResponseEntity<List<InstrumentResponse>> getAllInstruments() {
    List<InstrumentResponse> response = frontendService.getAllInstruments();
    return ResponseEntity.ok().body(response);
  }

  @GetMapping("orders/{uuid}/")
  public ResponseEntity<OrderResponse> getOrder(@PathVariable String uuid) {
    OrderResponse response = frontendService.getOrder(uuid);
    return ResponseEntity.ok().body(response);
  }

  @GetMapping("orders/")
  public ResponseEntity<List<OrderResponse>> getAllOrders() {
    List<OrderResponse> response = frontendService.getAllOrders();
    return ResponseEntity.ok().body(response);
  }

  @PostMapping("orders/")
  public ResponseEntity<Void> createNewOrder(@RequestBody CreateNewOrderRequest request) {
    createNewOrderUseCase.execute(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("instruments/changePrice/")
  public ResponseEntity<Void> changePrice(@RequestBody ChangeInstrumentPriceRequest request) {
    changeInstrumentPriceUseCase.execute(request);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @PutMapping("instruments/changName/")
  public ResponseEntity<Void> changeName(@RequestBody ChangeInstrumentNameRequest request) {
    changeInstrumentNameUseCase.execute(request);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
