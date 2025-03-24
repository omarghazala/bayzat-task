package com.bayzdelivery.mapper;

import com.bayzdelivery.dto.PersonDto;
import com.bayzdelivery.model.Person;

public class PersonMapper {
    public static Person mapPersonDtoToPerson(PersonDto personDto){
        Person person = new Person();
        person.setName(personDto.getName());
        person.setEmail(personDto.getEmail());
        person.setRegistrationNumber(personDto.getRegistrationNumber());
        return person;
    }
}
