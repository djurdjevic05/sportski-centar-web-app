package rs.fon.adjurdjevic.db.model;

import javax.persistence.*;

@Entity
@Table(name = "mesto")
public class Mesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String naziv;
    @Column
    private String postanskiBroj;

    public Mesto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(String postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }
}
