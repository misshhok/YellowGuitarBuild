package ru.bsu.yellowguitarbend.infrastructure.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.enums.DeliveryState;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.enums.InstrumentType;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instruments")
public class InstrumentEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_uuid")
  @ToString.Exclude
  private OrderEntity order;

  @Enumerated(EnumType.STRING)
  private InstrumentType type;

  @Id
  private String seriesNumber;

  @Column
  private Long priceCop;

  @Column
  private String commercialName;

  @Enumerated(EnumType.STRING)
  private DeliveryState deliveryState;

  @Column
  private boolean availableToOrder;

  public static InstrumentEntity of(String seriesNumber, String commercialName,
                                    InstrumentType type, Long priceCop) {

    return new InstrumentEntity(null, type, seriesNumber, priceCop,
      commercialName, DeliveryState.NEED_CHECK, true);
  }

  public void ordered() {
    this.availableToOrder = false;
  }

  public void changeName(String newName) {
    if (isAvailableToOrder()) {
      this.commercialName = newName;
    } else {
      throw new IllegalStateException(
        "Unable to change name of Instrument (seriesNumber - " + this.seriesNumber + ") which already ordered"
      );
    }
  }

  public void changePrice(Long newPrice) {
    this.priceCop = newPrice;
  }

  // По результатам проверки - товара либо нет, либо не готов к отправке
  public void notAvailable() {
    this.deliveryState = DeliveryState.NOT_AVAILABLE;
  }

  // По результатам проверки на складе - интрумент готов к отправке получателю
  public void readyForDelivery() {
    this.deliveryState = DeliveryState.AVAILABLE;
  }

}
