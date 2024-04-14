package ru.bsu.yellowguitarbend.domain.agregate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import ru.bsu.yellowguitarbend.domain.entity.Instrument;
import ru.bsu.yellowguitarbend.domain.enums.OrderState;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@ToString
@NoArgsConstructor
@Setter
public class Order {
  private UUID uuid;
  private String address;
  private String customerName;
  private List<Instrument> instruments;
  private Long totalSum;
  private ZonedDateTime createDate;
  private OrderState state;
  private ZonedDateTime lastUpdate;

  public Order(UUID uuid, String address, String customerName,
                List<Instrument> instruments, Long totalSum, OrderState state,
                ZonedDateTime createDate, ZonedDateTime lastUpdate) {
    this.uuid = uuid;
    this.address = address;
    this.customerName = customerName;
    this.instruments = instruments;
    this.totalSum = totalSum;
    this.state = state;
    this.createDate = createDate;
    this.lastUpdate = lastUpdate;
  }


  public Order copy(UUID uuid, String address, String customerName,
                List<Instrument> instruments, Long totalSum, OrderState state,
                ZonedDateTime createDate, ZonedDateTime lastUpdate) {
    return new Order(
    uuid,
    address,
    customerName,
    instruments,
    totalSum,
    state,
    createDate,
    lastUpdate
    );
  }


  public static Order createNew(@NonNull String address,
                                @NonNull String customerName,
                                @NonNull List<Instrument> instruments) {
    Long calculatedSumOfInstruments = instruments.stream()
      .reduce(0L, (x, y) -> {
        return x + y.getPriceCop();
      }, Long::sum);
    // Указываем что все инструменты подготовлены для заказа и недоступны для последующих заказов
    instruments.forEach(Instrument::ordered);

    return new Order(
      UUID.randomUUID(),
      address,
      customerName,
      instruments,
      calculatedSumOfInstruments,
      OrderState.NEW,
      ZonedDateTime.now(),
      ZonedDateTime.now());
  }

  public void failed() {
    if (this.state.equals(OrderState.NEW)) {
      this.state = OrderState.FAILED;
      this.lastUpdate = ZonedDateTime.now();
    } else {
      throw new IllegalStateException("Can't transit to failed state non-NEW order");
    }
  }

  public void startDelivering() {
    if (this.state == OrderState.FAILED) {
      this.state = OrderState.DELIVERING;
      this.lastUpdate = ZonedDateTime.now();
    } else {
      throw new IllegalStateException("Can't start delivering order because current state is not " +
        OrderState.FAILED);
    }
  }

  public void finishDelivering() {
    if (this.state == OrderState.DELIVERING) {
      this.state = OrderState.DELIVERED;
      this.lastUpdate = ZonedDateTime.now();
    } else {
      throw new IllegalStateException("Can't finish delivering order because current state is not " +
        OrderState.DELIVERING);
    }
  }


}
