package ru.bsu.yellowguitarbend.application.external.direction.to.dto.request;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class StartDeliveryRequest {
  private UUID orderUuid;
  private List<InstrumentDto> instrumentsToDelivery;
}
