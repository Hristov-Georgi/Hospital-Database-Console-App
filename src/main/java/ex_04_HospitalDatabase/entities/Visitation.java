package ex_04_HospitalDatabase.entities;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visitations")
public class Visitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, updatable = false)
    private LocalDate date;

    @Column(nullable = false, updatable = false, length = 2000)
    private String comment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id",
            referencedColumnName = "id",
            nullable = false, updatable = false)
    private Patient patient;

    protected Visitation(){}

    public Visitation(LocalDate date, String comment) {
        this.date = date;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


}
