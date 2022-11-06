package school.sptech.ido.resources.repository.entity;

import school.sptech.ido.application.controller.dto.ConquistaDto;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "conquista")
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

    public ConquistaEntity() {}

    public ConquistaEntity(ConquistaDto conquistaDto, UsuarioEntity usuario) {
        this.idConquista = null;
        this.nome = conquistaDto.getNome();
        this.descricao = conquistaDto.getDescricao();
        this.usuario = usuario;
    }

    public Integer getIdConquista() {
        return idConquista;
    }

    public void setIdConquista(Integer idConquista) {
        this.idConquista = idConquista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}
