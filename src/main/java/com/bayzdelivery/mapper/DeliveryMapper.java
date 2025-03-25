package com.bayzdelivery.mapper;

import com.bayzdelivery.dto.DeliveryDto;
import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.Person;
import com.bayzdelivery.repositories.DeliveryRepository;
import com.bayzdelivery.repositories.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryMapper {
    public static Delivery mapDeliveryDtoToDelivery(DeliveryDto deliveryDto, DeliveryRepository deliveryRepository, PersonRepository personRepository) {

        if (deliveryDto == null) {
            throw new IllegalArgumentException("DeliveryDto cannot be null");
        }
        List<String> validationErrors = validateDeliveryDto(deliveryDto);


        Delivery delivery = new Delivery();
        delivery.setComission((deliveryDto.getPrice()*0.05)+(deliveryDto.getDistance()*0.5));
        delivery.setDistance(deliveryDto.getDistance());
        delivery.setPrice(deliveryDto.getPrice());
        delivery.setEndTime(deliveryDto.getEndTime());
        delivery.setStartTime(deliveryDto.getStartTime());

        if(deliveryDto.getDeliveryManId() != null && deliveryDto.getCustomerId() != null){
            if(deliveryDto.getDeliveryManId().equals(deliveryDto.getCustomerId())){
                throw new RuntimeException("The same person can't do both jobs " + deliveryDto.getDeliveryManId());
            }
        }

        if (deliveryDto.getCustomerId() != null) {
            if (!personRepository.existsById(deliveryDto.getCustomerId())) {
                throw new RuntimeException("Person doesn't exist: " + deliveryDto.getCustomerId());
            }

            Person customer = personRepository.findById(deliveryDto.getCustomerId()).get();

            if(customer.getPersonType() == null || customer.getPersonType().getId() !=  1L ){
                throw new RuntimeException("Person is not a customer " + deliveryDto.getCustomerId());
            }

            delivery.setCustomer(personRepository.findById(deliveryDto.getCustomerId()).get());
        }

        if (deliveryDto.getDeliveryManId() != null) {
            if (!personRepository.existsById(deliveryDto.getDeliveryManId())) {
                throw new RuntimeException("Person doesn't exist: " + deliveryDto.getDeliveryManId());
            }
            Person deliveryMan = personRepository.findById(deliveryDto.getDeliveryManId()).get();

            if(deliveryMan.getPersonType() == null || deliveryMan.getPersonType().getId() !=  2L ){
                throw new RuntimeException("Person is not a delivery man " + deliveryDto.getDeliveryManId());
            }

            if (deliveryDto.getStartTime() != null && deliveryDto.getEndTime() != null ) {
                List<Delivery> overlappingDeliveries = deliveryRepository
                        .findOverlappingDeliveriesForDeliveryMan(
                                deliveryDto.getDeliveryManId(),
                                deliveryDto.getStartTime(),
                                deliveryDto.getEndTime()
                        );

                if (!overlappingDeliveries.isEmpty()) {
                    throw new RuntimeException("Delivery man with ID " + deliveryDto.getDeliveryManId() +
                            " is already assigned to another delivery during this time period. Conflicting delivery IDs: " +
                            overlappingDeliveries.stream().map(d -> d.getId().toString()).collect(Collectors.joining(", ")));
                }
            }
            delivery.setDeliveryMan(deliveryMan);
        }


        return delivery;
    }

    private static List<String> validateDeliveryDto(DeliveryDto deliveryDto) {

        List<String> errors = new ArrayList<>();

        if (deliveryDto.getPrice() == null) {
            errors.add("Price is required");
        } else if (deliveryDto.getPrice() <= 0) {
            errors.add("Price must be greater than zero");
        }

        if (deliveryDto.getDistance() == null) {
            errors.add("Distance is required");
        } else if (deliveryDto.getDistance() <= 0) {
            errors.add("Distance must be greater than zero");
        }

        if (deliveryDto.getStartTime() == null) {
            errors.add("Start time is required");
        }

        if (deliveryDto.getEndTime() != null && deliveryDto.getStartTime() != null) {
            if (deliveryDto.getEndTime().isBefore(deliveryDto.getStartTime())) {
                errors.add("End time cannot be before start time");
            }
        }

        if (deliveryDto.getCustomerId() == null) {
            errors.add("Customer ID is required");
        }

        if (deliveryDto.getDeliveryManId() == null) {
            errors.add("Delivery Man ID is required");
        }

        return errors;

    }
}