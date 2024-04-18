package ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request;

import lombok.Data;

@Data
public class ChangeInstrumentNameRequest {
  private String seriesNumber;
  private String newName;
}
