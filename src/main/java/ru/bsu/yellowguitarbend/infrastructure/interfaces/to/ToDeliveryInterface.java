package ru.bsu.yellowguitarbend.infrastructure.interfaces.to;

import ru.bsu.yellowguitarbend.infrastructure.interfaces.to.request.StartDeliveryRequest;

public interface ToDeliveryInterface {
  boolean startDelivery(StartDeliveryRequest request);
}
