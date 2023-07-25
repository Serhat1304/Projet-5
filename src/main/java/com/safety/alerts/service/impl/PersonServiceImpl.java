package com.safety.alerts.service.impl;

import com.safety.alerts.dto.PersonDTO;
import com.safety.alerts.mapper.PersonMapper;
import com.safety.alerts.model.Person;
import com.safety.alerts.repository.IPersonRepository;
import com.safety.alerts.service.IPersonService;
import com.safety.alerts.util.DataHolder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private IPersonRepository personRepository;


    @Override
    public List<PersonDTO> getAllPerson() {
        Logger.info("Getting all person is successful");
        return personRepository.getAll().stream()
                .map(personMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO getPerson(String firstName, String lastName) {
        Person person = personRepository.getPerson(firstName, lastName);
        if (person != null) {
            Logger.info("Getting person info by name {} is successful ", firstName, lastName);
            return personMapper.map(person);
        }Logger.error("Getting person info by name : {} failed", firstName, lastName);
        return null;
    }

    @Override
    public PersonDTO savePerson(PersonDTO personDTO) {
        Person person = personMapper.map(personDTO);
        personRepository.save(person);
        Logger.info("Save person is successful");
        return personMapper.map(person);
    }

    @Override
    public PersonDTO updatePerson(PersonDTO personDTO) {
        Person person = personMapper.map(personDTO);
        Person newPerson = personRepository.update(person);
        if (newPerson != null) {
            Logger.info("Update person is successful");
            return personMapper.map(newPerson);
        }Logger.error("Update person failed");
        return null;
    }

    @Override
    public void deletePerson(String firstName, String lastName) {
        Person person = personRepository.getPerson(firstName, lastName);
        if (person != null) {
            personRepository.delete(person);
            Logger.info("Delete person is successful");
        }Logger.error("Delete person failed");
    }
}
