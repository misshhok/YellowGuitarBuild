package ru.bsu.yellowguitarbend.infrastructure.interfaces.to.request;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class StartDeliveryRequest {
  private String orderUuid;
  private List<InstrumentDto> instrumentsToDelivery;
}
