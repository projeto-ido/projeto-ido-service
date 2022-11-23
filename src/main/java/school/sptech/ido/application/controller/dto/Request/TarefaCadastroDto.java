package school.sptech.ido.application.controller.dto.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TarefaCadastroDto {

    @NotBlank
    @Size(max = 45)
    private String titulo;

    @Size(max = 200)
    private String descricao;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFinal;

    @NotNull
    private Boolean urgencia;

    @NotNull
    private Boolean importancia;

    private List<SubTarefaCadastroDto> subTarefas = new ArrayList<>();

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

    public List<SubTarefaCadastroDto> getSubTarefas() {
        return subTarefas;
    }

    public void setSubTarefas(List<SubTarefaCadastroDto> subTarefas) {
        this.subTarefas = subTarefas;
    }

    public List<EtiquetaCadastroTarefaDto> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<EtiquetaCadastroTarefaDto> etiquetas) {
        this.etiquetas = etiquetas;
    }
}
