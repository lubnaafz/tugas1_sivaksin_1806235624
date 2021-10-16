package apap.tugas.sivaksin.model;

import apap.tugas.sivaksin.service.FaskesService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="dokter_pasien")
public class DokterPasienModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDokterPasien;

    @NotNull
    @Size(max=15)
    @Column(name = "batchId", nullable = false)
    private String idBatch;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pasienId", referencedColumnName = "idPasien", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PasienModel pasien;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "dokterId", referencedColumnName = "idDokter", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DokterModel dokter;

    @NotNull
    @Column(name = "waktu_suntik", nullable = false)
    @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktuSuntik;

    @NotNull
    @Column(name = "faskesId", nullable = false)
    private Long idFaskes;

    @NotNull
    @Size(max=255)
    @Column(name = "faskesNama", nullable = false)
    private String namaFaskes;

    @NotNull
    @Size(max=255)
    @Column(name = "vaksinJenis", nullable = false)
    private String jenisVaksin;



}
