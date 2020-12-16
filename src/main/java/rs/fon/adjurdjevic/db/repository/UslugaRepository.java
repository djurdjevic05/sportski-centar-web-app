package rs.fon.adjurdjevic.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.fon.adjurdjevic.db.model.Usluga;

@Repository
public interface UslugaRepository extends JpaRepository<Usluga, Long> {
}
