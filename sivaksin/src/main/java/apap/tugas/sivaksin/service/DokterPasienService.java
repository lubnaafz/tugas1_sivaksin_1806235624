package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.DokterPasienModel;
import apap.tugas.sivaksin.model.FaskesModel;

import java.util.List;

public interface DokterPasienService {
    void addDokterPasien(DokterPasienModel dokterPasien);
    void updateDokterPasien(DokterPasienModel dokterPasien);
    void deleteDokterPasien(DokterPasienModel dokterPasien);
    List<DokterPasienModel> getDokterPasienList();
    FaskesModel getFaskes(Long idFaskes);
    List<DokterPasienModel> getListDokterPasienByJenisVaksinAndNamaFaskes(String jenisVaksin, String namaFaskes);
}
