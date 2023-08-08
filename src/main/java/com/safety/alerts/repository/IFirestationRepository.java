package com.safety.alerts.repository;

import com.safety.alerts.model.Firestation;

import java.util.List;

public interface IFirestationRepository extends ICrudRepository<Firestation> {

    Firestation getFirestationByAddress(String address);

    List<String> getAddressByStation(Integer station);

    Firestation getFirestationByStation(Integer station);
}
