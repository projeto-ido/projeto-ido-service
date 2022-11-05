package school.sptech.ido.application.controller.dto;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UsuarioAtualizadoDto {

    @NotBlank
    @Size(max = 45)
    private String nome;

    @NotBlank
    @Size(max = 45)
    private String apelido;

    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(max = 45)
    private String senha;

    @Size(max = 200)
    private String biografia;

    @Past
    private LocalDate nascimento;

    @Lob
    private Byte[] imagemPerfil;

    private String imagemBiografia;

    @NotNull
    private Integer nivel;

    @NotNull
    private Boolean notificacao;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
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

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Boolean getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(Boolean notificacao) {
        this.notificacao = notificacao;
    }
}
