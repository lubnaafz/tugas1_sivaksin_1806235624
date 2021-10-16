package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.PasienModel;
import apap.tugas.sivaksin.model.VaksinModel;
import apap.tugas.sivaksin.repository.VaksinDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaksinServiceImpl implements VaksinService{
    @Autowired
    VaksinDb vaksinDb;

    @Override
    public void addVaksin(VaksinModel vaksin) { vaksinDb.save(vaksin); }

    @Override
    public void updateVaksin(VaksinModel vaksin) { vaksinDb.save(vaksin); }

    @Override
    public void deleteVaksin(VaksinModel vaksin){ vaksinDb.delete(vaksin); }

    @Override
    public List<VaksinModel> getVaksinList() {
        return vaksinDb.findAll();
    }
}
