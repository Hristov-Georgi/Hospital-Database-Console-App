package ex_04_HospitalDatabase.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "diagnoses")
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, updatable = false)
    private String name;

    @Column(nullable = false, updatable = false, length = 2000)
    private String comment;


    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Patient patient;


    @OneToMany(mappedBy = "diagnose", targetEntity = Medicament.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Medicament> medicamentList;

    protected Diagnose(){}

    public Diagnose (String name, String comment) {
        this.name = name;
        this.comment = comment;
        this.medicamentList = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Medicament> getMedicamentList() {
        return Collections.unmodifiableList(medicamentList);
    }

    public void setMedicamentList(Medicament medicament) {
        this.medicamentList.add(medicament);
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}

