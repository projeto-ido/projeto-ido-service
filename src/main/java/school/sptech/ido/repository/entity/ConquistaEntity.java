package school.sptech.ido.repository.entity;

import lombok.Data;
import school.sptech.ido.application.dto.ConquistaDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "conquista")
@Data
public class ConquistaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConquista;

    @NotBlank
    @Size(max = 45)
    private String nome;

    @NotBlank
    @Size(max = 100)
    private String descricao;

    @ManyToOne
    @JoinColumn(name="fk_usuario", nullable=false)
    private UsuarioEntity usuario;

    public ConquistaEntity(ConquistaDto conquistaDto, UsuarioEntity usuario) {
        this.idConquista = null;
        this.nome = conquistaDto.getNome();
        this.descricao = conquistaDto.getDescricao();
        this.usuario = usuario;
    }
}
