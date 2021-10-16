package apap.tugas.sivaksin.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import apap.tugas.sivaksin.model.DokterModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface DokterDb extends JpaRepository<DokterModel, Long> {
}
