package ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request;

import lombok.Data;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.enums.InstrumentType;

@Data
public class AddNewInstrumentRequest {
  private String name;
  private String seriesNumber;
  private Long price;
  private InstrumentType instrumentType;
}
