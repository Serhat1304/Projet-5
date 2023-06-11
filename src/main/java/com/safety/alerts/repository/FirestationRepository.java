package com.safety.alerts.repository;

import com.safety.alerts.model.Firestation;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class FirestationRepository {

    private final List<Firestation> firestationList = new ArrayList<>();

    public List<Firestation> findAll(){
        return this.firestationList;
    }

    public Firestation addFireStation(Firestation firestation){
        this.firestationList.add(firestation);
        return firestation;
    }

    public Firestation updateFireStation(Firestation firestation, String address) {

        Firestation newFirestation = findStationByAddress(address);
        newFirestation.setStation(newFirestation.getStation());

        return firestationList.set(firestationList.indexOf(findStationByAddress(address)), newFirestation);
    }

    public void deleteByAddress(String address) {
        this.firestationList.removeIf(firestation -> firestation.getAddress().equals(address));
    }

    public void deleteByStation(Integer station) {
        this.firestationList.removeIf(firestation -> firestation.getStation().equals(station));
    }

    public List<Firestation> findByStations(List<Integer> stations) {
        return this.firestationList.stream()
                .filter(firestation -> stations.contains(firestation.getStation()))
                .collect(Collectors.toList());
    }

    public List<Firestation> findByStation(Integer station) {
        return this.firestationList.stream()
                .filter(firestation -> station.equals(firestation.getStation()))
                .collect(Collectors.toList());
    }


    private Firestation findStationByAddress(String address) {
        return this.firestationList.stream().filter(firestation -> (firestation.getAddress()
                .equals(address))).findAny().orElseThrow();
    }
}
