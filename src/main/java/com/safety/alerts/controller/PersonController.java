package com.safety.alerts.controller;

import com.safety.alerts.dto.PersonDTO;
import com.safety.alerts.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private IPersonService personService;

    @GetMapping("/person")
    public List<PersonDTO> getPerson() {
        return personService.getAllPerson();
    }

    @PostMapping("/person")
    public PersonDTO savePerson(@RequestBody PersonDTO personDTO){
        return personService.savePerson(personDTO);
    }

    @PutMapping("/person")
    public PersonDTO updatePerson(@RequestBody PersonDTO personDTO) {
        return personService.updatePerson(personDTO);
    }

    @DeleteMapping("/person")
    public void deletePerson(@RequestBody PersonDTO personDTO) {
        personService.deletePerson(personDTO.getFirstName(), personDTO.getLastName());
    }
}
