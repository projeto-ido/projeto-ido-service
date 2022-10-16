package school.sptech.ido.application.controller.dto;

import lombok.Data;
import school.sptech.ido.resources.repository.entity.EtiquetaEntity;
import school.sptech.ido.resources.repository.entity.SubTarefaEntity;
import school.sptech.ido.resources.repository.entity.TarefaEntity;
import java.time.LocalDate;
import java.util.List;

@Data
public class TarefaDto {

    private Integer idTarefa;

    private String titulo;

    private String descricao;

    private Boolean status;

    private LocalDate dataInicio;

    private LocalDate dataFinal;

    private LocalDate dataCriacao;

    private Boolean urgencia;

    private Boolean importancia;

    private List<SubTarefaEntity> subTarefas;

    private Integer fkUsuario;

    private List<EtiquetaEntity> etiquetasTarefa;

    public TarefaDto() {}

    public TarefaDto(TarefaEntity tarefaEntity) {
        this.idTarefa = tarefaEntity.getIdTarefa();
        this.titulo = tarefaEntity.getTitulo();
        this.descricao = tarefaEntity.getDescricao();
        this.status = tarefaEntity.getStatus();
        this.dataInicio = tarefaEntity.getDataInicio();
        this.dataFinal = tarefaEntity.getDataFinal();
        this.dataCriacao = tarefaEntity.getDataCriacao();
        this.urgencia = tarefaEntity.getUrgencia();
        this.importancia = tarefaEntity.getImportancia();
        this.subTarefas = tarefaEntity.getSubTarefas();
        this.fkUsuario = tarefaEntity.getUsuario().getIdUsuario();
        this.etiquetasTarefa = tarefaEntity.getEtiquetasTarefa();
    }
}
