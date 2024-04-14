package ru.bsu.yellowguitarbend.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.bsu.yellowguitarbend.domain.enums.DeliveryState;
import ru.bsu.yellowguitarbend.domain.enums.InstrumentType;
import ru.bsu.yellowguitarbend.domain.value.InstrumentName;

/**
 * Инструмент - струнный музыкальный инструмент
 * основная единица бизнес-логики приложения - агрегат
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Instrument {
  private String seriesNumber;
  private InstrumentType type;
  private Long priceCop;
  private InstrumentName commercialName;
  private DeliveryState deliveryState;
  private boolean availableToOrder;

  private Instrument(InstrumentName commercialName,
                     String seriesNumber,
                     InstrumentType instrumentType, Long priceCop,
                     boolean availableToOrder, DeliveryState deliveryState) {
    this.commercialName = commercialName;
    this.seriesNumber = seriesNumber;
    this.type = instrumentType;
    this.priceCop = priceCop;
    this.availableToOrder = availableToOrder;
    this.deliveryState = deliveryState;
  }

  public static Instrument of(String seriesNumber, InstrumentName commercialName,
                              InstrumentType type, Long priceCop) {

    return new Instrument(commercialName, seriesNumber, type, priceCop, true, DeliveryState.NEED_CHECK);
  }

  public void ordered() {
    this.availableToOrder = false;
  }

  public void changeName(String newName) {
    if (isAvailableToOrder()) {
      this.commercialName = InstrumentName.of(newName);
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
