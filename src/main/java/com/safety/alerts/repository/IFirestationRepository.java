package com.safety.alerts.repository;

import com.safety.alerts.model.Firestation;

import java.util.List;

public interface IFirestationRepository {

    List<Firestation> getAll();

    Firestation getFirestationByAddress(String address);

    Firestation addFirestation(Firestation firestation);

    void  deleteFirestation(Firestation firestation);

    Firestation updateFirestation(Firestation firestation);


}
