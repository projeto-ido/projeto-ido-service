package school.sptech.ido.application.controller.dto;

import school.sptech.ido.resources.repository.entity.TarefaEntity;

import java.time.LocalDate;
import java.util.List;

public class TarefaExportacaoDto {

    private Integer idTarefa;

    private String titulo;

    private String descricao;

    private LocalDate dataInicio;

    private LocalDate dataFinal;

    private Boolean urgencia;

    private Boolean importancia;

    private Boolean status;

    private List<SubTarefaExportacaoDto> subTarefas;

    private String etiqueta1;

    private String etiqueta2;

    public TarefaExportacaoDto() {}

    public TarefaExportacaoDto(TarefaEntity tarefaEntity,
                               List<SubTarefaExportacaoDto> subTarefas,
                               String etiqueta1,
                               String etiqueta2 )
    {
        this.idTarefa = tarefaEntity.getIdTarefa();
        this.titulo = tarefaEntity.getTitulo();
        this.descricao = tarefaEntity.getDescricao();
        this.dataInicio = tarefaEntity.getDataInicio();
        this.dataFinal = tarefaEntity.getDataFinal();
        this.urgencia = tarefaEntity.getUrgencia();
        this.importancia = tarefaEntity.getImportancia();
        this.status = tarefaEntity.getStatus();
        this.subTarefas = subTarefas;
        this.etiqueta1 = etiqueta1;
        this.etiqueta2 = etiqueta2;
    }

    public String getPrioridade(){
        if (importancia && urgencia)
            return "Fazer Agora";
        else if (!importancia && urgencia)
            return "Delegar";
        else if (importancia && !urgencia)
            return "Agendar";
        else
            return "Não priorizar";
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
