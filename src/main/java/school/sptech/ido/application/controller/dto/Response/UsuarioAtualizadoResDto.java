package school.sptech.ido.application.controller.dto.Response;

import school.sptech.ido.resources.repository.entity.UsuarioEntity;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Base64;

public class UsuarioAtualizadoResDto {

    private String nome;

    private String apelido;

    private String email;

    private String telefone;

    private String biografia;

    private String imagemPerfil;

    private String imagemBiografia;

    public UsuarioAtualizadoResDto(UsuarioEntity usuarioEntity) {
        this.nome = usuarioEntity.getNome();
        this.apelido = usuarioEntity.getApelido();
        this.email = usuarioEntity.getEmail();
        this.telefone = usuarioEntity.getTelefone();
        this.biografia = usuarioEntity.getBiografia();
        this.imagemPerfil = Base64.getEncoder().encodeToString(usuarioEntity.getImagemPerfil());
        this.imagemBiografia = Base64.getEncoder().encodeToString(usuarioEntity.getImagemBiografia());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getImagemPerfil() {
        return imagemPerfil;
    }

    public void setImagemPerfil(String imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }

    public String getImagemBiografia() {
        return imagemBiografia;
    }

    public void setImagemBiografia(String imagemBiografia) {
        this.imagemBiografia = imagemBiografia;
    }
}
