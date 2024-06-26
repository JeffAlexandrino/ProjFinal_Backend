package backend.satc.saudeebemestar_service.controller;

import backend.satc.saudeebemestar_service.model.Recomendacao;
import backend.satc.saudeebemestar_service.service.RecomendacaoService;
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
@RequestMapping("/recomendacoes")
@Tag(name = "Recomendacao", description = "API para gerenciamento de recomendações")
public class RecomendacaoController {

    @Autowired
    private RecomendacaoService recomendacaoService;

    @Operation(summary = "Adicionar uma nova recomendação", description = "Cria uma nova recomendação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recomendação criada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Recomendacao adicionarRecomendacao(@RequestBody @Valid Recomendacao recomendacao) {
        return recomendacaoService.salvarRecomendacao(recomendacao);
    }

    @Operation(summary = "Consultar recomendação por ID", description = "Retorna uma recomendação pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recomendação encontrada"),
            @ApiResponse(responseCode = "404", description = "Recomendação não encontrada")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Recomendacao consultarRecomendacaoPorId(
            @Parameter(description = "ID da recomendação a ser consultada") @PathVariable("id") Long id) {
        return recomendacaoService.obterRecomendacaoPorId(id);
    }

    @Operation(summary = "Deletar recomendação por ID", description = "Remove uma recomendação pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Recomendação deletada"),
            @ApiResponse(responseCode = "404", description = "Recomendação não encontrada")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletarRecomendacao(
            @Parameter(description = "ID da recomendação a ser deletada") @PathVariable("id") Long id) {
        this.recomendacaoService.deletarRecomendacao(id);
    }

    @Operation(summary = "Listar todas as recomendações", description = "Retorna uma lista paginada de recomendações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de recomendações retornada")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<Recomendacao> listarTodasRecomendacoes(
            @Parameter(description = "Número da página") @PathParam("page") Integer page,
            @Parameter(description = "Tamanho da página") @PathParam("size") Integer size) {
        return this.recomendacaoService.listarTodasRecomendacoes(PageRequest.of(page, size));
    }

    @Operation(summary = "Atualizar recomendação por ID", description = "Atualiza uma recomendação existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recomendação atualizada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Recomendação não encontrada")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Recomendacao atualizarRecomendacao(
            @Parameter(description = "ID da recomendação a ser atualizada") @PathVariable("id") Long id,
            @RequestBody @Valid Recomendacao recomendacao) {
        return this.recomendacaoService.atualizarRecomendacao(id, recomendacao);
    }
}
