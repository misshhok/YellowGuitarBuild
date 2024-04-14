package ru.bsu.yellowguitarbend.application.external.direction.to.dto.request;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class CheckInstrumentsRequest {
  private UUID orderId;
  private List<InstrumentDto> instrumentsToCheck;
}
