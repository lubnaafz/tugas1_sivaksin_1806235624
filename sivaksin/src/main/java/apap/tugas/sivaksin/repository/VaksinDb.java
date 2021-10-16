package apap.tugas.sivaksin.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import apap.tugas.sivaksin.model.VaksinModel;

import java.util.Optional;

@Repository
public interface VaksinDb extends JpaRepository<VaksinModel, Long> {
    Optional<VaksinModel> findByJenisVaksin(String jenisVaksin);
}
