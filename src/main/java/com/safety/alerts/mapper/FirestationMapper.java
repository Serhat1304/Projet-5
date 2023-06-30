package com.safety.alerts.mapper;

import com.safety.alerts.dto.FirestationDTO;
import com.safety.alerts.model.Firestation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FirestationMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public FirestationMapper (ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FirestationDTO map(Firestation firestation) {
        return modelMapper.map(firestation, FirestationDTO.class);
    }

    public Firestation map(FirestationDTO firestationDTO) {
        return modelMapper.map(firestationDTO, Firestation.class);
    }
}
