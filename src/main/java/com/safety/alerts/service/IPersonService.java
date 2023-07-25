package com.safety.alerts.service;


import com.safety.alerts.dto.PersonDTO;

import java.util.List;

public interface IPersonService {

    List<PersonDTO> getAllPerson();
    PersonDTO getPerson(String firstName, String lastName);
    PersonDTO savePerson(PersonDTO personDTO);
    PersonDTO updatePerson(PersonDTO personDTO);
    void deletePerson(String firstName, String lastName);

}
