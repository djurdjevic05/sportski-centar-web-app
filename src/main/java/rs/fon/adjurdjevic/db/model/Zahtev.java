package rs.fon.adjurdjevic.db.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "zahtev")
public class Zahtev {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String naziv;
    @Column
    private String opis;
    @Column
    private String statusZahteva;
    @Column
    @Temporal(value = TemporalType.DATE)
    private Date datumKreiranja;
    @Column
    @Temporal(value = TemporalType.DATE)
    private Date datumOdgovora;
    @ManyToOne
    @JoinColumn(name = "clan_id", referencedColumnName = "id")
    private Korisnik clan;
    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Korisnik admin;
    @ManyToOne
    @JoinColumn(name = "usluga_id", referencedColumnName = "id")
    private Usluga usluga;

    public Zahtev() {
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

    public String getStatusZahteva() {
        return statusZahteva;
    }

    public void setStatusZahteva(String statusZahteva) {
        this.statusZahteva = statusZahteva;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public Date getDatumOdgovora() {
        return datumOdgovora;
    }

    public void setDatumOdgovora(Date datumOdgovora) {
        this.datumOdgovora = datumOdgovora;
    }

    public Korisnik getClan() {
        return clan;
    }

    public void setClan(Korisnik clan) {
        this.clan = clan;
    }

    public Korisnik getAdmin() {
        return admin;
    }

    public void setAdmin(Korisnik admin) {
        this.admin = admin;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }
}
