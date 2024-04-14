package ru.bsu.yellowguitarbend.domain.enums;

import lombok.ToString;

@ToString
public enum OrderState {
  NEW,
  FAILED,
  DELIVERING,
  DELIVERED
}
