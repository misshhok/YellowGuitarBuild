package ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request;

import lombok.Data;
import java.util.List;

@Data
public class CreateNewOrderRequest {
  private String address;
  private String customerName;
  private List<OrderItemDto> items;
}
