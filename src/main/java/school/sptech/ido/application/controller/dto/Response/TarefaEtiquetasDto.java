package school.sptech.ido.application.controller.dto.Response;

import school.sptech.ido.resources.repository.entity.TarefaEntity;

public class TarefaEtiquetasDto {

    private String etiqueta1;

    private String etiqueta2;

    public TarefaEtiquetasDto(TarefaEntity tarefa) {
        this.etiqueta1 = tarefa.getEtiquetasTarefa().size() == 1 ||
                tarefa.getEtiquetasTarefa().size() == 2 ?
                tarefa.getEtiquetasTarefa().get(0).getTitulo() : null;
        this.etiqueta2 = tarefa.getEtiquetasTarefa().size() == 2 ?
                tarefa.getEtiquetasTarefa().get(1).getTitulo() : null;
    }

    public String getEtiqueta1() {
        return etiqueta1;
    }

    public void setEtiqueta1(String etiqueta1) {
        this.etiqueta1 = etiqueta1;
    }

    public String getEtiqueta2() {
        return etiqueta2;
    }

    public void setEtiqueta2(String etiqueta2) {
        this.etiqueta2 = etiqueta2;
    }
}
