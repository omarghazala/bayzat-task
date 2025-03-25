package com.bayzdelivery.repositories;

import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.PersonType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported=false)
public interface PersonTypeRepository extends CrudRepository<PersonType, Long> {

    Object findByName(String person);
}
