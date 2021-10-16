package apap.tugas.sivaksin.controller;

import apap.tugas.sivaksin.model.DokterPasienModel;
import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.model.PasienModel;
import apap.tugas.sivaksin.model.VaksinModel;
import apap.tugas.sivaksin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PasienController {
    @Qualifier("faskesServiceImpl")
    @Autowired
    private FaskesService faskesService;

    @Qualifier("vaksinServiceImpl")
    @Autowired
    private VaksinService vaksinService;

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Qualifier("dokterPasienServiceImpl")
    @Autowired
    private DokterPasienService dokterPasienService;

    @GetMapping("/pasien")
    public String listPasien(Model model){
        List<PasienModel> listPasien = pasienService.getPasienList();
        if(listPasien.size() != 0) {
            model.addAttribute("listPasien", listPasien);
            return "daftar-pasien";
        }
        return "error-not-found";
    }

    @GetMapping("/pasien/tambah")
    public String addPasienForm(
            Model model){
        PasienModel pasien = new PasienModel();

        model.addAttribute("pasien", pasien);
        return "form-add-pasien";
    }

    @PostMapping("/pasien/tambah")
    public String addPasienSubmit(
            @ModelAttribute PasienModel pasien,
            Model model
    ){
        pasienService.addPasien(pasien);

        model.addAttribute("namaPasien", pasien.getNamaPasien());
        return "add-pasien";
    }

    @GetMapping("/pasien/{idPasien}")
    public String viewDetailPasien(
            @PathVariable Long idPasien,
            Model model
    ){
        PasienModel pasien = pasienService.getPasienByIdPasien(idPasien);

        model.addAttribute("pasien", pasien);
        model.addAttribute("listDokterPasien", pasien.getListDokterPasien());

        return "detail-pasien";
    }

    @GetMapping("/pasien/ubah/{idPasien}")
    public String updatePasienForm(
            @PathVariable Long idPasien,
            Model model
    ){
        PasienModel pasien = pasienService.getPasienByIdPasien(idPasien);

        model.addAttribute("pasien", pasien);
        return "form-update-pasien";
    }

    @PostMapping("/pasien/ubah")
    public String updatePasienSubmit(
            @ModelAttribute PasienModel pasien,
            Model model
    ){
        pasienService.updatePasien(pasien);

        model.addAttribute("namaPasien", pasien.getNamaPasien());
        return "update-pasien";
    }

    @RequestMapping(value = "/cari/pasien", method = RequestMethod.GET)
    public String filterPasien(
            @RequestParam (value="jenisVaksin", required = false) String jenisVaksin,
            @RequestParam (value="namaFaskes", required = false) String namaFaskes,
            Model model
    ){
        List <DokterPasienModel> listDokterPasien = dokterPasienService.getListDokterPasienByJenisVaksinAndNamaFaskes(jenisVaksin, namaFaskes);

        model.addAttribute("listVaksin", vaksinService.getVaksinList());
        model.addAttribute("listFaskes", faskesService.getFaskesList());
        model.addAttribute("listDokterPasien", listDokterPasien);
        return "cari-pasien";
    }


}
