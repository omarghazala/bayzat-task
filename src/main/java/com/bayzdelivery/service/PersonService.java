package com.bayzdelivery.service;

import java.util.List;

import com.bayzdelivery.dto.PersonDto;
import com.bayzdelivery.model.Person;

public interface PersonService {
  public List<Person> getAll();

  public Person save(PersonDto p);

  public Person findById(Long personId);

}
