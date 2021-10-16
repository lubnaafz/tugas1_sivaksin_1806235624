package apap.tugas.sivaksin.controller;

import apap.tugas.sivaksin.model.*;
import apap.tugas.sivaksin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FaskesController {
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

    @GetMapping("/faskes")
    public String listFaskes(Model model){
        List<FaskesModel> listFaskes = faskesService.getFaskesList();
        if(listFaskes.size() != 0) {
            model.addAttribute("listFaskes", listFaskes);
            return "daftar-faskes";
        }
        return "error-not-found";
    }

    @GetMapping("/faskes/tambah")
    public String addFaskesForm(
            Model model
    ){

        List<VaksinModel> listVaksin = vaksinService.getVaksinList();

        List<PasienModel> listPasien = new ArrayList<>();

        model.addAttribute("listVaksin", listVaksin);
        model.addAttribute("listPasien", listPasien);
        model.addAttribute("faskes", new FaskesModel());
        return "form-add-faskes";
    }

    @PostMapping("/faskes/tambah")
    public String addFaskesSubmit(
            @ModelAttribute FaskesModel faskes,
            Model model
    ){
        faskesService.addFaskes(faskes);
        model.addAttribute("namaFaskes", faskes.getNamaFaskes());
        return "add-faskes";
    }

    @GetMapping("/faskes/{idFaskes}")
    public String viewDetailFaskes(
            @PathVariable Long idFaskes,
            Model model
    ){
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);

        model.addAttribute("listPasien", faskes.getListPasien());
        model.addAttribute("faskes", faskes);

        return "detail-faskes";
    }

    @GetMapping("/faskes/ubah/{idFaskes}")
    public String updateFaskesForm(
            @PathVariable Long idFaskes,
            Model model
    ){
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);
        List<VaksinModel> listVaksin = vaksinService.getVaksinList();

        model.addAttribute("listVaksin", listVaksin);
        model.addAttribute("faskes", faskes);
        return "form-update-faskes";
    }

    @PostMapping("/faskes/ubah")
    public String updateFaskesSubmit(
            @ModelAttribute FaskesModel faskes,
            Model model
    ){
        faskesService.updateFaskes(faskes);
        model.addAttribute("namaFaskes", faskes.getNamaFaskes());
        return "update-faskes";
    }

    @GetMapping("/faskes/hapus/{idFaskes}")
    public String deleteFaskes(
            @PathVariable Long idFaskes,
            Model model
    ){
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);

        faskesService.deleteFaskes(faskes);
        model.addAttribute("namaFaskes", faskes.getNamaFaskes());
        return "delete-faskes";


    }

    @GetMapping("/faskes/{idFaskes}/tambah/pasien")
    public String addPasienFaskesForm(
            @PathVariable Long idFaskes,
            Model model){

        FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);
        PasienModel pasien = new PasienModel();

        faskes.getListPasien().add(pasien);

        String batchId = "BISMILLAH";
        String namaFaskes = faskes.getNamaFaskes();
        String jenisVaksin = faskes.getVaksin().getJenisVaksin();

        model.addAttribute("faskes", faskes);
        model.addAttribute("namaFaskes", namaFaskes);
        model.addAttribute("jenisVaksin", jenisVaksin);
        model.addAttribute("listPasien", pasienService.getPasienList());
        model.addAttribute("listDokter", dokterService.getDokterList());
        model.addAttribute("batchId", batchId);
        model.addAttribute("dokterPasien", new DokterPasienModel());
        return "form-add-pasien_faskes";
    }

    @PostMapping(value = "/faskes/pasien/tambah", params= {"pasien", "idFaskes", "waktuSuntik"})
    public String addPasienFaskesSubmit(
            @ModelAttribute DokterPasienModel dokterPasienModel,
            final BindingResult bindingResult,
            final HttpServletRequest req,
            Model model
    ){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalTime timeSuntik = LocalTime.parse(req.getParameter("waktuSuntik"), formatter);

        LocalTime jamBuka = faskesService.getFaskesByIdFaskes(Long.valueOf(req.getParameter("idFaskes"))).getJamMulai();
        LocalTime jamTutup = faskesService.getFaskesByIdFaskes(Long.valueOf(req.getParameter("idFaskes"))).getJamTutup();

        if(timeSuntik.isAfter(jamTutup) || timeSuntik.isBefore(jamBuka)){
            return "error-pasien-faskes";
        }
        PasienModel pasien = pasienService.getPasienByIdPasien(Long.valueOf(req.getParameter("pasien")));
        String batchId = pasienService.makeBatchId(pasien);
        dokterPasienModel.setIdBatch(batchId);

        FaskesModel faskes = faskesService.getFaskesByIdFaskes(Long.valueOf(req.getParameter("idFaskes")));
        faskes.getListPasien().add(pasien);

        dokterPasienService.addDokterPasien(dokterPasienModel);

        model.addAttribute("namaPasien", pasien.getNamaPasien());
        model.addAttribute("batchIdTemp", dokterPasienModel.getIdBatch());
        return "add-pasien_faskes";
    }

    @RequestMapping(value = "/cari/faskes", method = RequestMethod.GET)
    public String filterFaskes(
            @RequestParam (value="jenisVaksin", required = false) String jenisVaksin,
            Model model
    ){
        List <FaskesModel> listFaskes = faskesService.getListFaskesByJenisVaksin(jenisVaksin);

        model.addAttribute("listFaskes", listFaskes);
        model.addAttribute("listVaksin", vaksinService.getVaksinList());
        return "cari-faskes";
    }



}
