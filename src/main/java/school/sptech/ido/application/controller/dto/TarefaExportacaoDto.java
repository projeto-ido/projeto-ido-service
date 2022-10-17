package school.sptech.ido.application.controller.dto;

import lombok.Data;
import school.sptech.ido.resources.repository.entity.TarefaEntity;

import java.time.LocalDate;
import java.util.List;

@Data
public class TarefaExportacaoDto {

    private String titulo;

    private String descricao;

    private LocalDate dataInicio;

    private LocalDate dataFinal;

    private Boolean urgencia;

    private Boolean importancia;

    private Boolean status;

    private List<SubTarefaExportacaoDto> subTarefas;

    private List<EtiquetaExportacaoDto> etiquetasTarefa;


    public TarefaExportacaoDto(TarefaEntity tarefaEntity,
                               List<SubTarefaExportacaoDto> subTarefas,
                               List<EtiquetaExportacaoDto> etiquetasTarefa)
    {
        this.titulo = tarefaEntity.getTitulo();
        this.descricao = tarefaEntity.getDescricao();
        this.dataInicio = tarefaEntity.getDataInicio();
        this.dataFinal = tarefaEntity.getDataFinal();
        this.urgencia = tarefaEntity.getUrgencia();
        this.importancia = tarefaEntity.getImportancia();
        this.status = tarefaEntity.getStatus();
        this.subTarefas = subTarefas;
        this.etiquetasTarefa = etiquetasTarefa;
    }
}
