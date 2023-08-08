package com.safety.alerts.dto.specificDTOs;

import com.safety.alerts.dto.PersonDTO;
import lombok.Data;

import java.util.List;

@Data
public class FirestationCoverageDTO{
    private List<PersonDTO> personDTOS;
    private Integer adultsCount;
    private Integer childrenCount;
}
