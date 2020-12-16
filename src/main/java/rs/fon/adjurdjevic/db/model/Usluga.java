package rs.fon.adjurdjevic.db.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "usluga")
public class Usluga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String naziv;
    @Column
    private String opis;
    @Column
    private Boolean aktivna;
    @ManyToOne
    @JoinColumn(name = "vrsta_usluge_id", referencedColumnName = "id")
    private VrstaUsluge vrstaUsluge;
    @OneToMany(mappedBy = "usluga")
    private Collection<Zahtev> zahtevi;

    public Usluga() {
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

    public Boolean getAktivna() {
        return aktivna;
    }

    public void setAktivna(Boolean aktivna) {
        this.aktivna = aktivna;
    }

    public VrstaUsluge getVrstaUsluge() {
        return vrstaUsluge;
    }

    public void setVrstaUsluge(VrstaUsluge vrstaUsluge) {
        this.vrstaUsluge = vrstaUsluge;
    }

    public Collection<Zahtev> getZahtevi() {
        return zahtevi;
    }

    public void setZahtevi(Collection<Zahtev> zahtevi) {
        this.zahtevi = zahtevi;
    }

}
