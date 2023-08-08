package com.safety.alerts.service;

import com.safety.alerts.dto.specificDTOs.ChildAlertDTO;
import com.safety.alerts.dto.specificDTOs.FirestationCoverageDTO;
import com.safety.alerts.dto.specificDTOs.PersonInfoDTO;

import java.util.List;

public interface ISpecificService {
    FirestationCoverageDTO getPersonCoveredByStation(Integer station);
    List<ChildAlertDTO> getChildrenByAddress(String address);
    List<String> getPhoneNumbersByStation(Integer station);


    List<PersonInfoDTO> getPersonInfo(String firstName, String lastName);
    List<String> getCommunityEmailByCity(String city);
}
