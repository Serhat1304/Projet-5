package com.safety.alerts.repository.impl;

import com.safety.alerts.model.Person;
import com.safety.alerts.repository.IPersonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepositoryImpl implements IPersonRepository {

    private List<Person> persons;


    @Override
    public void saveAll(List<Person> personList) {
        this.persons = personList;
    }

    @Override
    public List<Person> getAll() {
        return persons;
    }

    @Override
    public Person getPerson(String firstName, String lastname) {
        Optional<Person> optionalPerson = persons.stream()
                .filter(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastname))
                .findFirst();
        return optionalPerson.orElse(null);
    }

    @Override
    public Person save(Person person) {
        persons.add(person);
        return person;
    }

    @Override
    public boolean delete(Person person) {
        return persons.remove(person);
    }

    @Override
    public Person update(Person person) {
        Optional<Person> optionalPerson = persons.stream()
                .filter(person1 -> person1.getFirstName().equals(person.getFirstName())
                && person1.getLastName().equals(person.getLastName()))
                .findFirst();
        if (optionalPerson.isPresent()) {
            Person person1 = optionalPerson.get();
            person1.setPhone(person.getPhone());
            person1.setCity(person.getCity());
            person1.setZip(person.getZip());
            person1.setEmail(person.getEmail());
            person1.setAddress(person.getAddress());

            return person1;
        }
        return null;
    }

    /*@Override
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
    }*/
}
