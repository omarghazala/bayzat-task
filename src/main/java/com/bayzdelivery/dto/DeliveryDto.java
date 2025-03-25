package com.bayzdelivery.dto;

import com.bayzdelivery.model.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public class DeliveryDto {
    Instant startTime;

    Instant endTime;

    Long distance;

    Long price;

    Long deliveryManId;

    Long customerId;

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getDeliveryManId() {
        return deliveryManId;
    }

    public void setDeliveryManId(Long deliveryManId) {
        this.deliveryManId = deliveryManId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
