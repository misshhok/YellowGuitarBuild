package ru.bsu.yellowguitarbend.application.external.direction.from.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class CreateNewOrderRequest {
  private String address;
  private String customerName;
  private List<OrderItemDto> items;
}
