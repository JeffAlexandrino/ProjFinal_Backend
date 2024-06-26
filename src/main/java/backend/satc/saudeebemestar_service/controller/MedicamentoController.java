package backend.satc.saudeebemestar_service.controller;

import backend.satc.saudeebemestar_service.model.Medicamento;
import backend.satc.saudeebemestar_service.service.MedicamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicamentos")
@Tag(name = "Medicamento", description = "API para gerenciamento de medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @Operation(summary = "Adicionar um novo medicamento", description = "Cria um novo medicamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Medicamento criado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Medicamento adicionarMedicamento(@RequestBody @Valid Medicamento medicamento) {
        return medicamentoService.salvarMedicamento(medicamento);
    }

    @Operation(summary = "Consultar medicamento por ID", description = "Retorna um medicamento pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medicamento encontrado"),
            @ApiResponse(responseCode = "404", description = "Medicamento não encontrado")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Medicamento consultarMedicamentoPorId(
            @Parameter(description = "ID do medicamento a ser consultado") @PathVariable("id") Long id) {
        return medicamentoService.obterMedicamentoPorId(id);
    }

    @Operation(summary = "Deletar medicamento por ID", description = "Remove um medicamento pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Medicamento deletado"),
            @ApiResponse(responseCode = "404", description = "Medicamento não encontrado")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletarMedicamento(
            @Parameter(description = "ID do medicamento a ser deletado") @PathVariable("id") Long id) {
        this.medicamentoService.deletarMedicamento(id);
    }

    @Operation(summary = "Listar todos os medicamentos", description = "Retorna uma lista paginada de medicamentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de medicamentos retornada")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<Medicamento> listarTodosMedicamentos(
            @Parameter(description = "Número da página") @PathParam("page") Integer page,
            @Parameter(description = "Tamanho da página") @PathParam("size") Integer size) {
        return this.medicamentoService.listarTodosMedicamentos(PageRequest.of(page, size));
    }

    @Operation(summary = "Atualizar medicamento por ID", description = "Atualiza um medicamento existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medicamento atualizado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Medicamento não encontrado")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Medicamento atualizarMedicamento(
            @Parameter(description = "ID do medicamento a ser atualizado") @PathVariable("id") Long id,
            @RequestBody @Valid Medicamento medicamento) {
        return this.medicamentoService.atualizarMedicamento(id, medicamento);
    }

}
