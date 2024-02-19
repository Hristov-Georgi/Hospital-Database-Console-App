package ex_04_HospitalDatabase;

import ex_04_HospitalDatabase.entities.Diagnose;
import ex_04_HospitalDatabase.entities.Medicament;
import ex_04_HospitalDatabase.entities.Patient;
import ex_04_HospitalDatabase.entities.Visitation;
import ex_04_HospitalDatabase.patientProcedures.PatientHandling;
import ex_04_HospitalDatabase.patientProcedures.PatientHandlingImpl;
import ex_04_HospitalDatabase.validateData.ValidateData;
import ex_04_HospitalDatabase.validateData.ValidateDataImpl;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class StartApplication {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PU_name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ValidateData validateData = new ValidateDataImpl();
        Read read = new Read(validateData);
        PatientHandling patientHandling = new PatientHandlingImpl(entityManager);

        String commands = "\nList of commands: add patient, delete patient, add visitation, add diagnose\n" +
             "\nEnter desired command from the list above to continue or type \"close program\" and press \"enter\" to exit:";

        System.out.println(commands);

        String command = scanner.nextLine();
        while (!command.equals("close program")) {

            entityManager.getTransaction().begin();

            switch (command) {

                case "add patient":
                    String firstName = read.firstName(scanner);
                    String lastName = read.lastName(scanner);
                    String address = read.address(scanner);
                    String email = read.email(scanner);
                    LocalDate date = read.date(scanner, command);
                    String picturePath = read.picture(scanner);
                    char insurance = read.insurance(scanner);

                    Patient patient = new Patient(firstName, lastName, address, email, date, picturePath, insurance);

                    patientHandling.addNewPatient(patient);


                    break;

                case "add visitation":

                    String fName = read.firstName(scanner);
                    String lName = read.lastName(scanner);
                    LocalDate dateOfBirth = read.date(scanner, "add patient");

                    Patient findPatient = patientHandling.findPatient(fName, lName, dateOfBirth);

                    String comment = read.comment(scanner);
                    LocalDate visitationDate = read.date(scanner, command);

                    Visitation visitation = new Visitation(visitationDate, comment);
                    findPatient.setVisitations(visitation);
                    visitation.setPatient(findPatient);

                    entityManager.persist(visitation);
                    entityManager.persist(findPatient);

                    break;


                case "add diagnose":

                    String nameFirst = read.firstName(scanner);
                    String nameLast = read.lastName(scanner);
                    LocalDate birthDate = read.date(scanner, "add patient");

                    Patient patientWithDiagnose = patientHandling.findPatient(nameFirst, nameLast, birthDate);

                    String diagnoseName = read.diagnoseName(scanner);
                    String diagnoseComment = read.comment(scanner);

                    Diagnose diagnose = new Diagnose(diagnoseName, diagnoseComment);

                    patientWithDiagnose.setDiagnoses(diagnose);
                    diagnose.setPatient(patientWithDiagnose);

                    entityManager.persist(diagnose);
                    entityManager.persist(patientWithDiagnose);

                    List<Medicament> medicamentList = read.medicamentList(scanner);

                    for (Medicament m : medicamentList) {
                        m.setDiagnose(diagnose);
                        diagnose.setMedicamentList(m);
                        entityManager.persist(m);
                        entityManager.persist(diagnose);
                    }

                    break;

                case "delete patient" :
                    String firstNameDelete = read.firstName(scanner);
                    String lastNameDelete = read.lastName(scanner);
                    LocalDate birthDateDelete = read.date(scanner, "add patient");

                    Patient patientDelete = patientHandling.findPatient(firstNameDelete, lastNameDelete, birthDateDelete);

                    patientHandling.deletePatient(patientDelete);
                    break;

            }

            try {
                entityManager.getTransaction().commit();
                System.out.println("All records were added successfully in database.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println(commands);

            command = scanner.nextLine();
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
