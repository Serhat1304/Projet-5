package com.safety.alerts.repository;

import com.safety.alerts.model.Firestation;

public interface IFirestationRepository extends ICrudRepository<Firestation> {

    Firestation getFirestationByAddress(String address);

}
