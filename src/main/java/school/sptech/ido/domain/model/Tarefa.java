package school.sptech.ido.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tarefa extends Atividade {

    @Getter @Setter private String titulo;
    @Getter private final List<SubTarefa> subtarefas = new ArrayList<>();
    @Getter @Setter private Date dataInicio;
    @Getter @Setter private Date dataFinal;
    @Getter @Setter private Date dataCriacao;
    @Getter private final List<Etiqueta> etiquetas = new ArrayList<>();
    @Getter @Setter private boolean urgencia;
    @Getter @Setter private boolean importancia;

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
