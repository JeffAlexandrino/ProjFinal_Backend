package backend.satc.saudeebemestar_service.service;

import backend.satc.saudeebemestar_service.exception.RecomendacaoException;
import backend.satc.saudeebemestar_service.exception.RecomendacaoNaoEncontradaException;
import backend.satc.saudeebemestar_service.model.Recomendacao;
import backend.satc.saudeebemestar_service.repository.RecomendacaoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class RecomendacaoService {

    @Autowired
    private RecomendacaoRepository recomendacaoRepository;

    public Recomendacao salvarRecomendacao(Recomendacao recomendacao) {
        try {
            log.info("Preparando para salvar recomendação do paciente: "+ recomendacao.getPaciente());
            return this.recomendacaoRepository.save(recomendacao);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao salvar a recomendação do atendimento do paciente: "+ recomendacao.getPaciente() + " Erro: "+ e.getMessage());
            throw new RecomendacaoException("Ocorreu um erro ao salvar a recomendação do atendimento do paciente: "+ recomendacao.getPaciente());
        }
    }

    public Page<Recomendacao> listarTodasRecomendacoes(Pageable pageable) {
        log.info("Listando todas as recomendações de forma paginada");
        return this.recomendacaoRepository.findAll(pageable);
    }

    public Recomendacao obterRecomendacaoPorId(Long id) {
        log.info("Preparando para consultar recomendação por id: "+ id);
        Optional<Recomendacao> recomendacaoOpt = this.recomendacaoRepository.findById(id);
        if (recomendacaoOpt.isPresent()) {
            return recomendacaoOpt.get();
        } else {
            throw new RecomendacaoNaoEncontradaException("Recomendacao com id: " + id + " não encontrada");
        }
    }

    public void deletarRecomendacao(Long id) {
        log.info("Preparando para deletar recomendação com id: "+ id);
        Recomendacao recomendacao = obterRecomendacaoPorId(id);
        this.recomendacaoRepository.delete(recomendacao);
    }

    public Recomendacao atualizarRecomendacao(Long id, Recomendacao recomendacao) {
        try {
            log.info("Preparando para atualizar recomendação com ID : "+ id);
            Recomendacao recomendacaoDb = obterRecomendacaoPorId(id);
            recomendacao.setId(recomendacaoDb.getId());
            return this.recomendacaoRepository.save(recomendacao);
        } catch (Exception e) {
            log.error("Ocorreu um erro durante a atualização das informações da recomendação do paciente:  "+ recomendacao.getPaciente() + " Erro: "+ e.getMessage());
            throw new RecomendacaoException("Ocorreu um erro durante a atualização das informações da recomendação do paciente:  "+ recomendacao.getPaciente());
        }

    }
}
