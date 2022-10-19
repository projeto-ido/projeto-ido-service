package school.sptech.ido.resources.repository.entity;

import lombok.Data;
import school.sptech.ido.application.controller.dto.TarefaCadastroDto;
import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tarefa")
@Data
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

    private LocalDate dataInicio;

    @FutureOrPresent
    private LocalDate dataFinal;

    private LocalDate dataCriacao;

    private LocalDate dataConclusao;

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

    public TarefaEntity() {}

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
        this.dataCriacao = LocalDate.now();
        this.dataConclusao = null;
        this.urgencia = tarefaCadastroDto.getUrgencia();
        this.importancia = tarefaCadastroDto.getImportancia();
        this.subTarefas = new ArrayList<>();
        this.usuario = usuario;
        this.etiquetasTarefa = new ArrayList<>();
    }
}