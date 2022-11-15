package school.sptech.ido.application.controller.dto;

import school.sptech.ido.resources.repository.entity.TarefaEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TarefaExportacaoDto {

    private String titulo;

    private String descricao;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFinal;

    private Boolean urgencia;

    private Boolean importancia;

    private Boolean status;

    private List<SubTarefaExportacaoDto> subTarefas;

    private List<EtiquetaExportacaoDto> etiquetasTarefa;

    public TarefaExportacaoDto() {}

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDateTime dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Boolean getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(Boolean urgencia) {
        this.urgencia = urgencia;
    }

    public Boolean getImportancia() {
        return importancia;
    }

    public void setImportancia(Boolean importancia) {
        this.importancia = importancia;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<SubTarefaExportacaoDto> getSubTarefas() {
        return subTarefas;
    }

    public void setSubTarefas(List<SubTarefaExportacaoDto> subTarefas) {
        this.subTarefas = subTarefas;
    }

    public List<EtiquetaExportacaoDto> getEtiquetasTarefa() {
        return etiquetasTarefa;
    }

    public void setEtiquetasTarefa(List<EtiquetaExportacaoDto> etiquetasTarefa) {
        this.etiquetasTarefa = etiquetasTarefa;
    }
}
