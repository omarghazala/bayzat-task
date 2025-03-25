package com.bayzdelivery.dto;

import java.time.Instant;

public class TopDeliveryMenBodyDto {
    Instant startTime;

    Instant endTime;

    public TopDeliveryMenBodyDto(){}

    public TopDeliveryMenBodyDto(Instant startTime, Instant endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

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
}
