package school.sptech.ido.application.controller.dto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioAtualizadoDto {

    @NotBlank
    @Size(max = 45)
    private String nome;

    @NotBlank
    @Size(max = 45)
    private String apelido;

    @NotBlank
    @Size(min = 10, max = 100)
    private String email;

    @NotBlank
    @Size(min = 10, max = 11)
    private String telefone;

    @Size(max = 200)
    private String biografia;

    @Lob
    private Byte[] imagemPerfil;

    private String imagemBiografia;

    public UsuarioAtualizadoDto(
            String nome,
            String apelido,
            String email,
            String telefone,
            String biografia,
            Byte[] imagemPerfil,
            String imagemBiografia
    ) {
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.telefone = telefone;
        this.biografia = biografia;
        this.imagemPerfil = imagemPerfil;
        this.imagemBiografia = imagemBiografia;
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
