package ru.bsu.yellowguitarbend.infrastructure.persistance.entity;

import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.enums.OrderState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

  @Id
  private String uuid;

  @Column
  private String address;

  @Column
  private String customerName;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
  private List<InstrumentEntity> instruments;

  @Column
  private Long totalSum;

  @Column
  private ZonedDateTime createDate;

  @Enumerated(EnumType.STRING)
  private OrderState state;

  @Column
  private ZonedDateTime lastUpdate;

  public static OrderEntity createNew(@NonNull String address,
                                      @NonNull String customerName,
                                      @NonNull List<InstrumentEntity> instruments) {
    Long calculatedSumOfInstruments = instruments.stream()
      .reduce(0L, (x, y) -> {
        return x + y.getPriceCop();
      }, Long::sum);
    // Указываем что все инструменты подготовлены для заказа и недоступны для последующих заказов
    instruments.forEach(InstrumentEntity::ordered);

    return new OrderEntity(
      UUID.randomUUID().toString(),
      address,
      customerName,
      instruments,
      calculatedSumOfInstruments,
      ZonedDateTime.now(),
      OrderState.NEW,
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
