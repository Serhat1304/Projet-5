package com.safety.alerts.repository;

import com.safety.alerts.dto.PersonDTO;
import com.safety.alerts.model.Person;

import java.util.List;

public interface IPersonRepository extends ICrudRepository<Person> {

    Person getPerson(String firstName, String lastName);
    List<Person> getPersonsByAddress(String address);
    List<String > getEmailByCity(String city);
    List<Person> getPersonsByStation(Integer station);
    List<Person> getPersons(String firstName, String lastName);
    List<Person> getPersonByCity(String city);

}
