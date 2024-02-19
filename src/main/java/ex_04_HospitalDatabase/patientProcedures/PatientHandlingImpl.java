package ex_04_HospitalDatabase.patientProcedures;

import ex_04_HospitalDatabase.entities.Patient;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;

public class PatientHandlingImpl implements PatientHandling {
     private final EntityManager entityManager;

     public PatientHandlingImpl (EntityManager entityManager) {
         this.entityManager = entityManager;
     }

    @Override
    public void addNewPatient(Patient patient) {
        entityManager.persist(patient);
    }

    @Override
    public Patient findPatient(String firstName, String lastName, LocalDate dateOfBirth) {

         try {
             return entityManager.createQuery("SELECT p FROM Patient p" +
                             " WHERE p.firstName = :firstName AND p.lastName = :lastName" +
                             " AND p.dateOfBirth = :dateOfBirth", Patient.class)
                     .setParameter("firstName", firstName)
                     .setParameter("lastName", lastName)
                     .setParameter("dateOfBirth", dateOfBirth)
                     .getSingleResult();
         } catch (NoResultException e) {
             System.out.println("Patient is not in the database. You should add him/her in database, before adding new visitation");
             return null;
         }

    }

    @Override
    public void deletePatient(Patient patient) {
        entityManager.remove(patient);
        System.out.printf("Patient %s %s born %s was deleted from database!\n",
                patient.getFirstName(), patient.getLastName(), patient.getDateOfBirth());
      }
}
