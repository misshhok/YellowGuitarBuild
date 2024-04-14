package ru.bsu.yellowguitarbend.application.external.direction.from.dto.request;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class CheckInstrumentsResultRequest {
  private UUID orderUuid;
  private List<CheckedInstrumentDto> checkedInstruments;
}
