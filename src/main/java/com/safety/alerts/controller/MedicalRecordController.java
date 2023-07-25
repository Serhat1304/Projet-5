package com.safety.alerts.controller;

import com.safety.alerts.dto.MedicalRecordDTO;
import com.safety.alerts.model.MedicalRecord;
import com.safety.alerts.service.IMedicalRecordService;
import com.safety.alerts.service.impl.MedicalRecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
public class MedicalRecordController {

    @Autowired
    private IMedicalRecordService medicalRecordService;

    @GetMapping("/medicalRecord")
    public List<MedicalRecordDTO> getMedicalRecord() {
        return medicalRecordService.getAllMedicalRecord();
    }

    @PostMapping("/medicalRecord")
    public MedicalRecordDTO saveMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO){
        return medicalRecordService.saveMedicalRecord(medicalRecordDTO);
    }

    @PutMapping("/medicalRecord/{firstName}/{lastName}")
    public MedicalRecordDTO updateMedicalRecord(
            @PathVariable String firstName,
            @PathVariable String lastName,
            @RequestBody MedicalRecordDTO medicalRecordDTO) {
        return medicalRecordService.updateMedicalRecord(firstName, lastName, medicalRecordDTO);
    }

    @DeleteMapping("/medicalRecord/{firstName}/{lastName}")
    public void deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName) {
        medicalRecordService.deleteMedicalRecord(lastName, firstName);
    }

}
