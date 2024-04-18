package ru.bsu.yellowguitarbend.infrastructure.interfaces.from.response;

import lombok.Data;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.enums.OrderState;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class OrderResponse {
  private String uuid;
  private String address;
  private String customerName;
  private List<InstrumentResponse> instruments;
  private Long totalSum;
  private ZonedDateTime createDate;
  private OrderState state;
  private ZonedDateTime lastUpdate;
}
