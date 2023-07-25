package com.safety.alerts.service.impl;

import com.safety.alerts.dto.FirestationDTO;
import com.safety.alerts.mapper.FirestationMapper;
import com.safety.alerts.model.Firestation;
import com.safety.alerts.repository.IFirestationRepository;
import com.safety.alerts.service.IFirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FirestationServiceImpl implements IFirestationService {

    @Autowired
    private FirestationMapper firestationMapper;

    @Autowired
    private IFirestationRepository firestationRepository;



    @Override
    public List<FirestationDTO> getAllFirestation() {
        Logger.info("Getting all firestation successful");
        return firestationRepository.getAll().stream()
                .map(firestationMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public FirestationDTO getFirestationByAddress(String address) {
        Firestation firestation = firestationRepository.getFirestationByAddress(address);
        if (firestation != null) {
            Logger.info("Getting firestation by address :  {} is successful", address);
            return firestationMapper.map(firestation);
        }Logger.error("Getting firestation by address : {} failed", address);
        return null;
    }

    @Override
    public FirestationDTO saveFirestation(FirestationDTO firestationDTO) {
        Firestation firestation = firestationMapper.map(firestationDTO);
        firestationRepository.save(firestation);
        Logger.info("Saving firestation is successful");
        return firestationMapper.map(firestation);
    }

    @Override
    public FirestationDTO updateFirestation(FirestationDTO firestationDTO) {
        Firestation firestation = firestationMapper.map(firestationDTO);
        Firestation newFirestation = firestationRepository.update(firestation);
        if (newFirestation != null) {
            Logger.info("Updating firestation is successful");
            return firestationMapper.map(newFirestation);
        }Logger.error("Updating firestation failed");
        return null;
    }


    @Override
    public void deleteFirestationByAddress(String address) {
        Firestation firestation = firestationRepository.getFirestationByAddress(address);
        if (firestation != null) {
            firestationRepository.delete(firestation);
        }Logger.info("Delete firestation by address : {} is successful", address);
    }
}
