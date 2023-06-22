package com.safety.alerts.repository;

import com.safety.alerts.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PersonRepositoryImpl implements IPersonRepository {

    private final static Logger logger = LoggerFactory.getLogger(PersonRepositoryImpl.class);

    private ArrayList<Person> persons;

    public PersonRepositoryImpl(ArrayList<Person> persons) {
        if(this.persons == null){
            this.persons = new ArrayList<>();
        }
        this.persons.addAll(persons);
    }

    @Override
    public List<Person> getAll() {
        return this.persons;
    }

    @Override
    public Person getPerson(String email) {
        for(Person person : this.persons) {
            if(Objects.equals(person.getEmail(), email)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Person addPerson(Person person) {
        this.persons.add(person);
        return person;
    }

    @Override
    public Person deletePerson(Person person) {
        this.persons.remove(person);
        return person;
    }

    @Override
    public Person updatePerson(Person person) {
        this.persons.set(getAll().indexOf(getPerson(person.getEmail())), person);
        return person;
    }

    @Override
    public List<Person> getPersonsByAddress(String address) {

        List<Person> personsByAddress = new ArrayList<>();

        for(Person person : this.persons) {
            if(Objects.equals(person.getAddress(), address)) {
                logger.info("Request getting persons by address successful");
                personsByAddress.add(person);
            }

            }
        if(personsByAddress.isEmpty()) {
            logger.info("Request getting persons failed");
        }
        return personsByAddress;

    }

    @Override
    public List<String> getEmailByCity(String city) {
        List<String> emailByCityList = new ArrayList<>();

        for(Person person : getAll()) {
            if(person.getCity().compareTo(city) == 0) {
                logger.info("Request getting email by city is successful");
                emailByCityList.add(person.getEmail());
            }
        }
        if (emailByCityList.isEmpty()) {
            logger.info("Request getting email failed");
        }
        return emailByCityList;
    }

    @Override
    public List<Person> getPersonByFirstAndLastName(String FirstName, String LastName) {
        List<Person> personByFirstAndLastNameList = new ArrayList<>();

        for(Person person : this.persons) {
            if(person.getFirstName() != null && person.equals(FirstName) && person.getLastName() != null && person.equals(LastName)) {
                logger.info("Request getting person by first and last name is successful");
            }
        }
        if(personByFirstAndLastNameList.isEmpty()) {
            logger.info("Request getting by first and last name failed");
        }
        return personByFirstAndLastNameList;
    }
}
