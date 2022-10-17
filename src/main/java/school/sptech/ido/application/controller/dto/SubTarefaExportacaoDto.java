package school.sptech.ido.application.controller.dto;

import school.sptech.ido.resources.repository.entity.SubTarefaEntity;

public class SubTarefaExportacaoDto {

    private String titulo;

    private Boolean status;

    private Integer prioridade;

    public SubTarefaExportacaoDto(SubTarefaEntity subTarefaEntity) {
        this.titulo = subTarefaEntity.getTitulo();
        this.status = subTarefaEntity.getStatus();
        this.prioridade = subTarefaEntity.getPrioridade();
    }

    @Override
    public String toString() {
        return String.format(
                "Subtarefa - %s, %b, %d");
    }
}
