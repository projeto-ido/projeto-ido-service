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

    public SubTarefa adicionarSubTarefa(SubTarefa subTarefa) {
        if (subtarefas.size() < 5) {
            subTarefa.setId(subtarefas.size() + 1);
            subtarefas.add(subTarefa);
            return subTarefa;
        }
        return null;
    }

    public SubTarefa removerSubTarefa(int id) {
        if (id > 0 && id <= subtarefas.size()) return subtarefas.remove(id - 1);

        return null;
    }

    public SubTarefa editarSubtarefa(int id, SubTarefa subTarefa) {
        if (id > 0 && id <= subtarefas.size()) {
            subtarefas.set(id - 1, subTarefa);
            return subtarefas.get(id - 1);
        }
        return null;
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
