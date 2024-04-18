package ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request;

import lombok.Data;

@Data
public class OrderItemDto {
  private String instrumentName;
  private int quantity;
}
