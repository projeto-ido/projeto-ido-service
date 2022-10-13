package school.sptech.ido.repository.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "etiqueta")
@Data
public class EtiquetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEtiqueta;

    @NotBlank
    @Size(max = 45)
    private String titulo;

    @NotBlank
    @Size(max = 20)
    private String cor;

    @ManyToOne
    @JoinColumn(name="fk_usuario", nullable=false)
    private UsuarioEntity usuario;

    @ManyToMany(mappedBy = "etiquetasTarefa")
    private List<TarefaEntity> tarefa;
}
