package com.safety.alerts.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.alerts.model.Response;
import com.safety.alerts.repository.IFirestationRepository;
import com.safety.alerts.repository.IMedicalRecordRepository;
import com.safety.alerts.repository.IPersonRepository;
import com.safety.alerts.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;


@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DataHolder dataHolder;

    @Autowired
    private IFirestationRepository firestationRepository;

    @Autowired
    private IMedicalRecordRepository medicalRecordRepository;

    @Autowired
    private IPersonRepository personRepository;


    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        try {
            Response response = objectMapper.readValue(new File("src/main/resources/data.json"), Response.class);
            dataHolder.setResponse(response);

            firestationRepository.saveAll(response.getFirestations());
            medicalRecordRepository.saveAll(response.getMedicalrecords());
            personRepository.saveAll(response.getPersons());

            Logger.info("Load Data successful");
        }catch (IOException e) {
            Logger.error("Load Data failed", e);
        }
    }
}
