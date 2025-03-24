package com.bayzdelivery.mapper;

import com.bayzdelivery.dto.PersonDto;
import com.bayzdelivery.generator.StringGenerator;
import com.bayzdelivery.model.Person;

public class PersonMapper {
    public static Person mapPersonDtoToPerson(PersonDto personDto){
        Person person = new Person();
        if(personDto.getName() != null){
            person.setName(personDto.getName());
        }
        if(personDto.getEmail() != null) {
            person.setEmail(personDto.getEmail());
        }

        person.setRegistrationNumber(StringGenerator.generateUniqueString());
        return person;
    }
}
