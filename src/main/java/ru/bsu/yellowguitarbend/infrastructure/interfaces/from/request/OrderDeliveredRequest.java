package ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request;

import lombok.Data;

@Data
public class OrderDeliveredRequest {
  private String orderUuid;
}
