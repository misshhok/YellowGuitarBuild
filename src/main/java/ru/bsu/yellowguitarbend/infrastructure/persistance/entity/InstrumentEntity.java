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
import ru.bsu.yellowguitarbend.domain.enums.DeliveryState;
import ru.bsu.yellowguitarbend.domain.enums.InstrumentType;

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
  OrderEntity order;

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
}
