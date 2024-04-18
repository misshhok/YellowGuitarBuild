package ru.bsu.yellowguitarbend.infrastructure.persistance.entity.enums;

import lombok.ToString;

@ToString
public enum OrderState {
  NEW,
  FAILED,
  DELIVERING,
  DELIVERED
}
