package ru.bsu.yellowguitarbend.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.mapper.InstrumentMapper;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.mapper.OrderMapper;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.response.InstrumentResponse;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.from.response.OrderResponse;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.InstrumentEntity;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.OrderEntity;
import ru.bsu.yellowguitarbend.infrastructure.persistance.repository.InstrumentJpaRepository;
import ru.bsu.yellowguitarbend.infrastructure.persistance.repository.OrderJpaRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FrontendService {
  private final OrderJpaRepository orderRepository;
  private final InstrumentJpaRepository instrumentRepository;
  private final InstrumentMapper instrumentMapper;
  private final OrderMapper orderMapper;

  public InstrumentResponse getInstrument(String seriesNumber) {
    InstrumentEntity entity = instrumentRepository.findBySeriesNumber(seriesNumber)
      .orElseThrow(() -> new IllegalArgumentException("Instrument not found"));
    return instrumentMapper.toResponse(entity);
  }

  public List<InstrumentResponse> getAllInstruments() {
    return instrumentMapper.toResponseList(instrumentRepository.findAll());
  }

  public OrderResponse getOrder(String uuid) {
    OrderEntity entity = orderRepository.findByUuid(uuid)
      .orElseThrow(() -> new IllegalArgumentException("Instrument not found"));
    return orderMapper.toResponse(entity);
  }

  public List<OrderResponse> getAllOrders() {
    return orderMapper.toResponseList(orderRepository.findAll());
  }
}
