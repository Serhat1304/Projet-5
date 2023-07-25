package com.safety.alerts.repository;

import com.safety.alerts.model.MedicalRecord;

public interface IMedicalRecordRepository extends ICrudRepository<MedicalRecord>{

    MedicalRecord getMedicalRecord(String firstName, String lastName);

}
