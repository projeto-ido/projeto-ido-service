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

    public Integer getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Integer idTarefa) {
        this.idTarefa = idTarefa;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
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

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public List<EtiquetaEntity> getEtiquetasTarefa() {
        return etiquetasTarefa;
    }

    public void setEtiquetasTarefa(List<EtiquetaEntity> etiquetasTarefa) {
        this.etiquetasTarefa = etiquetasTarefa;
    }
}
