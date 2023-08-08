package com.safety.alerts.service.impl;

import com.safety.alerts.dto.FirestationDTO;
import com.safety.alerts.dto.MedicalRecordDTO;
import com.safety.alerts.dto.PersonDTO;
import com.safety.alerts.dto.specificDTOs.ChildAlertDTO;
import com.safety.alerts.dto.specificDTOs.FirestationCoverageDTO;
import com.safety.alerts.dto.specificDTOs.PersonInfoDTO;
import com.safety.alerts.mapper.FirestationMapper;
import com.safety.alerts.mapper.MedicalRecordMapper;
import com.safety.alerts.mapper.PersonMapper;
import com.safety.alerts.model.Firestation;
import com.safety.alerts.model.MedicalRecord;
import com.safety.alerts.model.Person;
import com.safety.alerts.repository.IFirestationRepository;
import com.safety.alerts.repository.IMedicalRecordRepository;
import com.safety.alerts.repository.IPersonRepository;
import com.safety.alerts.service.ISpecificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SpecificServiceImpl implements ISpecificService {

    @Autowired
    private IFirestationRepository firestationRepository;

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IMedicalRecordRepository medicalRecordRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private FirestationMapper firestationMapper;

    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    @Autowired
    private PersonServiceImpl personService;

    @Override
    public FirestationCoverageDTO getPersonCoveredByStation(Integer station) {
        Firestation firestation = firestationRepository.getFirestationByStation(station);

        if (firestation == null) {
            return null;
        }

        List<Person> personCovered = personRepository.getPersonsByAddress(firestation.getAddress());
        List<PersonDTO> personDTOS = new ArrayList<>();
        int adultsCount = 0;
        int childrenCount = 0;

        for (Person person : personCovered) {
            MedicalRecord medicalRecord = medicalRecordRepository.getMedicalRecord(person.getFirstName(),person.getLastName());
            if (medicalRecord != null) {
                MedicalRecordDTO medicalRecordDTO = medicalRecordMapper.map(medicalRecord);
                Integer age = medicalRecordDTO.getAge();

                PersonDTO personDTO = personMapper.map(person);
                personDTO.setAge(age);

                personDTOS.add(personDTO);

                if (age <= 18) {
                    childrenCount++;
                }else {
                    adultsCount++;
                }
            }
        }
        FirestationCoverageDTO coverageDTO = new FirestationCoverageDTO();
        coverageDTO.setPersonDTOS(personDTOS);
        coverageDTO.setAdultsCount(adultsCount);
        coverageDTO.setChildrenCount(childrenCount);
        return coverageDTO;
    }

    @Override
    public List<ChildAlertDTO> getChildrenByAddress(String address) {
        List<Person> personByAddress = personRepository.getPersonsByAddress(address);
        List<ChildAlertDTO> childAlertDTOList = new ArrayList<>();

        for (Person person : personByAddress) {
            MedicalRecordDTO medicalRecordDTO = medicalRecordMapper.map(medicalRecordRepository.getMedicalRecord(person.getFirstName(),person.getLastName()));
            if (medicalRecordDTO != null && medicalRecordDTO.getAge() <= 18) {
                ChildAlertDTO childAlertDTO = new ChildAlertDTO();
                childAlertDTO.setFirstName(person.getFirstName());
                childAlertDTO.setLastName(person.getLastName());
                childAlertDTO.setAge(medicalRecordDTO.getAge());

                List<String> otherFamilyMembers = personByAddress.stream()
                        .filter(person1 -> !person1.equals(person))
                        .map(person1 -> person1.getFirstName() + " " + person1.getLastName())
                        .collect(Collectors.toList());

                childAlertDTO.setOtherHouseMembers(otherFamilyMembers);
                childAlertDTOList.add(childAlertDTO);
            }
        }
        return childAlertDTOList;
    }

    @Override
    public List<String> getPhoneNumbersByStation(Integer station) {
        List<Person> personByStation = personService.getPersonsByStation(station);
        List<String> phoneNumbers = new ArrayList<>();

        for (Person person : personByStation) {
            List<Person> personsByAddress = personService.getPersonsByAddress(person.getAddress());

            for (Person person1 : personsByAddress) {
                phoneNumbers.add(person1.getPhone());
            }
        }
        return phoneNumbers;
    }

    @Override
    public List<PersonInfoDTO> getPersonInfo(String firstName, String lastName) {
        List<Person> persons = personRepository.getPersons(firstName, lastName);
        List<PersonInfoDTO> personInfoDTOS = new ArrayList<>();

        for (Person person : persons) {
            MedicalRecord medicalRecord = medicalRecordRepository.getMedicalRecord(person.getFirstName(), person.getLastName());
            if (medicalRecord != null){
                MedicalRecordDTO medicalRecordDTO = medicalRecordMapper.map(medicalRecord);

                PersonInfoDTO personInfoDTO = new PersonInfoDTO();
                personInfoDTO.setLastName(person.getLastName());
                personInfoDTO.setFirstName(person.getFirstName());
                personInfoDTO.setAge(medicalRecordDTO.getAge());
                personInfoDTO.setMail(person.getEmail());
                personInfoDTO.setAddress(person.getAddress());
                personInfoDTO.setMedicalRecordDTO(medicalRecordDTO);

                personInfoDTOS.add(personInfoDTO);
            }
        }
        return personInfoDTOS;
    }

    @Override
    public List<String> getCommunityEmailByCity(String city) {
        List<Person> personsByCity = personRepository.getPersonByCity(city);
        return personsByCity.stream()
                .map(Person::getEmail)
                .collect(Collectors.toList());
    }


}
