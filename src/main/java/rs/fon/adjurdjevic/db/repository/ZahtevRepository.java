package rs.fon.adjurdjevic.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.fon.adjurdjevic.db.model.Zahtev;

import java.util.List;

@Repository
public interface ZahtevRepository extends JpaRepository<Zahtev, Long> {
    @Query("select z from Zahtev z inner join z.clan c where c.username = ?1 order by z.id desc")
    List<Zahtev> getAllByUsername(String username);
}
