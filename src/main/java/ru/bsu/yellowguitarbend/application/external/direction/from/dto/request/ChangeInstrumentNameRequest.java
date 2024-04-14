package ru.bsu.yellowguitarbend.application.external.direction.from.dto.request;

import lombok.Data;

@Data
public class ChangeInstrumentNameRequest {
  private String seriesNumber;
  private String newName;
}
