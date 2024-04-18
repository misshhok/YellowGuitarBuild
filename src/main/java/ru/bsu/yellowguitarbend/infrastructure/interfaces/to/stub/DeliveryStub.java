package ru.bsu.yellowguitarbend.infrastructure.interfaces.to.stub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.to.ToDeliveryInterface;
import ru.bsu.yellowguitarbend.infrastructure.interfaces.to.request.StartDeliveryRequest;

@Slf4j
@Component
public class DeliveryStub implements ToDeliveryInterface {

  @Override
  public boolean startDelivery(StartDeliveryRequest request) {
    log.info("Going to delivery external service via HTTP or Message Broker");
    return true;
  }
}
