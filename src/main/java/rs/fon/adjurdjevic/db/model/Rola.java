package rs.fon.adjurdjevic.db.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "rola")
public class Rola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String naziv;
    @Column
    private String opis;
    @OneToMany(mappedBy = "rola")
    private Collection<Korisnik> korisnici;

    public Rola() {
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

    public Collection<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(Collection<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    @Override
    public String toString() {
        return "Rola{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                '}';
    }
}
