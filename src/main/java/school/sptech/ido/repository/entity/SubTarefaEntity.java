package school.sptech.ido.repository.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sub_tarefa")
public class SubTarefaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSubTarefa;

    @NotNull
    @Size(max = 200)
    private String descricao;

    @NotNull
    private Boolean status;

    @NotNull
    private Integer prioridade;

    @ManyToOne
    @JoinColumn(name="fk_tarefa", nullable=false)
    private TarefaEntity tarefa;

    public Integer getIdSubTarefa() {
        return idSubTarefa;
    }

    public void setIdSubTarefa(Integer idSubTarefa) {
        this.idSubTarefa = idSubTarefa;
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

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public TarefaEntity getTarefa() {
        return tarefa;
    }

    public void setTarefa(TarefaEntity tarefa) {
        this.tarefa = tarefa;
    }
}
