package rs.fon.adjurdjevic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.fon.adjurdjevic.db.model.Korisnik;
import rs.fon.adjurdjevic.db.model.Mesto;
import rs.fon.adjurdjevic.db.model.Rola;
import rs.fon.adjurdjevic.db.repository.KorisnikRepository;
import rs.fon.adjurdjevic.db.repository.MestoRepository;
import rs.fon.adjurdjevic.db.repository.RolaRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class KorisnikService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(KorisnikService.class);

    private KorisnikRepository korisnikRepository;
    private RolaRepository rolaRepository;
    private MestoRepository mestoRepository;

    @Autowired
    public KorisnikService(KorisnikRepository korisnikRepository, RolaRepository rolaRepository, MestoRepository mestoRepository) {
        this.korisnikRepository = korisnikRepository;
        this.rolaRepository = rolaRepository;
        this.mestoRepository = mestoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik korisnik = korisnikRepository.findByUsername(username);
        if (korisnik == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        log.debug("Rola 1 {}", korisnik.getRola());
        grantedAuthorities.add(new SimpleGrantedAuthority(korisnik.getRola().getNaziv()));
        log.debug("Authorities 2 {}", Arrays.toString(grantedAuthorities.toArray()));
        return new org.springframework.security.core.userdetails.User(korisnik.getUsername(), korisnik.getPassword(), grantedAuthorities);
    }

    public List<Korisnik> findAllUsera() {
        return korisnikRepository.findAll();
    }

    public List<Mesto> findAllPlaces() {
        return mestoRepository.findAll();
    }

    public Korisnik findUser(Long id) {
        return korisnikRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Neispravan id korisnika:" + id));
    }

    public Korisnik saveUser(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }

    public void updateUser(Long id, Korisnik korisnik) {
        korisnikRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Neispravan id korisnika:" + id));
        korisnikRepository.save(korisnik);
    }

    public void deleteUser(Long id) {
        korisnikRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Neispravan id korisnika:" + id));
        korisnikRepository.deleteById(id);
    }

    public List<Rola> getRoles() {
        return rolaRepository.findAll();
    }
}
