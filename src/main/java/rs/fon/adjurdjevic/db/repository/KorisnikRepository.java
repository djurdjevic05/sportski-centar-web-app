package rs.fon.adjurdjevic.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.fon.adjurdjevic.db.model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    Korisnik findByUsername(String username);
}
