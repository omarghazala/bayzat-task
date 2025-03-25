package com.bayzdelivery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bayzdelivery.dto.PersonDto;
import com.bayzdelivery.mapper.PersonMapper;
import com.bayzdelivery.repositories.PersonRepository;
import com.bayzdelivery.model.Person;
import com.bayzdelivery.repositories.PersonTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonTypeRepository personTypeRepository;

    @Override
    public List<Person> getAll() {
        List<Person> personList = new ArrayList<>();
        personRepository.findAll().forEach(personList::add);
        return personList;
    }

    public Person save(PersonDto personDto) {
        return personRepository.save(PersonMapper.mapPersonDtoToPerson(personDto,personRepository,personTypeRepository));
    }

    @Override
    public Person findById(Long personId) {
        Optional<Person> dbPerson = personRepository.findById(personId);
        return dbPerson.orElse(null);
    }
}
