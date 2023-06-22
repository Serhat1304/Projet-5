package com.safety.alerts.service;


import com.safety.alerts.model.Person;

import java.util.List;

public interface IPersonService {

    List<Person> getall();
    Person getPerson(String email);
    Person addPerson(Person person);
    Person updatePerson(Person person);
    Boolean deletePerson(String firstName, String lastName);
    List<Person> getPersonByAddress(String address);
    List<String> getEmailByCity(String city);

}
