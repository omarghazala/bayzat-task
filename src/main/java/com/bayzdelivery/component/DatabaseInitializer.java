package com.bayzdelivery.component;

import com.bayzdelivery.model.PersonType;
import com.bayzdelivery.repositories.PersonTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    PersonTypeRepository personTypeRepository;
    @Override
    public void run(String... args) throws Exception {
        if (personTypeRepository.findByName("CUSTOMER") == null) {
            PersonType customer = new PersonType();
            customer.setName("CUSTOMER");
            personTypeRepository.save(customer);
        }

        if (personTypeRepository.findByName("DELIVERY_MAN") == null) {
            PersonType deliveryMan = new PersonType();
            deliveryMan.setName("DELIVERY_MAN");
            personTypeRepository.save(deliveryMan);
        }
    }
}
