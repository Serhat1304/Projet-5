package com.safety.alerts.controller;

import com.safety.alerts.dto.FirestationDTO;
import com.safety.alerts.service.IFirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirestationController {
    @Autowired
    private IFirestationService firestationService;


    @GetMapping("/firestations")
    public List<FirestationDTO> getFirestation() {
        return firestationService.getAll();
    }
    @PostMapping("/firestations")
    public FirestationDTO addFirestation(@RequestBody FirestationDTO firestationDTO) {
        return firestationService.addFirestation(firestationDTO);
    }

    @PutMapping("/firestations")
    public FirestationDTO updateFirestation(@RequestBody FirestationDTO firestationDTO) {
        return firestationService.updateFirestation(firestationDTO);
    }

    @DeleteMapping("/firestations")
    public void deleteFirestation(@RequestBody FirestationDTO firestationDTO) {
        firestationService.deleteFirestationByAddress(firestationDTO.getAddress());
    }
}
