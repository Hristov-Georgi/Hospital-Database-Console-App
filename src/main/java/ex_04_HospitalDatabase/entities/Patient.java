package ex_04_HospitalDatabase.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", updatable = false, nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false, updatable = false)
    private String lastName;

    @Column(nullable = false, length = 500)
    private String address;

    @Column(nullable = false)
    private String email;

    @Column(name = "date_of_birth", nullable = false, updatable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String picturePath;

    @Column(name = "medical_insurance", nullable = false)
    private char medicalInsurance;

    @OneToMany(mappedBy = "patient", targetEntity = Visitation.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Visitation> visitations;

    @OneToMany(mappedBy = "patient", targetEntity = Diagnose.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Diagnose> diagnoses;

    protected Patient(){}

    public Patient(String firstName, String lastName, String address, String email, LocalDate dateOfBirth,
                   String picturePath, char medicalInsurance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.picturePath = picturePath;
        this.medicalInsurance = medicalInsurance;
        this.visitations = new ArrayList<>();
        this.diagnoses = new ArrayList<>();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public char isMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(char medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    public List<Visitation> getVisitations() {
        return Collections.unmodifiableList(this.visitations);
    }

    public void setVisitations(Visitation visitation) {
        this.visitations.add(visitation);
    }

    public char getMedicalInsurance() {
        return medicalInsurance;
    }

    public List<Diagnose> getDiagnoses() {
        return Collections.unmodifiableList(diagnoses);
    }

    public void setDiagnoses(Diagnose diagnose) {
        this.diagnoses.add(diagnose);
    }
}
