package com.safety.alerts.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.alerts.dto.FirestationDTO;
import com.safety.alerts.mapper.FirestationMapper;
import com.safety.alerts.model.Firestation;
import com.safety.alerts.model.Person;
import com.safety.alerts.repository.FirestationRepositoryImpl;
import com.safety.alerts.repository.MedicalRecordRepositoryImpl;
import com.safety.alerts.repository.PersonRepositoryImpl;
import com.safety.alerts.util.DataHolder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Log4j2
public class FirestationServiceImpl implements IFirestationService{

    private final FirestationMapper firestationMapper;
    private final DataHolder dataHolder;

    public FirestationServiceImpl(FirestationMapper firestationMapper, DataHolder dataHolder) {
        this.firestationMapper = firestationMapper;
        this.dataHolder = dataHolder;
    }


    @Override
    public List<FirestationDTO> getAll() {
        List<Firestation> firestations = dataHolder.getResponse().getFirestations();
        return firestations.stream()
                .map(firestationMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public FirestationDTO getFirestationByAddress(String address) {
        List<Firestation> firestations = dataHolder.getResponse().getFirestations();
        return firestations.stream()
                .filter(firestation -> firestation.getAddress().equals(address))
                .map(firestationMapper::map)
                .findFirst()
                .orElse(null);
    }

    @Override
    public FirestationDTO addFirestation(FirestationDTO firestationDTO) {
        Firestation firestation = firestationMapper.map(firestationDTO);
        List<Firestation> firestations = dataHolder.getResponse().getFirestations();
        firestations.add(firestation);
        return firestationMapper.map(firestation);
    }

    @Override
    public FirestationDTO updateFirestation(String address, Integer station) {
        List<Firestation> firestations = dataHolder.getResponse().getFirestations();
        for (Firestation firestation : firestations) {
            if (firestation.getAddress().equals(address)) {
                firestation.setStation(station);
                return firestationMapper.map(firestation);
            }
        }
        return null;
    }

    @Override
    public void deleteFirestation(String address, Integer station) {
        List<Firestation> firestations = dataHolder.getResponse().getFirestations();
        firestations.removeIf(firestation ->
                firestation.getAddress().equals(address) && firestation.getStation().equals(station));
    }
}
