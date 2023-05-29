package school.sptech.ido.application.controller.dto.Response;

import school.sptech.ido.resources.repository.entity.EtiquetaEntity;
import school.sptech.ido.resources.repository.entity.SubTarefaEntity;
import school.sptech.ido.resources.repository.entity.TarefaEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TarefaDto {

    private Integer idTarefa;

    private String titulo;

    private String descricao;

    private Boolean status;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFinal;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataConclusao;

    private Boolean urgencia;

    private Boolean importancia;

    private List<SubTarefaDto> subTarefas;

    private Integer fkUsuario;

    private List<EtiquetaDto> etiquetasTarefa;

    public TarefaDto() {}

    public TarefaDto(TarefaEntity tarefaEntity, List<SubTarefaDto> subTarefaDto, List<EtiquetaDto> etiquetaDtos) {
        this.idTarefa = tarefaEntity.getIdTarefa();
        this.titulo = tarefaEntity.getTitulo();
        this.descricao = tarefaEntity.getDescricao();
        this.status = tarefaEntity.getStatus();
        this.dataInicio = tarefaEntity.getDataInicio();
        this.dataFinal = tarefaEntity.getDataFinal();
        this.dataCriacao = tarefaEntity.getDataCriacao();
        this.dataConclusao = tarefaEntity.getDataConclusao();
        this.urgencia = tarefaEntity.getUrgencia();
        this.importancia = tarefaEntity.getImportancia();
        this.subTarefas = subTarefaDto;
        this.fkUsuario = tarefaEntity.getUsuario().getIdUsuario();
        this.etiquetasTarefa = etiquetaDtos;
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
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

    public List<SubTarefaDto> getSubTarefas() {
        return subTarefas;
    }

    public void setSubTarefas(List<SubTarefaDto> subTarefas) {
        this.subTarefas = subTarefas;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public List<EtiquetaDto> getEtiquetasTarefa() {
        return etiquetasTarefa;
    }

    public void setEtiquetasTarefa(List<EtiquetaDto> etiquetasTarefa) {
        this.etiquetasTarefa = etiquetasTarefa;
    }
}
