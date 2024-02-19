package ex_04_HospitalDatabase.patientProcedures;

import ex_04_HospitalDatabase.entities.Patient;

import java.time.LocalDate;

public interface PatientHandling {

    void addNewPatient(Patient patient);

    Patient findPatient(String firstName, String lastName, LocalDate dateOfBirth);

    void deletePatient(Patient patient);
}
