package com.bayzdelivery.service;

import com.bayzdelivery.dto.DeliveryDto;
import com.bayzdelivery.dto.TopDeliveryMenDto;
import com.bayzdelivery.model.Delivery;

import java.time.Instant;
import java.util.List;

public interface DeliveryService {

  public Delivery save(DeliveryDto delivery);

  public Delivery findById(Long deliveryId);

  public List<TopDeliveryMenDto> getTopDeliveryMenByCommission(Instant startTime, Instant endTime);
}
