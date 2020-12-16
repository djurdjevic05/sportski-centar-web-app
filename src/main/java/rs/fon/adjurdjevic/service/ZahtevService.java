package rs.fon.adjurdjevic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.fon.adjurdjevic.db.model.Zahtev;
import rs.fon.adjurdjevic.db.repository.KorisnikRepository;
import rs.fon.adjurdjevic.db.repository.ZahtevRepository;

import java.util.Date;
import java.util.List;

@Service
public class ZahtevService {

    private ZahtevRepository zahtevRepository;
    private KorisnikRepository korisnikRepository;

    @Autowired
    public ZahtevService(KorisnikRepository korisnikRepository, ZahtevRepository zahtevRepository) {
        this.korisnikRepository = korisnikRepository;
        this.zahtevRepository = zahtevRepository;
    }

    public List<Zahtev> findAllRequests() {
        return zahtevRepository.findAll(Sort.by("id").descending());
    }

    public List<Zahtev> findAllRequests(String username) {
        return zahtevRepository.getAllByUsername(username);
    }

    public void approveRequest(Long id, String username) {
        Zahtev zahtev = zahtevRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Neispravan id zahteva:" + id));
        zahtev.setStatusZahteva("PRIHVACEN");
        zahtev.setDatumOdgovora(new Date());
        zahtev.setAdmin(korisnikRepository.findByUsername(username));
        zahtevRepository.save(zahtev);
    }

    public void declineRequest(Long id, String username) {
        Zahtev zahtev = zahtevRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Neispravan id zahteva:" + id));
        zahtev.setStatusZahteva("ODBIJEN");
        zahtev.setDatumOdgovora(new Date());
        zahtev.setAdmin(korisnikRepository.findByUsername(username));
        zahtevRepository.save(zahtev);
    }

    public void saveRequest(Zahtev zahtev, String username) {
        zahtev.setClan(korisnikRepository.findByUsername(username));
        zahtevRepository.save(zahtev);
    }
}
