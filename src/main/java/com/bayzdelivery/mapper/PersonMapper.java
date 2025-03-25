package com.bayzdelivery.mapper;

import com.bayzdelivery.dto.PersonDto;
import com.bayzdelivery.generator.StringGenerator;
import com.bayzdelivery.model.Person;
import com.bayzdelivery.repositories.PersonRepository;
import com.bayzdelivery.repositories.PersonTypeRepository;

public class PersonMapper {
    public static Person mapPersonDtoToPerson(PersonDto personDto, PersonRepository personRepository, PersonTypeRepository personTypeRepository){
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

        if(personDto.getPersonTypeId() != null){
            if(!personTypeRepository.existsById(personDto.getPersonTypeId())){
                throw new RuntimeException("Person type doesn't exist: " + personDto.getPersonTypeId());
            }
            person.setPersonType(personTypeRepository.findById(personDto.getPersonTypeId()).get());
        }
        String newRegistrationNumber = StringGenerator.generateUniqueString();

        if(personRepository.existsByRegistrationNumber(newRegistrationNumber)){
            throw new RuntimeException("Registration number already exists: " + newRegistrationNumber);
        }

        person.setRegistrationNumber(newRegistrationNumber);
        return person;
    }
}
