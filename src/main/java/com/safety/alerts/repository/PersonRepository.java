package com.safety.alerts.repository;

import com.safety.alerts.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonRepository   {

    private final List<Person> personList = new ArrayList<>();

    public List<Person> findAll(){
        return this.personList;
    }

    public Person addPerson(Person person){
        this.personList.add(person);
        return person;
    }

    public Person updatePerson(Person person, String firstName, String lastName) throws IndexOutOfBoundsException{

        Person newPerson = findByFirstAndLastName(firstName, lastName);
        newPerson.setAddress(person.getAddress());
        newPerson.setCity(person.getCity());
        newPerson.setEmail(person.getEmail());
        newPerson.setPhone(person.getPhone());

        return personList.set(personList.indexOf(findByFirstAndLastName(firstName, lastName)),newPerson);

    }

    public Person findByFirstAndLastName(String firstName, String lastName) {
        return this.personList.stream()
                .filter(person -> (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)))
                .findAny().orElseThrow();

    }

    public void deleteByFirstAndLastName(String firstName, String lastName) {
        this.personList.removeIf(person ->
                person.getFirstName().equals(firstName) && person.getLastName().equals(lastName));
    }
    public List<Person> findByLastName(String lastName){
        return this.personList.stream()
                .filter((person -> person.getLastName().equals(lastName)))
                .collect(Collectors.toList());
    }

    public List<Person> findByCity(String city){
        return this.personList.stream()
                .filter(person -> person.getCity().equals(city))
                .collect(Collectors.toList());
    }

    public List<Person> findByAddress(String address){
        return this.personList.stream()
                .filter(person -> person.getAddress().equals(address))
                .collect(Collectors.toList());
    }

}
