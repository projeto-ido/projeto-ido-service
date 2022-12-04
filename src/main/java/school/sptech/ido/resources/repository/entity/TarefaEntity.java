package school.sptech.ido.resources.repository.entity;

import school.sptech.ido.application.controller.dto.Request.TarefaCadastroDto;
import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tarefa")
public class TarefaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarefa;

    @NotBlank
    @Size(max = 45)
    private String titulo;

    @Size(max = 200)
    private String descricao;

    @NotNull
    private Boolean status;

    private LocalDateTime dataInicio;
    
    private LocalDateTime dataFinal;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataConclusao;

    @NotNull
    private Boolean urgencia;

    @NotNull
    private Boolean importancia;

    @OneToMany()
    private List<SubTarefaEntity> subTarefas;

    @ManyToOne
    @JoinColumn(name="fk_usuario", nullable=false)
    private UsuarioEntity usuario;

    @ManyToMany
    @JoinTable(
        name="tarefa_etiqueta",
        joinColumns = @JoinColumn(name = "fk_tarefa"),
        inverseJoinColumns = @JoinColumn(name = "fk_etiqueta")
    )
    private List<EtiquetaEntity> etiquetasTarefa;

    public TarefaEntity() {}

    public TarefaEntity(
        Integer idTarefa,
        String titulo,
        String descricao,
        Boolean status,
        LocalDateTime dataInicio,
        LocalDateTime dataFinal,
        LocalDateTime dataCriacao,
        LocalDateTime dataConclusao,
        Boolean urgencia,
        Boolean importancia,
        List<SubTarefaEntity> subTarefas,
        UsuarioEntity usuario,
        List<EtiquetaEntity> etiquetasTarefa
    ) {
        this.idTarefa = idTarefa;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.dataCriacao = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.urgencia = urgencia;
        this.importancia = importancia;
        this.subTarefas = subTarefas;
        this.usuario = usuario;
        this.etiquetasTarefa = etiquetasTarefa;
    }

    public TarefaEntity(
        TarefaCadastroDto tarefaCadastroDto,
        UsuarioEntity usuario
    ) {
        this.idTarefa = null;
        this.titulo = tarefaCadastroDto.getTitulo();
        this.descricao = tarefaCadastroDto.getDescricao();
        this.status = false;
        this.dataInicio = tarefaCadastroDto.getDataInicio();
        this.dataFinal = tarefaCadastroDto.getDataFinal();
        this.dataCriacao = LocalDateTime.now();
        this.dataConclusao = null;
        this.urgencia = tarefaCadastroDto.getUrgencia();
        this.importancia = tarefaCadastroDto.getImportancia();
        this.subTarefas = new ArrayList<>();
        this.usuario = usuario;
        this.etiquetasTarefa = new ArrayList<>();
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
