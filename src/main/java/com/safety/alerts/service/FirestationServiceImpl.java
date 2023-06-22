package com.safety.alerts.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.alerts.mapper.JSONMapperImpl;
import com.safety.alerts.model.Firestation;
import com.safety.alerts.model.Person;
import com.safety.alerts.repository.FirestationRepositoryImpl;
import com.safety.alerts.repository.MedicalRecordRepositoryImpl;
import com.safety.alerts.repository.PersonRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FirestationServiceImpl implements IFirestationService{

    private static final Logger logger = LoggerFactory.getLogger(FirestationServiceImpl.class);

    private JSONMapperImpl jsonMapperImpl;
    private FirestationRepositoryImpl firestationRepository;
    private MedicalRecordRepositoryImpl medicalRecordRepository;
    private PersonRepositoryImpl personRepository;
    private ArrayList<Firestation> firestations;

    public FirestationServiceImpl() throws IOException{
        this.jsonMapperImpl = new JSONMapperImpl(new ObjectMapper());
        this.firestationRepository = new FirestationRepositoryImpl(new ArrayList<>(this.jsonMapperImpl.getResponse().getFirestations()));
        this.personRepository = new PersonRepositoryImpl(new ArrayList<>(this.jsonMapperImpl.getResponse().getPersons()));
        this.medicalRecordRepository = new MedicalRecordRepositoryImpl(new ArrayList<>(this.jsonMapperImpl.getResponse().getMedicalRecords()));
    }

    @Override
    public List<Firestation> getAll() {
        return this.firestationRepository.getAll();
    }

    @Override
    public Firestation getFirestation(Integer station) {
        return this.firestationRepository.getFirestation(station);
    }

    @Override
    public Firestation addFirestation(Firestation firestation) {
        if(getFirestation(firestation.getStation()) !=null) {
            logger.error("Firestation is already exist");
        }
        return this.firestationRepository.addFirestation(firestation);
    }

    @Override
    public Boolean deleteFirestation(Integer station) {
        Firestation firestation = getFirestation(station);
        if(firestation == null) {
            return false;
        }
        this.firestationRepository.deleteFirestation(firestation);
        return true;
    }

    @Override
    public Firestation updateFirestation(Firestation firestation) {
        if(getFirestation(firestation.getStation()) != null) {
            logger.info("Request updating firestation is successful");
            return this.firestationRepository.updateFirestation(firestation);
        }logger.error("Firestation not found");
        return null;
    }

    @Override
    public int getFirestationByPersonAddress(String address) {
        List<Person> persons = this.personRepository.getPersonsByAddress(address);

        for(Person person : persons){
            Firestation firestation = this.firestationRepository.getFirestationByAddress(address);
            if(Objects.equals(person.getAddress(),firestation.getAddress())){
                logger.info("Getting station number by address is successful");
                return firestation.getStation();
            }
        }
        logger.info("Getting station number by address is successful");
        return 0;
    }

}
