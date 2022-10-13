package school.sptech.ido.repository.entity;

import lombok.Data;
import school.sptech.ido.application.dto.SubTarefaCadastroDto;
import school.sptech.ido.application.dto.SubTarefaDto;

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
    private String descricao;

    @NotNull
    private Boolean status;

    @NotNull
    private Integer prioridade;

    @ManyToOne
    @JoinColumn(name="fk_tarefa", nullable=false)
    private TarefaEntity tarefa;

    public SubTarefaEntity(SubTarefaCadastroDto subTarefaCadastroDto, TarefaEntity tarefa) {
        this.idSubTarefa = null;
        this.descricao = subTarefaCadastroDto.getDescricao();
        this.status = false;
        this.prioridade = subTarefaCadastroDto.getPrioridade();
        this.tarefa = tarefa;
    }

    public SubTarefaEntity(SubTarefaDto subTarefaDto, TarefaEntity tarefa) {
        this.idSubTarefa = subTarefaDto.getIdSubTarefa();
        this.descricao = subTarefaDto.getDescricao();
        this.status = subTarefaDto.getStatus();
        this.prioridade = subTarefaDto.getPrioridade();
        this.tarefa = tarefa;
    }
}
