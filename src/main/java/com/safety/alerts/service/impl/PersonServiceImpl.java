package com.safety.alerts.service.impl;

import com.safety.alerts.dto.PersonDTO;
import com.safety.alerts.mapper.PersonMapper;
import com.safety.alerts.model.Person;
import com.safety.alerts.service.IPersonService;
import com.safety.alerts.util.DataHolder;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PersonServiceImpl implements IPersonService {

    private final PersonMapper personMapper;
    private final DataHolder dataHolder;

    public PersonServiceImpl(PersonMapper personMapper, DataHolder dataHolder) {
        this.personMapper = personMapper;
        this.dataHolder = dataHolder;
    }

    @Override
    public List<PersonDTO> getAll() {
        List<Person> persons = dataHolder.getResponse().getPersons();
        return persons.stream()
                .map(personMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO getPersonByEmail(String email) {
        List<Person> persons = dataHolder.getResponse().getPersons();
        return persons.stream()
                .filter(person -> person.getEmail().equals(email))
                .map(personMapper::map)
                .findFirst()
                .orElse(null);
    }

    @Override
    public PersonDTO addPerson(PersonDTO personDTO) {
        Person person = personMapper.map(personDTO);
        dataHolder.getResponse().getPersons().add(person);
        return personMapper.map(person);
    }

    @Override
    public PersonDTO updatePerson(PersonDTO personDTO) {
        List<Person> persons = dataHolder.getResponse().getPersons();
        Person newPerson = persons.stream()
                .filter(person -> person.getFirstName().equals(personDTO.getFirstName()) &&
                        person.getLastName().equals(personDTO.getLastName()))
                .findFirst()
                .orElse(null);
        if (newPerson != null) {
            newPerson.setAddress(personDTO.getAddress());
            newPerson.setCity(personDTO.getCity());
            newPerson.setEmail(personDTO.getEmail());
            newPerson.setZip(personDTO.getZip());
            newPerson.setPhone(personDTO.getPhone());
        }
        return personMapper.map(newPerson);
    }

    @Override
    public void deletePerson(PersonDTO personDTO) {
        List<Person> persons = dataHolder.getResponse().getPersons();
        persons.removeIf(person -> person.getFirstName().equals(personDTO.getFirstName()) && person.getLastName().equals(personDTO.getLastName()));
    }
}
