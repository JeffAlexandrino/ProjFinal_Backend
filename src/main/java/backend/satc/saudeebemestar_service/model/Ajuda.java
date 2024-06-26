package backend.satc.saudeebemestar_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ajuda {
    private List<Estudante> estudantes = new ArrayList<>();
    private String projeto;
    private String tema;
}
