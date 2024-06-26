package backend.satc.saudeebemestar_service.service;

import backend.satc.saudeebemestar_service.exception.MedicamentoException;
import backend.satc.saudeebemestar_service.exception.MedicamentoNaoEncontradoException;
import backend.satc.saudeebemestar_service.model.Medicamento;
import backend.satc.saudeebemestar_service.repository.MedicamentoRepository;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public Medicamento salvarMedicamento(Medicamento medicamento) {
        try {
            log.info("Preparando para salvar medicamento: "+ medicamento.getNome());
            return this.medicamentoRepository.save(medicamento);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao salvar o medicamento: "+ medicamento.getNome() + " Erro: "+ e.getMessage());
            throw new MedicamentoException("Ocorreu um erro ao salvar o medicamento: "+ medicamento.getNome());
        }
    }

    public Page<Medicamento> listarTodosMedicamentos(Pageable pageable) {
        log.info("Listando todos os medicamentos de forma paginada");
        return this.medicamentoRepository.findAll(pageable);
    }

    public Medicamento obterMedicamentoPorId(Long id) {
        log.info("Preparando para consultar medicamento por id: "+ id);
        Optional<Medicamento> medicamentoOpt = this.medicamentoRepository.findById(id);
        if (medicamentoOpt.isPresent()) {
            return medicamentoOpt.get();
        } else {
            throw new MedicamentoNaoEncontradoException("Medicamento com id: " + id + " não encontrado");
        }
    }

    public void deletarMedicamento(Long id) {
        log.info("Preparando para deletar medicamento com id: "+ id);
        Medicamento medicamento = obterMedicamentoPorId(id);
        this.medicamentoRepository.delete(medicamento);
    }

    public Medicamento atualizarMedicamento(Long id, Medicamento medicamento) {
        try {
            log.info("Preparando para atualizar medicamento com ID : "+ id);
            Medicamento medicamentoDb = obterMedicamentoPorId(id);
            medicamento.setId(medicamentoDb.getId());
            return this.medicamentoRepository.save(medicamento);
        } catch (Exception e) {
            log.error("Ocorreu um erro durante a atualização das informações do medicamento:  "+ medicamento.getNome() + " Erro: "+ e.getMessage());
            throw new MedicamentoException("Ocorreu um erro durante a atualização das informações do medicamento:  "+ medicamento.getNome());
        }
    }
}
