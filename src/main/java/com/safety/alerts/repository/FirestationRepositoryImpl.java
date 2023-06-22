package com.safety.alerts.repository;

import com.safety.alerts.model.Firestation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FirestationRepositoryImpl implements IFirestationRepository {

    private static final Logger logger = LoggerFactory.getLogger(FirestationRepositoryImpl.class);

    private ArrayList<Firestation> firestations;

    public FirestationRepositoryImpl(ArrayList<Firestation> firestations) {
        if(this.firestations == null){
            this.firestations = new ArrayList<>();
        }
        this.firestations.addAll(firestations);
    }

    @Override
    public List<Firestation> getAll() {
        return this.getAll();
    }

    @Override
    public Firestation getFirestation(Integer station) {
        for (Firestation firestation : this.firestations) {
            if(Objects.equals(firestation.getStation(),station)) {
                return firestation;
            }
        }
        return null;
    }

    @Override
    public Firestation addFirestation(Firestation firestation) {
        this.firestations.add(firestation);
        return firestation;
    }

    @Override
    public Firestation deleteFirestation(Firestation firestation) {
        this.firestations.remove(firestation);
        return firestation;
    }

    @Override
    public Firestation updateFirestation(Firestation firestation) {
        this.firestations.set(getAll().indexOf(getFirestation(firestation.getStation())), firestation);
        return firestation;
    }

    @Override
    public Firestation getFirestationByAddress(String address) {
        for(Firestation firestation : this.firestations) {
            if(Objects.equals(firestation.getAddress(), address)) {
                logger.info("Request getting firestation by address successful ");
                return firestation;
            }
        }
        logger.info("Request getting firestation by adsress failed");
        return null;
    }

}