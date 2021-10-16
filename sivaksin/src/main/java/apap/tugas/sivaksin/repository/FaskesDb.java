package apap.tugas.sivaksin.repository;

import apap.tugas.sivaksin.model.VaksinModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import apap.tugas.sivaksin.model.FaskesModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface FaskesDb extends JpaRepository<FaskesModel, Long> {
    Optional<FaskesModel> findByIdFaskes(Long idFaskes);
    Optional<FaskesModel> findByNamaFaskes(String namaFaskes);
    List<FaskesModel> findAllByVaksin(Optional<VaksinModel> vaksin);
}
