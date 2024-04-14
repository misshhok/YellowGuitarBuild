package ru.bsu.yellowguitarbend.application.external.direction.from.dto.request;

import lombok.Data;
import java.util.UUID;

@Data
public class OrderDeliveredRequest {
  private UUID orderUuid;
}
