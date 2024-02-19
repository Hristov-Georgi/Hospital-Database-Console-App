package ex_04_HospitalDatabase.entities;

import ex_04_HospitalDatabase.entities.Diagnose;

import javax.persistence.*;

@Entity
@Table(name = "medicaments")
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, updatable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "diagnose_id", referencedColumnName = "id", updatable = false)
    private Diagnose diagnose;

    protected Medicament(){}

    public Medicament(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
