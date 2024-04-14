package ru.bsu.yellowguitarbend.infrastructure.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.bsu.yellowguitarbend.domain.enums.OrderState;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@ToString

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
}
