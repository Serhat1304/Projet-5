package com.safety.alerts.service;

import com.safety.alerts.model.Firestation;
import com.safety.alerts.repository.FirestationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class FirestationService {

    @Autowired
    private FirestationRepository firestationRepository;

    public FirestationService(FirestationRepository firestationRepository) {
        this.firestationRepository = firestationRepository;
    }

    public List<Firestation> getFirestation() {
        return firestationRepository.findAll();
    }

    public Firestation addFirestation(Firestation station) {
        return firestationRepository.addFireStation(station);
    }

    public Firestation updateFirestation(Firestation station, String address) {
        return firestationRepository.updateFireStation(station, address);
    }

    public void deleteFirestationByStation(Integer station) {
        firestationRepository.deleteByStation(station);
    }

    public void deleteFirestationByAddress(String address) {
        firestationRepository.deleteByAddress(address);
    }

    public List<Firestation> findFirestationByStations(List<Integer> stations) {
        return firestationRepository.findByStations(stations);
    }

    public List<Firestation> findFirestationByStation(Integer station) {
        return firestationRepository.findByStation(station);
    }


}
