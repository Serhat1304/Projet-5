package com.safety.alerts.mapper;

import com.safety.alerts.dto.PersonDTO;
import com.safety.alerts.model.Person;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public PersonMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PersonDTO map(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    public Person map(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }
}
