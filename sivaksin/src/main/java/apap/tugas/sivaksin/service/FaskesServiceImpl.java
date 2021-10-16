package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.model.VaksinModel;
import apap.tugas.sivaksin.repository.FaskesDb;
import apap.tugas.sivaksin.repository.VaksinDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FaskesServiceImpl implements FaskesService{
    @Autowired
    FaskesDb faskesDb;

    @Autowired
    VaksinDb vaksinDb;

    @Override
    public void addFaskes(FaskesModel faskes) { faskesDb.save(faskes); }

    @Override
    public void updateFaskes(FaskesModel faskes) { faskesDb.save(faskes); }

    @Override
    public void deleteFaskes(FaskesModel faskes){ faskesDb.delete(faskes); }

    @Override
    public List<FaskesModel> getFaskesList() {
        return faskesDb.findAll();
    }

    @Override
    public FaskesModel getFaskesByIdFaskes(Long idFaskes) {
        Optional<FaskesModel> faskes = faskesDb.findByIdFaskes(idFaskes);
        if (faskes.isPresent()) {
            return  faskes.get();
        }
        return null;
    }

    @Override
    public List<FaskesModel> getListFaskesByJenisVaksin(String jenisVaksin){
        Optional<VaksinModel> vaksin = vaksinDb.findByJenisVaksin(jenisVaksin);
        return faskesDb.findAllByVaksin(vaksin);
    }
}
