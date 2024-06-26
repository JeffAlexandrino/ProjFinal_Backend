package backend.satc.saudeebemestar_service.repository;

import backend.satc.saudeebemestar_service.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}
