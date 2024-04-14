package ru.bsu.yellowguitarbend.application.external.direction.to;

import ru.bsu.yellowguitarbend.application.external.direction.to.dto.request.StartDeliveryRequest;

public interface ToDeliveryInterface {
  boolean startDelivery(StartDeliveryRequest request);
}
