package backend.satc.saudeebemestar_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "medicamento")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do medicamento é obrigatório")
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank(message = "A dosagem do medicamento é obrigatória")
    @Column(name = "dosagem", nullable = false)
    private String dosagem;

    @NotBlank(message = "O nome do fabricante é obrigatório")
    @Column(name = "fabricante", nullable = false)
    private String fabricante;

    @Min(value = 0, message = "A quanditade de estoque minima é 0")
    @NotNull(message = "A quantidade em estoque é obrigatória")
    @Column(name = "qtd_estoque", nullable = false)
    private Integer qtdEstoque;

    @NotNull(message = "A data de validade é obrigatória")
    @Column(name = "data_validade", nullable = false)
    private LocalDate dataValidade;
}
