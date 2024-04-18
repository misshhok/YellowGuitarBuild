package ru.bsu.yellowguitarbend.infrastructure.interfaces.from.request;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class CheckInstrumentsResultRequest {
  private String orderUuid;
  private List<CheckedInstrumentDto> checkedInstruments;
}
