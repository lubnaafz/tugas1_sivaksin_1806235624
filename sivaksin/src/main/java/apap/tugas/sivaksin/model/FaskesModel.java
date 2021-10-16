package apap.tugas.sivaksin.model;

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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="faskes")
public class FaskesModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFaskes;

    @NotNull
    @Size(max=255)
    @Column(name = "nama_faskes", nullable = false)
    private String namaFaskes;

    @NotNull
    @Size(max=255)
    @Column(name = "kabupaten", nullable = false)
    private String kabupaten;

    @NotNull
    @Size(max=255)
    @Column(name = "provinsi", nullable = false)
    private String provinsi;

    @NotNull
    @Column(name = "kuota", nullable = false)
    private int kuota;

    @NotNull
    @Column(name = "jam_mulai", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime jamMulai;

    @NotNull
    @Column(name = "jam_tutup", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime jamTutup;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "faskes_pasien",
            joinColumns = @JoinColumn(name = "faskes_id", referencedColumnName = "idFaskes", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "pasien_id", referencedColumnName = "idPasien", nullable = false)
    )
    List<PasienModel> listPasien;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "vaksin_id", referencedColumnName = "idVaksin", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private VaksinModel vaksin;
}
