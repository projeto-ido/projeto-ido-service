package school.sptech.ido.resources.repository.entity;

import school.sptech.ido.application.controller.dto.Request.SubTarefaCadastroDto;
import school.sptech.ido.application.controller.dto.Response.SubTarefaDto;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sub_tarefa")
public class SubTarefaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSubTarefa;

    @NotBlank
    @Size(max = 20)
    private String titulo;

    @NotNull
    private Boolean status;

    @ManyToOne
    @JoinColumn(name="fk_tarefa", nullable=false)
    private TarefaEntity tarefa;

    public SubTarefaEntity() {}

    public SubTarefaEntity(Integer idSubTarefa, String titulo, Boolean status, TarefaEntity tarefa) {
        this.idSubTarefa = idSubTarefa;
        this.titulo = titulo;
        this.status = status;
        this.tarefa = tarefa;
    }

    public SubTarefaEntity(String titulo , Boolean status, TarefaEntity tarefa) {
        this.titulo = titulo;
        this.status = status;
        this.tarefa = tarefa;
    }

    public SubTarefaEntity(SubTarefaCadastroDto subTarefaCadastroDto, TarefaEntity tarefa) {
        this.idSubTarefa = null;
        this.titulo = subTarefaCadastroDto.getTitulo();
        this.status = subTarefaCadastroDto.isStatus();
        this.tarefa = tarefa;
    }

    public SubTarefaEntity(SubTarefaDto subTarefaDto, TarefaEntity tarefa) {
        this.idSubTarefa = subTarefaDto.getIdSubTarefa();
        this.titulo = subTarefaDto.getTitulo();
        this.status = subTarefaDto.getStatus();
        this.tarefa = tarefa;
    }

    public SubTarefaEntity(SubTarefaCadastroDto subTarefaCadastroDto) {
        this.idSubTarefa = null;
        this.titulo = subTarefaCadastroDto.getTitulo();
        this.status = subTarefaCadastroDto.isStatus();
    }

    public Integer getIdSubTarefa() {
        return idSubTarefa;
    }

    public void setIdSubTarefa(Integer idSubTarefa) {
        this.idSubTarefa = idSubTarefa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public TarefaEntity getTarefa() {
        return tarefa;
    }

    public void setTarefa(TarefaEntity tarefa) {
        this.tarefa = tarefa;
    }
}
