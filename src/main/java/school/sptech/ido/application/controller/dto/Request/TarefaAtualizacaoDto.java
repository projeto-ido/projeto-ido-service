package school.sptech.ido.application.controller.dto.Request;

import school.sptech.ido.application.controller.dto.Response.SubTarefaDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TarefaAtualizacaoDto {

    @NotBlank
    @Size(max = 45)
    private String titulo;

    @Size(max = 200)
    private String descricao;

    @NotNull
    private Boolean status;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFinal;

    private LocalDateTime dataConclusao;

    @NotNull
    private Boolean urgencia;

    @NotNull
    private Boolean importancia;

    private List<SubTarefaDto> subTarefas = new ArrayList<>();

    private List<EtiquetaCadastroTarefaDto> etiquetas = new ArrayList<>();

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

    public List<EtiquetaCadastroTarefaDto> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<EtiquetaCadastroTarefaDto> etiquetas) {
        this.etiquetas = etiquetas;
    }
}
