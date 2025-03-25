package com.bayzdelivery.service;

import java.util.Optional;

import com.bayzdelivery.dto.DeliveryDto;
import com.bayzdelivery.mapper.DeliveryMapper;
import com.bayzdelivery.repositories.DeliveryRepository;
import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {

  @Autowired
  DeliveryRepository deliveryRepository;

  @Autowired
  PersonRepository personRepository;

  public Delivery save(DeliveryDto deliveryDto) {
    return deliveryRepository.save(DeliveryMapper.mapDeliveryDtoToDelivery(deliveryDto,deliveryRepository ,personRepository));
  }

  public Delivery findById(Long deliveryId) {
    Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
    if (optionalDelivery.isPresent()) {
      return optionalDelivery.get();
    }else return null;
  }
}
