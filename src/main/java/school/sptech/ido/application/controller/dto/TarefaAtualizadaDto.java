package school.sptech.ido.application.controller.dto;

import school.sptech.ido.resources.repository.entity.EtiquetaEntity;
import school.sptech.ido.resources.repository.entity.SubTarefaEntity;
import school.sptech.ido.resources.repository.entity.TarefaEntity;

import java.time.LocalDate;
import java.util.List;

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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
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

    public List<SubTarefaEntity> getSubTarefas() {
        return subTarefas;
    }

    public void setSubTarefas(List<SubTarefaEntity> subTarefas) {
        this.subTarefas = subTarefas;
    }

    public List<EtiquetaEntity> getEtiquetasTarefa() {
        return etiquetasTarefa;
    }

    public void setEtiquetasTarefa(List<EtiquetaEntity> etiquetasTarefa) {
        this.etiquetasTarefa = etiquetasTarefa;
    }
}
