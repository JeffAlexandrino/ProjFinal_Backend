package backend.satc.saudeebemestar_service.controller;

import backend.satc.saudeebemestar_service.model.Ajuda;
import backend.satc.saudeebemestar_service.model.Estudante;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ajuda")
@Tag(name = "Ajuda", description = "Retorna as informações do projeto")
public class AjudaController {

    @Operation(summary = "Obter ajuda", description = "Retorna informações sobre o projeto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ajuda obtida com sucesso")
    })
    @GetMapping
    public Ajuda obterAjuda() {

        Ajuda ajuda = new Ajuda();
        ajuda.setTema("Saude e Bem Estar");
        ajuda.setProjeto("Saude e Bem Estar Service");

        Estudante estudante1 = new Estudante();
        estudante1.setNome("GABRIEL DE OLIVEIRA RODRIGUES");

        Estudante estudante2 = new Estudante();
        estudante2.setNome("JEFFERSON BARZAN ALEXANDRINO");

        Estudante estudante3 = new Estudante();
        estudante3.setNome("RAUL FONSECA DE CASTRO PEREIRA");

        ajuda.setEstudantes(List.of(estudante1, estudante2, estudante3));

        return ajuda;
    }
}
