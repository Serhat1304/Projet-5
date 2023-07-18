package com.safety.alerts.repository.impl;

import com.safety.alerts.model.Person;
import com.safety.alerts.repository.IPersonRepository;
import org.springframework.stereotype.Repository;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository

public class PersonRepositoryImpl implements IPersonRepository {

    private List<Person> persons;

    public PersonRepositoryImpl(List<Person> persons) {
        this.persons = new ArrayList<>(persons);
    }

    @Override
    public List<Person> getAll() {
        return new ArrayList<>(persons);
    }

    @Override
    public Person getPerson(String email) {
        return persons.stream()
                .filter(person -> Objects.equals(person.getEmail(), email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Person addPerson(Person person) {
        persons.add(person);
        return person;
    }

    @Override
    public void deletePerson(Person person) {
        persons.remove(person);
    }

    @Override
    public Person updatePerson(Person person) {
        persons.set(persons.indexOf(person),person);
        Logger.info("Updating person is successful");
        return person;
    }

    @Override
    public List<Person> getPersonsByAddress(String address) {
        return persons.stream()
                .filter(person -> Objects.equals(person.getAddress(), address))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getEmailByCity(String city) {
        return persons.stream()
                .filter(person -> person.getCity().equalsIgnoreCase(city))
                .map(Person::getEmail)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> getPersonByFirstAndLastName(String firstName, String lastName) {
        return persons.stream()
                .filter(person -> Objects.equals(person.getFirstName(), firstName) && Objects.equals(person.getLastName(), lastName))
                .collect(Collectors.toList());
    }
}
