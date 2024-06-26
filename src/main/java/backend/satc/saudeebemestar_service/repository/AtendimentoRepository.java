package backend.satc.saudeebemestar_service.repository;

import backend.satc.saudeebemestar_service.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
}
