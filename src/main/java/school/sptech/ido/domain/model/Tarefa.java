package school.sptech.ido.domain.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Tarefa extends Atividade {

    private String titulo;
    private final List<SubTarefa> subtarefas = new ArrayList<>();
    private Date dataInicio;
    private Date dataFinal;
    private Date dataCriacao;
    private final List<Etiqueta> etiquetas = new ArrayList<>();
    private boolean urgencia;
    private boolean importancia;

    public String calcularPrioridade() {
        if (urgencia && importancia) {
            return "Fazer Agora";
        } else if (!urgencia && importancia) {
            return "Agendar";
        } else if (urgencia) {
            return "Delegar";
        }

        return "Não priorizar";
    }

    public Etiqueta adicionarEtiqueta(Etiqueta etiqueta) {
        if (etiquetas.size() < 5) {
            etiqueta.setId(etiquetas.size() + 1);
            etiquetas.add(etiqueta);
            return etiqueta;
        }
        return null;
    }

    public Etiqueta removerEtiqueta(int id) {
        if (id > 0 && id < etiquetas.size()) return etiquetas.remove(id - 1);

        return null;
    }
}
