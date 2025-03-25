package com.bayzdelivery.controller;

import com.bayzdelivery.dto.DeliveryDto;
import com.bayzdelivery.dto.TopDeliveryMenBodyDto;
import com.bayzdelivery.dto.TopDeliveryMenDto;
import com.bayzdelivery.model.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bayzdelivery.service.DeliveryService;

import java.util.List;

@RestController
public class DeliveryController {

  @Autowired
  DeliveryService deliveryService;

  @PostMapping(path ="/delivery")
  public ResponseEntity<Delivery> createNewDelivery(@RequestBody DeliveryDto delivery) {
    return ResponseEntity.ok(deliveryService.save(delivery));
  }

  @GetMapping(path = "/delivery/{delivery-id}")
  public ResponseEntity<Delivery> getDeliveryById(@PathVariable(name="delivery-id",required=true)Long deliveryId){
    Delivery delivery = deliveryService.findById(deliveryId);
    if (delivery !=null)
      return ResponseEntity.ok(delivery);
    return ResponseEntity.notFound().build();
  }

  @PostMapping("/top-delivery-men")
  public ResponseEntity<List<TopDeliveryMenDto>> getTopDeliveryMen(@RequestBody TopDeliveryMenBodyDto topDeliveryMenBodyDto) {

    List<TopDeliveryMenDto> topDeliveryMen = deliveryService.getTopDeliveryMenByCommission(topDeliveryMenBodyDto.getStartTime(), topDeliveryMenBodyDto.getEndTime());
    if(topDeliveryMen!=null){
      return ResponseEntity.ok(topDeliveryMen);
    }
    return ResponseEntity.notFound().build();
  }
}
