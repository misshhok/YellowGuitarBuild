package ru.bsu.yellowguitarbend.application.external.direction.from.dto.request;

import lombok.Data;

@Data
public class OrderItemDto {
  private String instrumentName;
  private int quantity;
}
