package school.sptech.ido.repository.entity;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tarefa")
public class TarefaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarefa;

    @NotBlank
    @Size(max = 30)
    private String titulo;

    @NotNull
    @Size(max = 200)
    private String descricao;

    @NotNull
    private Boolean status;

    private LocalDate dataInicio;

    @FutureOrPresent
    private LocalDate dataFinal;

    private LocalDate dataCriacao;

    @NotNull
    private Boolean urgencia;

    @NotNull
    private Boolean importancia;

    @OneToMany(mappedBy = "tarefa")
    private List<SubTarefaEntity> subTarefas;

    @ManyToOne
    @JoinColumn(name="fk_usuario", nullable=false)
    private UsuarioEntity usuario;

    @ManyToMany
    @JoinTable(
        name="tarefa_etiqueta",
        joinColumns = @JoinColumn(name = "id_tarefa"),
        inverseJoinColumns = @JoinColumn(name = "id_etiqueta")
    )
    private List<EtiquetaEntity> etiquetasTarefa;

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

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public List<EtiquetaEntity> getEtiquetasTarefa() {
        return etiquetasTarefa;
    }

    public void setEtiquetasTarefa(List<EtiquetaEntity> etiquetasTarefa) {
        this.etiquetasTarefa = etiquetasTarefa;
    }
}
