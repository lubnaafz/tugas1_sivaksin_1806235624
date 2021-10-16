package apap.tugas.sivaksin.repository;

import apap.tugas.sivaksin.model.DokterPasienModel;
import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.model.VaksinModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DokterPasienDb extends JpaRepository<DokterPasienModel, Long> {
    Optional<DokterPasienModel> findByIdDokterPasien(Long idDokterPasien);
    List<DokterPasienModel> findDistinctByJenisVaksinOrNamaFaskes(String jenisVaksin, String namaFaskes);
}
