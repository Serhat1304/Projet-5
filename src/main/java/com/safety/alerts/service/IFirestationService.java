package com.safety.alerts.service;

import com.safety.alerts.dto.FirestationDTO;
import com.safety.alerts.model.Firestation;

import java.util.List;

public interface IFirestationService {

    List<FirestationDTO> getAll();

    FirestationDTO addFirestation(FirestationDTO firestationDTO);

    FirestationDTO updateFirestation(FirestationDTO firestationDTO);

    FirestationDTO getFirestationByAddress(String address);

    void deleteFirestationByAddress(String address);


}
