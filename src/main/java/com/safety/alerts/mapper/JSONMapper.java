package com.safety.alerts.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.alerts.model.Firestation;
import com.safety.alerts.model.MedicalRecord;
import com.safety.alerts.model.Person;
import com.safety.alerts.repository.FirestationRepository;
import com.safety.alerts.repository.MedicalRecordRepository;
import com.safety.alerts.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.io.IOException;

public class JSONMapper {

    public JSONMapper() {}

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private FirestationRepository firestationRepository;

    @Value("${dataSourceJson}")
    private String filePath;

    @PostConstruct
    public void loadData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new FileInputStream(filePath));
        loadPersons(node);
        loadFireStations(node);
        loadMedicalRecords(node);
    }

    public void loadPersons(JsonNode node) {
        JsonNode persons = node.path("persons");

        for (JsonNode nodePerson : persons) {

            Person person = new Person();
            person.setFirstName(nodePerson.path("firstName").asText());
            person.setLastName(nodePerson.path("lastName").asText());
            person.setPhone(nodePerson.path("phone").asInt());
            person.setAddress(nodePerson.path("adress").asText());
            person.setCity(nodePerson.path("city").asText());
            person.setEmail(nodePerson.path("email").asText());
            person.setZip(nodePerson.path("zip").asInt());

            personRepository.addPerson(person);
        }
    }

    public void loadFireStations(JsonNode node) {
        JsonNode fireStations = node.path("firestations");

        for (JsonNode nodeFireStation : fireStations) {

            Firestation firestation = new Firestation();
            firestation.setAddress(nodeFireStation.path("adress").asText());
            firestation.setStation(nodeFireStation.path("station").asInt());

            firestationRepository.addFireStation(firestation);
        }
    }

    public void loadMedicalRecords(JsonNode node) {
        JsonNode medicalRecords = node.path("medicalRecords");

        for (JsonNode nodeMedicalRecord : medicalRecords) {

            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setFirstName(nodeMedicalRecord.path("firstName").asText());
            medicalRecord.setLastName(nodeMedicalRecord.path("lastName").asText());
            medicalRecord.setBirthDate(nodeMedicalRecord.path("birthdate").asText());
            medicalRecord.setMedications(nodeMedicalRecord.path("medications").asText());
            medicalRecord.setAllergies(nodeMedicalRecord.path("allergies").asText());

            medicalRecordRepository.addMedicalRecord(medicalRecord);
        }
    }

}
