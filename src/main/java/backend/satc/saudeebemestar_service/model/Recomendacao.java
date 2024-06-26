package backend.satc.saudeebemestar_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "recomendacao")
public class Recomendacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do paciente é obrigatório")
    @Column(name = "paciente", nullable = false)
    private String paciente;

    @NotBlank(message = "O diagnostico é obrigatório")
    @Column(name = "diagnostico", nullable = false)
    private String diagnostico;

    @NotBlank(message = "A recomendação é obrigatória")
    @Column(name = "recomendacao", nullable = false)
    private String recomendacao;

}
