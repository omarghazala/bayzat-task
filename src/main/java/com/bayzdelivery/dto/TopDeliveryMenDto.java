package com.bayzdelivery.dto;

import java.math.BigDecimal;

public class TopDeliveryMenDto {
    private Long id;
    private String name;
    private String email;
    private Double totalCommission;
    private Double averageCommission;

    public TopDeliveryMenDto(Long id, String name, String email, Double totalCommission,Double averageCommission) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.totalCommission = totalCommission;
        this.averageCommission = averageCommission;
    }

    // Add getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(Double totalCommission) {
        this.totalCommission = totalCommission;
    }

    public Double getAverageCommission() {
        return averageCommission;
    }

    public void setAverageCommission(Double averageCommission) {
        this.averageCommission = averageCommission;
    }
}
