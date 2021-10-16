package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.DokterModel;
import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.repository.DokterDb;
import apap.tugas.sivaksin.repository.FaskesDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DokterServiceImpl implements DokterService{
    @Autowired
    DokterDb dokterDb;

    @Override
    public List<DokterModel> getDokterList() {
        return dokterDb.findAll();
    }
}
