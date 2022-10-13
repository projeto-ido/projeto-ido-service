package school.sptech.ido.repository.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sub_tarefa")
@Data
public class SubTarefaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSubTarefa;

    @NotBlank
    @Size(max = 20)
    private String titulo;

    @NotNull
    private Boolean status;

    @NotNull
    private Integer prioridade;

    @ManyToOne
    @JoinColumn(name="fk_tarefa", nullable=false)
    private TarefaEntity tarefa;

    public SubTarefaEntity(SubTarefaCadastroDto subTarefaCadastroDto, TarefaEntity tarefa) {
        this.idSubTarefa = null;
        this.titulo = subTarefaCadastroDto.getTitulo();
        this.status = false;
        this.prioridade = subTarefaCadastroDto.getPrioridade();
        this.tarefa = tarefa;
    }

    public SubTarefaEntity(SubTarefaDto subTarefaDto, TarefaEntity tarefa) {
        this.idSubTarefa = subTarefaDto.getIdSubTarefa();
        this.titulo = subTarefaDto.getTitulo();
        this.status = subTarefaDto.getStatus();
        this.prioridade = subTarefaDto.getPrioridade();
        this.tarefa = tarefa;
    }
}
