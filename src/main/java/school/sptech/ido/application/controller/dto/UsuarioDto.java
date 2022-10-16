package school.sptech.ido.application.controller.dto;

import lombok.Data;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class UsuarioDto {

    private Integer idUsuario;

    private String nome;

    private String apelido;

    private String email;

    private String senha;

    private String biografia;

    private LocalDate nascimento;

    private Byte[] imagemPerfil;

    private String imagemBiografia;

    private Integer nivel;

    private Boolean notificacao;

    public UsuarioDto(UsuarioEntity usuarioEntity) {
        this.idUsuario = usuarioEntity.getIdUsuario();
        this.nome = usuarioEntity.getNome();
        this.apelido = usuarioEntity.getApelido();
        this.email = usuarioEntity.getEmail();
        this.senha = usuarioEntity.getSenha();
        this.biografia = usuarioEntity.getBiografia();
        this.nascimento = usuarioEntity.getNascimento();
        this.imagemPerfil = usuarioEntity.getImagemPerfil();
        this.imagemBiografia = usuarioEntity.getImagemBiografia();
        this.nivel = usuarioEntity.getNivel();
        this.notificacao = usuarioEntity.getNotificacao();
    }
}
