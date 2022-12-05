package school.sptech.ido.application.controller.dto;


import javax.validation.constraints.*;
import java.time.LocalDate;

public class UsuarioCadastroDto {

    @NotBlank
    @Size(max = 45)
    private String nome;

    @NotBlank
    @Size(max = 45)
    private String apelido;

    @Past
    private LocalDate nascimento;

    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    @NotBlank
    @Size(max = 45)
    private String senha;

    @NotBlank
    @Size(min = 8, max = 11)
    private String telefone;

    public UsuarioCadastroDto() {

    }

    public UsuarioCadastroDto(String nome, String apelido, LocalDate nascimento, String email, String senha, String telefone) {
        this.nome = nome;
        this.apelido = apelido;
        this.nascimento = nascimento;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
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

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
