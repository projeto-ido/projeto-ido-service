package school.sptech.ido.application.controller.dto;

import lombok.Data;
import school.sptech.ido.resources.repository.entity.SubTarefaEntity;

@Data
public class SubTarefaDto {

    private Integer idSubTarefa;

    private String titulo;

    private Boolean status;

    private Integer prioridade;

    private Integer fkTarefa;

    public SubTarefaDto(SubTarefaEntity subTarefaEntity) {
        this.idSubTarefa = subTarefaEntity.getIdSubTarefa();
        this.titulo = subTarefaEntity.getTitulo();
        this.status = subTarefaEntity.getStatus();
        this.prioridade = subTarefaEntity.getPrioridade();
        this.fkTarefa = subTarefaEntity.getTarefa().getIdTarefa();
    }
}
