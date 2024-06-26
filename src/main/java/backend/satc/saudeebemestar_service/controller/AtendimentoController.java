package backend.satc.saudeebemestar_service.controller;

import backend.satc.saudeebemestar_service.model.Atendimento;
import backend.satc.saudeebemestar_service.service.AtendimentoService;
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
@RequestMapping("/atendimentos")
@Tag(name = "Atendimento", description = "API para gerenciamento de atendimentos")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @Operation(summary = "Adicionar um novo atendimento", description = "Cria um novo atendimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Atendimento criado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Atendimento adicionarAtendimento(@RequestBody @Valid Atendimento atendimento) {
        return atendimentoService.salvarAtendimento(atendimento);
    }

    @Operation(summary = "Consultar atendimento por ID", description = "Retorna um atendimento pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atendimento encontrado"),
            @ApiResponse(responseCode = "404", description = "Atendimento não encontrado")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Atendimento consultarMedicamentoPorId(
            @Parameter(description = "ID do atendimento a ser consultado") @PathVariable("id") Long id) {
        return atendimentoService.obterAtendimentoPorId(id);
    }

    @Operation(summary = "Deletar atendimento por ID", description = "Remove um atendimento pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Atendimento deletado"),
            @ApiResponse(responseCode = "404", description = "Atendimento não encontrado")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletarAtendimento(
            @Parameter(description = "ID do atendimento a ser deletado") @PathVariable("id") Long id) {
        this.atendimentoService.deletarAtendimento(id);
    }

    @Operation(summary = "Listar todos os atendimentos", description = "Retorna uma lista paginada de atendimentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de atendimentos retornada")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<Atendimento> listarTodosMedicamentos(
            @Parameter(description = "Número da página") @PathParam("page") Integer page,
            @Parameter(description = "Tamanho da página") @PathParam("size") Integer size) {
        return this.atendimentoService.listarTodosAtendimentos(PageRequest.of(page, size));
    }

    @Operation(summary = "Atualizar atendimento por ID", description = "Atualiza um atendimento existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atendimento atualizado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Atendimento não encontrado")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Atendimento atualizarMedicamento(
            @Parameter(description = "ID do atendimento a ser atualizado") @PathVariable("id") Long id,
            @RequestBody @Valid Atendimento atendimento) {
        return this.atendimentoService.atualizarAtendimento(id, atendimento);
    }

}
