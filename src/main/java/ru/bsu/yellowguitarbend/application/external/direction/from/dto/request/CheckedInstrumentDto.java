package ru.bsu.yellowguitarbend.application.external.direction.from.dto.request;

import lombok.Data;

@Data
public class CheckedInstrumentDto {
  private String seriesNumber;
  private Boolean readyForDelivery;
}
