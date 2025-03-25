package com.bayzdelivery.mapper;

import com.bayzdelivery.dto.DeliveryDto;
import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.repositories.PersonRepository;

import java.util.ArrayList;
import java.util.List;

public class DeliveryMapper {
    public static Delivery mapDeliveryDtoToDelivery(DeliveryDto deliveryDto, PersonRepository personRepository) {

        if (deliveryDto == null) {
            throw new IllegalArgumentException("DeliveryDto cannot be null");
        }
        List<String> validationErrors = validateDeliveryDto(deliveryDto);


        Delivery delivery = new Delivery();
        delivery.setComission(deliveryDto.getComission());
        delivery.setDistance(deliveryDto.getDistance());
        delivery.setPrice(deliveryDto.getPrice());
        delivery.setEndTime(deliveryDto.getEndTime());
        delivery.setStartTime(deliveryDto.getStartTime());

        if (deliveryDto.getDeliveryManId() != null) {
            if (!personRepository.existsById(deliveryDto.getDeliveryManId())) {
                throw new RuntimeException("Person doesn't exist: " + deliveryDto.getDeliveryManId());
            }
            delivery.setDeliveryMan(personRepository.findById(deliveryDto.getDeliveryManId()).get());
        }

        if (deliveryDto.getCustomerId() != null) {
            if (!personRepository.existsById(deliveryDto.getCustomerId())) {
                throw new RuntimeException("Person doesn't exist: " + deliveryDto.getDeliveryManId());
            }
            delivery.setCustomer(personRepository.findById(deliveryDto.getCustomerId()).get());
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

        // Commission validation - can add business rules here
        if (deliveryDto.getComission() != null && deliveryDto.getComission() < 0) {
            errors.add("Commission cannot be negative");
        }

        return errors;

    }
}