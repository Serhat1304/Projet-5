package com.safety.alerts.dto.specificDTOs;

import lombok.Data;

import java.util.List;

@Data
public class ChildAlertDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private List<String> otherHouseMembers;
}
