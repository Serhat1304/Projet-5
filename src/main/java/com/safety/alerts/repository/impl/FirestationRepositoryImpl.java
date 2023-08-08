package com.safety.alerts.repository.impl;

import com.safety.alerts.model.Firestation;
import com.safety.alerts.repository.IFirestationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FirestationRepositoryImpl implements IFirestationRepository {

    private List<Firestation> firestations;

    @Override
    public void saveAll(List<Firestation> firestationList) {
        this.firestations = firestationList;
    }

    @Override
    public List<Firestation> getAll() {
        return firestations;
    }

    @Override
    public boolean delete(Firestation firestation) {
        firestations.remove(firestation);
        return true;
    }

    @Override
    public Firestation getFirestationByAddress(String address) {
        Optional<Firestation> optionalFirestation = firestations.stream()
                .filter(firestation -> firestation.getAddress().equals(address))
                .findFirst();
        return optionalFirestation.orElse(null);
    }

    @Override
    public List<String> getAddressByStation(Integer station) {
        return firestations.stream()
                .filter(firestation -> firestation.getStation().equals(station))
                .map(Firestation::getAddress)
                .collect(Collectors.toList());
    }

    @Override
    public Firestation getFirestationByStation(Integer station) {
            Optional<Firestation> optionalFirestation = firestations.stream()
                    .filter(firestation -> firestation.getStation().equals(station))
                    .findFirst();
            return optionalFirestation.orElse(null);
        }

    @Override
    public Firestation save(Firestation firestation) {
        firestations.add(firestation);
        return firestation;
    }

    @Override
    public Firestation update(Firestation firestation) {
        Firestation newFirestation = getFirestationByAddress(firestation.getAddress());
        if (newFirestation != null) {
            newFirestation.setStation(firestation.getStation());
            return newFirestation;
        }
        return null;
    }



}