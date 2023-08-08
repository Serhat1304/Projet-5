package com.safety.alerts.controller;

import com.safety.alerts.dto.specificDTOs.ChildAlertDTO;
import com.safety.alerts.dto.specificDTOs.FirestationCoverageDTO;
import com.safety.alerts.dto.specificDTOs.PersonInfoDTO;
import com.safety.alerts.service.impl.SpecificServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpecificController {

    @Autowired
    private SpecificServiceImpl specificService;

    @GetMapping("/firestation")
    public ResponseEntity<FirestationCoverageDTO> getFirestationCoverage(@RequestParam Integer station) {
        FirestationCoverageDTO coverageDTO = specificService.getPersonCoveredByStation(station);

        if (coverageDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(coverageDTO, HttpStatus.OK);
    }

    @GetMapping("/childAlert")
    public ResponseEntity<List<ChildAlertDTO>> getChildAlert(@RequestParam String address){
        List<ChildAlertDTO> childAlertDTOList = specificService.getChildrenByAddress(address);

        if (!childAlertDTOList.isEmpty()){
            return new ResponseEntity<>(childAlertDTOList,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(childAlertDTOList,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("phoneAlert")
    public ResponseEntity<List<String>> getPhoneNumbersByFirestation(@RequestParam Integer firestation){

        List<String> phoneNumbers = specificService.getPhoneNumbersByStation(firestation);

        if (!phoneNumbers.isEmpty()){
            return new ResponseEntity<>(phoneNumbers, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(phoneNumbers, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("personInfo")
    public ResponseEntity<List<PersonInfoDTO>> getPersonInfo(@RequestParam String firstName, String lastName){

        List<PersonInfoDTO> personInfoDTOS = specificService.getPersonInfo(firstName, lastName);

        if (!personInfoDTOS.isEmpty()) {
            return new ResponseEntity<>(personInfoDTOS, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(personInfoDTOS, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("communityEmail")
    public ResponseEntity<List<String>> getCommunityEmailByCity(@RequestParam String city){
        List<String> communityEmails = specificService.getCommunityEmailByCity(city);

        if (!communityEmails.isEmpty()){
            return new ResponseEntity<>(communityEmails, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(communityEmails,HttpStatus.NOT_FOUND);
        }
    }
}
