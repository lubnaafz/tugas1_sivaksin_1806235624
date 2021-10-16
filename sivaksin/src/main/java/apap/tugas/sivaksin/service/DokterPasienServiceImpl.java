package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.DokterPasienModel;
import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.repository.DokterPasienDb;
import apap.tugas.sivaksin.repository.FaskesDb;
import apap.tugas.sivaksin.repository.VaksinDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DokterPasienServiceImpl implements DokterPasienService{
    @Autowired
    DokterPasienDb dokterPasienDb;

    @Autowired
    FaskesDb faskesDb;

    @Autowired
    VaksinDb vaksinDb;

    @Override
    public void addDokterPasien(DokterPasienModel dokterPasien) { dokterPasienDb.save(dokterPasien); }

    @Override
    public void updateDokterPasien(DokterPasienModel dokterPasien) { dokterPasienDb.save(dokterPasien); }

    @Override
    public void deleteDokterPasien(DokterPasienModel dokterPasien){ dokterPasienDb.delete(dokterPasien); }

    @Override
    public List<DokterPasienModel> getDokterPasienList() {
        return dokterPasienDb.findAll();
    }

    @Override
    public FaskesModel getFaskes(Long idFaskes){
        Optional<FaskesModel> faskes = faskesDb.findByIdFaskes(idFaskes);
        if (faskes.isPresent()) {
            return  faskes.get();
        }
        return null;
    }

    @Override
    public List<DokterPasienModel> getListDokterPasienByJenisVaksinAndNamaFaskes(String jenisVaksin, String namaFaskes){
        return dokterPasienDb.findDistinctByJenisVaksinOrNamaFaskes(jenisVaksin, namaFaskes);
    }
}
