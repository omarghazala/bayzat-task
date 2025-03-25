package com.bayzdelivery.service;

import com.bayzdelivery.dto.DeliveryDto;
import com.bayzdelivery.model.Delivery;

public interface DeliveryService {

  public Delivery save(DeliveryDto delivery);

  public Delivery findById(Long deliveryId);
}
