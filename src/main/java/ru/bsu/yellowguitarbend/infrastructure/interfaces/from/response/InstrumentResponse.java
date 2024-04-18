package ru.bsu.yellowguitarbend.infrastructure.interfaces.from.response;

import lombok.Data;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.enums.DeliveryState;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.enums.InstrumentType;

@Data
public class InstrumentResponse {
  private String orderUuid;
  private InstrumentType type;
  private String seriesNumber;
  private Long priceCop;
  private String commercialName;
  private DeliveryState deliveryState;
  private boolean availableToOrder;
}
