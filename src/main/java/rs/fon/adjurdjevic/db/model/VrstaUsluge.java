package rs.fon.adjurdjevic.db.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "vrsta_usluge")
public class VrstaUsluge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String naziv;
    @Column
    private String opis;
    @OneToMany(mappedBy = "vrstaUsluge")
    private Collection<Usluga> usluge;

    public VrstaUsluge() {
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Collection<Usluga> getUsluge() {
        return usluge;
    }

    public void setUsluge(Collection<Usluga> usluge) {
        this.usluge = usluge;
    }

    @Override
    public String toString() {
        return "VrstaUsluge{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                '}';
    }
}
