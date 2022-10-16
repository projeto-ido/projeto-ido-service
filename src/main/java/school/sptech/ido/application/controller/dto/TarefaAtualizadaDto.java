package school.sptech.ido.application.controller.dto;

import lombok.Data;
import school.sptech.ido.resources.repository.entity.EtiquetaEntity;
import school.sptech.ido.resources.repository.entity.SubTarefaEntity;
import school.sptech.ido.resources.repository.entity.TarefaEntity;

import java.time.LocalDate;
import java.util.List;

@Data
public class TarefaAtualizadaDto {

    private String titulo;

    private String descricao;

    private LocalDate dataInicio;

    private LocalDate dataFinal;

    private Boolean urgencia;

    private Boolean importancia;

    private List<SubTarefaEntity> subTarefas;

    private List<EtiquetaEntity> etiquetasTarefa;

    public TarefaAtualizadaDto() {}

    public TarefaAtualizadaDto(TarefaEntity tarefaEntity) {
        this.titulo = tarefaEntity.getTitulo();
        this.descricao = tarefaEntity.getDescricao();
        this.dataInicio = tarefaEntity.getDataInicio();
        this.dataFinal = tarefaEntity.getDataFinal();
        this.urgencia = tarefaEntity.getUrgencia();
        this.importancia = tarefaEntity.getImportancia();
        this.subTarefas = tarefaEntity.getSubTarefas();
        this.etiquetasTarefa = tarefaEntity.getEtiquetasTarefa();
    }
}
