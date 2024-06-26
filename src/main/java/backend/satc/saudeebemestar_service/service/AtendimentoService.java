package backend.satc.saudeebemestar_service.service;

import backend.satc.saudeebemestar_service.exception.AtendimentoException;
import backend.satc.saudeebemestar_service.exception.AtendimentoNaoEncontradoException;
import backend.satc.saudeebemestar_service.model.Atendimento;
import backend.satc.saudeebemestar_service.repository.AtendimentoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    public Atendimento salvarAtendimento(Atendimento atendimento) {
        try {
            log.info("Preparando para salvar atendimento do paciente: "+ atendimento.getPaciente());
            return this.atendimentoRepository.save(atendimento);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao salvar o atendimento do paciente: "+ atendimento.getPaciente() + " Erro: "+ e.getMessage());
            throw new AtendimentoException("Ocorreu um erro ao salvar o atendimento do paciente: "+ atendimento.getPaciente());
        }
    }

    public Page<Atendimento> listarTodosAtendimentos(Pageable pageable) {
        log.info("Listando todos os atendimentos de forma paginada");
        return this.atendimentoRepository.findAll(pageable);
    }

    public Atendimento obterAtendimentoPorId(Long id) {
        log.info("Preparando para consultar atendimento por id: "+ id);
        Optional<Atendimento> atendimentoOpt = this.atendimentoRepository.findById(id);
        if (atendimentoOpt.isPresent()) {
            return atendimentoOpt.get();
        } else {
            throw new AtendimentoNaoEncontradoException("Atendimento com id: " + id + " não encontrado");
        }
    }

    public void deletarAtendimento(Long id) {
        log.info("Preparando para deletar atendimento com id: "+ id);
        Atendimento atendimento = obterAtendimentoPorId(id);
        this.atendimentoRepository.delete(atendimento);
    }

    public Atendimento atualizarAtendimento(Long id, Atendimento atendimento) {
        try {
            Atendimento atendimentoDb = obterAtendimentoPorId(id);
            log.info("Preparando para atualizar atendimento com ID : "+ id);
            atendimento.setId(atendimentoDb.getId());
            return this.atendimentoRepository.save(atendimento);
        } catch (Exception e) {
            log.error("Ocorreu um erro durante a atualização das informações do atendimento do paciente:  "+ atendimento.getPaciente() + " Erro: "+ e.getMessage());
            throw new AtendimentoException("Ocorreu um erro durante a atualização das informações do atendimento do paciente:  "+ atendimento.getPaciente());
        }
    }
}
