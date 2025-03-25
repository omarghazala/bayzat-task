package com.bayzdelivery.mapper;

import com.bayzdelivery.dto.PersonDto;
import com.bayzdelivery.generator.StringGenerator;
import com.bayzdelivery.model.Person;
import com.bayzdelivery.repositories.PersonRepository;

public class PersonMapper {
    public static Person mapPersonDtoToPerson(PersonDto personDto, PersonRepository personRepository){
        Person person = new Person();
        if(personDto.getName() != null){
            person.setName(personDto.getName());
        }
        if(personDto.getEmail() != null) {
            if(personRepository.existsByEmail(personDto.getEmail())){
                throw new RuntimeException("Email already exists: " + personDto.getEmail());
            }
            person.setEmail(personDto.getEmail());
        }

        String newRegistrationNumber = StringGenerator.generateUniqueString();

        if(personRepository.existsByRegistrationNumber(newRegistrationNumber)){
            throw new RuntimeException("Registration number already exists: " + newRegistrationNumber);
        }

        person.setRegistrationNumber(newRegistrationNumber);
        return person;
    }
}
