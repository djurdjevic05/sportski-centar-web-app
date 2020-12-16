package rs.fon.adjurdjevic.db.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "korisnik")
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String ime;
    @Column
    private String prezime;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String jmbg;
    @Column
    private String pol;
    @Column
    private String adresa;
    @Column
    @Temporal(value = TemporalType.DATE)
    private Date datumRodjenja;
    @ManyToOne
    @JoinColumn(name = "mesto_id", referencedColumnName = "id")
    private Mesto mesto;
    @ManyToOne
    @JoinColumn(name = "rola_id", referencedColumnName = "id")
    private Rola rola;
    @OneToMany(mappedBy = "clan")
    private Collection<Zahtev> zahteviClanova;
    @OneToMany(mappedBy = "admin")
    private Collection<Zahtev> zahteviAdmina;

    public Korisnik() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Rola getRola() {
        return rola;
    }

    public void setRola(Rola rola) {
        this.rola = rola;
    }

    public Collection<Zahtev> getZahteviClanova() {
        return zahteviClanova;
    }

    public void setZahteviClanova(Collection<Zahtev> zahteviClanova) {
        this.zahteviClanova = zahteviClanova;
    }

    public Collection<Zahtev> getZahteviAdmina() {
        return zahteviAdmina;
    }

    public void setZahteviAdmina(Collection<Zahtev> zahteviAdmina) {
        this.zahteviAdmina = zahteviAdmina;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", jmbg='" + jmbg + '\'' +
                ", pol='" + pol + '\'' +
                ", adresa='" + adresa + '\'' +
                ", datumRodjenja=" + datumRodjenja +
                '}';
    }
}
