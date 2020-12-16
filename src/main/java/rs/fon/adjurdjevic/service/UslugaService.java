package rs.fon.adjurdjevic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.fon.adjurdjevic.db.model.Usluga;
import rs.fon.adjurdjevic.db.model.VrstaUsluge;
import rs.fon.adjurdjevic.db.repository.UslugaRepository;
import rs.fon.adjurdjevic.db.repository.VrstaUslugeRepository;

import java.util.List;

@Service
public class UslugaService {

    private UslugaRepository uslugaRepository;
    private VrstaUslugeRepository vrstaUslugeRepository;

    @Autowired
    public UslugaService(UslugaRepository uslugaRepository, VrstaUslugeRepository vrstaUslugeRepository) {
        this.uslugaRepository = uslugaRepository;
        this.vrstaUslugeRepository = vrstaUslugeRepository;
    }

    public List<VrstaUsluge> findAllServiceTypes() {
        return vrstaUslugeRepository.findAll();
    }

    public List<Usluga> findAllServices() {
        return uslugaRepository.findAll();
    }

    public Usluga findService(Long id) {
        return uslugaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Neispravan id usluge:" + id));
    }

    public Usluga saveService(Usluga usluga) {
        return uslugaRepository.save(usluga);
    }

    public void updateService(Long id, Usluga usluga) {
        uslugaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Neispravan id usluge:" + id));
        uslugaRepository.save(usluga);
    }

    public void deleteService(Long id) {
        uslugaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Neispravan id usluge:" + id));
        uslugaRepository.deleteById(id);
    }

    public void deactivateService(Long id) {
        Usluga usluga = uslugaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Neispravan id usluge:" + id));
        usluga.setAktivna(false);
        uslugaRepository.save(usluga);
    }

    public void activateService(Long id) {
        Usluga usluga = uslugaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Neispravan id usluge:" + id));
        usluga.setAktivna(true);
        uslugaRepository.save(usluga);
    }
}
