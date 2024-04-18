package ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request;

import lombok.Data;

@Data
public class ChangeInstrumentPriceRequest {
  private String seriesNumber;
  private Long newPrice;
}
