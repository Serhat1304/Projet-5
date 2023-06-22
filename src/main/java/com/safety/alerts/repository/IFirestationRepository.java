package com.safety.alerts.repository;

import com.safety.alerts.model.Firestation;

import java.util.List;

public interface IFirestationRepository {

    List<Firestation> getAll();

    Firestation getFirestation(Integer station);

    Firestation addFirestation(Firestation firestation);

    Firestation deleteFirestation(Firestation firestation);

    Firestation updateFirestation(Firestation firestation);

    Firestation getFirestationByAddress(String address);

}
