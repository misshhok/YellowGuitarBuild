package ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request;

import lombok.Data;

@Data
public class CheckedInstrumentDto {
  private String seriesNumber;
  private Boolean readyForDelivery;
}
