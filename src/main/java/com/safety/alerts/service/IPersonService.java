package com.safety.alerts.service;


import com.safety.alerts.dto.PersonDTO;

import java.util.List;

public interface IPersonService {

    List<PersonDTO> getAll();
    PersonDTO getPersonByEmail(String email);
    PersonDTO addPerson(PersonDTO personDTO);
    PersonDTO updatePerson(PersonDTO personDTO);
    void deletePerson(PersonDTO personDTO);

}
