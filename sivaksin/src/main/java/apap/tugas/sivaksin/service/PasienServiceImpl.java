package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.model.PasienModel;
import apap.tugas.sivaksin.repository.FaskesDb;
import apap.tugas.sivaksin.repository.PasienDb;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class PasienServiceImpl implements PasienService{
    @Autowired
    PasienDb pasienDb;

    @Override
    public void addPasien(PasienModel pasien) { pasienDb.save(pasien); }

    @Override
    public void updatePasien(PasienModel pasien) { pasienDb.save(pasien); }

    @Override
    public List<PasienModel> getPasienList() {
        return pasienDb.findAll();
    }

    @Override
    public PasienModel getPasienByIdPasien(Long idPasien) {
        Optional<PasienModel> pasien = pasienDb.findByIdPasien(idPasien);
        if (pasien.isPresent()) {
            return  pasien.get();
        }
        return null;
    }

    @Override
    public String makeBatchId(PasienModel pasien){
        String ke1 = Integer.toString(pasien.getJenisKelamin());

        int panjangNama = pasien.getNamaPasien().length();
        String lastCharName = pasien.getNamaPasien().substring(panjangNama-1,panjangNama);
        String ke2 = lastCharName.toUpperCase(Locale.ROOT);

        String firstCharTempatLahir = pasien.getTempatLahir().substring(0,2);
        String ke3_4 = firstCharTempatLahir.toUpperCase(Locale.ROOT);

        int tanggal = pasien.getTanggalLahir().getDayOfMonth();
        String ke5_6 = Integer.toString(tanggal);

        int bulan = pasien.getTanggalLahir().getMonthValue();
        String ke7_8 = Integer.toString(bulan);

        int tahun = pasien.getTanggalLahir().getYear();
        int threeCharTahun = Math.round(tahun/10);
        String ke9_11 = Integer.toString(threeCharTahun);

        String random = RandomString.make(2);
        String ke12_13 = random.toUpperCase(Locale.ROOT);

        String idBatch = ke1 + ke2 + ke3_4 + ke5_6 + ke7_8 + ke9_11 + ke12_13;

        return idBatch;
    }
}
