package ru.bsu.yellowguitarbend.application.external.direction.from.dto.request;

import lombok.Data;
import ru.bsu.yellowguitarbend.domain.enums.InstrumentType;

@Data
public class AddNewInstrumentRequest {
  private String name;
  private String seriesNumber;
  private Long price;
  private InstrumentType instrumentType;
}
