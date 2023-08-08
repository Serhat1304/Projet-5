    package com.safety.alerts.dto;

    import lombok.Data;

    import java.util.List;

    @Data
    public class MedicalRecordDTO {
        private String firstName;
        private String lastName;
        private String birthdate;
        private List<String> medications;
        private List<String> allergies;
        private Integer age;
    }
