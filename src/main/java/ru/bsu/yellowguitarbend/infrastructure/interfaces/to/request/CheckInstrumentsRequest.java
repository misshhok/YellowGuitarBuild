package ru.bsu.yellowguitarbend.infrastructure.interfaces.to.request;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class CheckInstrumentsRequest {
  private String orderId;
  private List<InstrumentDto> instrumentsToCheck;
}
