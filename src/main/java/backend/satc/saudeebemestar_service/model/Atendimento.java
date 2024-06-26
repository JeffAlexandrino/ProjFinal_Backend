package backend.satc.saudeebemestar_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "atendimento")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do paciente não pode ser nulo ou vazio")
    @Column(name = "paciente", nullable = false)
    private String paciente;

    @Column(name = "data", nullable = false)
    @NotNull(message = "A data do atendimento é obrigatória")
    private LocalDate data;

    @Column(name = "medico", nullable = false)
    @NotBlank(message = "O nome do medico não pode ser nulo ou vazio")
    private String medico;
}
