package com.safety.alerts.repository;

import com.safety.alerts.model.Person;

import java.util.List;

public interface IPersonRepository {

    List<Person> getAll();

    Person getPerson(String email);

    Person addPerson(Person person);

    Person deletePerson(Person person);

    Person updatePerson(Person person);

    List<Person> getPersonsByAddress(String address);

    List<String > getEmailByCity(String city);

    List<Person> getPersonByFirstAndLastName(String FirstName, String LastName);

}
