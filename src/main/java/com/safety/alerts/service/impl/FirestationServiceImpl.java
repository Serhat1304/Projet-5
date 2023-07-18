package com.safety.alerts.service.impl;

import com.safety.alerts.dto.FirestationDTO;
import com.safety.alerts.mapper.FirestationMapper;
import com.safety.alerts.model.Firestation;
import com.safety.alerts.repository.IFirestationRepository;
import com.safety.alerts.service.IFirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class FirestationServiceImpl implements IFirestationService {

    @Autowired
    private FirestationMapper firestationMapper;

    @Autowired
    private IFirestationRepository firestationRepository;



    @Override
    public List<FirestationDTO> getAll() {
        return firestationRepository.getAll().stream()
                .map(firestationMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public FirestationDTO getFirestationByAddress(String address) {
        Firestation firestation = firestationRepository.getFirestationByAddress(address);
        if (firestation != null) {
            return firestationMapper.map(firestation);
        }
        return null;
    }

    @Override
    public FirestationDTO addFirestation(FirestationDTO firestationDTO) {
        Firestation firestation = firestationMapper.map(firestationDTO);
        firestationRepository.addFirestation(firestation);
        return firestationMapper.map(firestation);
    }

    @Override
    public FirestationDTO updateFirestation(FirestationDTO firestationDTO) {
        Firestation firestation = firestationMapper.map(firestationDTO);
        Firestation newFirestation = firestationRepository.updateFirestation(firestation);
        if (firestation != null) {
            return firestationMapper.map(newFirestation);
        }
        return null;
    }


    @Override
    public void deleteFirestationByAddress(String address) {
        Firestation firestation = firestationRepository.getFirestationByAddress(address);
        if (firestation != null) {
            firestationRepository.deleteFirestation(firestation);
        }
    }
}
