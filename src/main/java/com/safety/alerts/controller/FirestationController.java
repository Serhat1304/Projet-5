package com.safety.alerts.controller;

import com.safety.alerts.dto.FirestationDTO;
import com.safety.alerts.service.FirestationServiceImpl;
import com.safety.alerts.service.IFirestationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirestationController {
    private final IFirestationService firestationService;

    public FirestationController(FirestationServiceImpl firestationService) {
        this.firestationService = firestationService;
    }

    @GetMapping("/firestations")
    public List<FirestationDTO> getFirestation(@RequestBody FirestationDTO firestationDTO) {
        return firestationService.getAll();
    }
    @PostMapping("/firestations")
    public FirestationDTO addFirestation(@RequestBody FirestationDTO firestationDTO) {
        return firestationService.addFirestation(firestationDTO);
    }

    @PutMapping("/{address}")
    public FirestationDTO updateFirestation(@PathVariable("address")String address,
                                                     @RequestBody FirestationDTO firestationDTO) {
        return firestationService.updateFirestation(address, firestationDTO.getStation());
    }

    @DeleteMapping("{address}")
    public void deleteFirestation(@RequestParam String address, @RequestParam Integer station) {
        firestationService.deleteFirestation(address, station);
    }
}
