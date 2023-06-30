package com.safety.alerts.service;

import com.safety.alerts.dto.FirestationDTO;
import com.safety.alerts.model.Firestation;

import java.util.List;

public interface IFirestationService {

    List<FirestationDTO> getAll();

    FirestationDTO getFirestationByAddress(String address);

    FirestationDTO addFirestation(FirestationDTO firestationDTO);

    FirestationDTO updateFirestation(String address, Integer station);

    void deleteFirestation(String address, Integer station);


}
