package rs.fon.adjurdjevic.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.fon.adjurdjevic.db.model.Mesto;

@Repository
public interface MestoRepository extends JpaRepository<Mesto, Long> {
}
