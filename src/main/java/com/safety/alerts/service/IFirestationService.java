package com.safety.alerts.service;

import com.safety.alerts.model.Firestation;

import java.util.List;

public interface IFirestationService {

    List<Firestation> getAll();

    Firestation getFirestation(Integer station);

    Firestation addFirestation(Firestation firestation);

    Boolean deleteFirestation(Integer station);

    Firestation updateFirestation(Firestation firestation);

    int getFirestationByPersonAddress(String address);

}
