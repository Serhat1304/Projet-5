package com.safety.alerts.service;

import com.safety.alerts.model.Person;
import com.safety.alerts.repository.PersonRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.font.ShapeGraphicAttribute;
import java.util.List;

@Service
@Data
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPerson() {
        return personRepository.findAll();
    }

    public Person addPerson(Person person) {
        return personRepository.addPerson(person);
    }

    public Person updatePerson(Person person, String firstName, String lastname) {
        return personRepository.updatePerson(person, firstName, lastname);
    }

    public void deletePerson(Person person, String firstName, String lastName) {
        personRepository.deleteByFirstAndLastName(firstName, lastName);
    }

    public List<Person> findPersonByLastName(String lastName) {
        return personRepository.findByLastName(lastName);
    }

    public List<Person> findByCity(String city) {
        return personRepository.findByCity(city);
    }

    public List<Person> findByAddress(String address) {
        return personRepository.findByAddress(address);
    }
}
