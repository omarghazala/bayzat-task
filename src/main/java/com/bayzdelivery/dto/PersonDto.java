package com.bayzdelivery.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class PersonDto {
    String name;
    String email;

    Long personTypeId;


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

    public Long getPersonTypeId() {
        return personTypeId;
    }

    public void setPersonTypeId(Long personTypeId) {
        this.personTypeId = personTypeId;
    }
}
