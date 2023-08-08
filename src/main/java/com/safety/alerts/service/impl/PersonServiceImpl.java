package com.safety.alerts.service.impl;

import com.safety.alerts.dto.PersonDTO;
import com.safety.alerts.mapper.PersonMapper;
import com.safety.alerts.model.Person;
import com.safety.alerts.repository.IFirestationRepository;
import com.safety.alerts.repository.IPersonRepository;
import com.safety.alerts.service.IPersonService;
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

    @Autowired
    private IFirestationRepository firestationRepository;

    private List<Person> persons;

    public PersonServiceImpl(List<Person> persons) {
        this.persons = persons;
    }


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

    @Override
    public List<Person> getPersonsByAddress(String address) {
        return persons.stream()
                .filter(person -> person.getAddress().equals(address))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> getPersonsByStation(Integer station) {
        List<String> addresses = firestationRepository.getAddressByStation(station);

        return persons.stream()
                .filter(person -> addresses.contains(person.getAddress()))
                .collect(Collectors.toList());
    }
}
