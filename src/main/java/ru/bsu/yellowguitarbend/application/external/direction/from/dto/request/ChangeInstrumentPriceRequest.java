package ru.bsu.yellowguitarbend.application.external.direction.from.dto.request;

import lombok.Data;

@Data
public class ChangeInstrumentPriceRequest {
  private String seriesNumber;
  private Long newPrice;
}
