package school.sptech.ido.application.controller.dto.Response;

import school.sptech.ido.resources.repository.entity.UsuarioEntity;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioAtualizadoResDto {


    private String nome;

    private String apelido;

    private String biografia;

    private Byte[] imagemPerfil;

    private String imagemBiografia;

    public UsuarioAtualizadoResDto(UsuarioEntity usuarioEntity) {
        this.nome = usuarioEntity.getNome();
        this.apelido = usuarioEntity.getApelido();
        this.biografia = usuarioEntity.getBiografia();
        this.imagemPerfil = usuarioEntity.getImagemPerfil();
        this.imagemBiografia = usuarioEntity.getImagemBiografia();
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

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public Byte[] getImagemPerfil() {
        return imagemPerfil;
    }

    public void setImagemPerfil(Byte[] imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }

    public String getImagemBiografia() {
        return imagemBiografia;
    }

    public void setImagemBiografia(String imagemBiografia) {
        this.imagemBiografia = imagemBiografia;
    }
}
