package school.sptech.ido.resources.repository.entity;

import lombok.Data;
import school.sptech.ido.application.controller.dto.UsuarioAtualizadoDto;
import school.sptech.ido.application.controller.dto.UsuarioCadastroDto;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")

public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @NotBlank
    @Size(max = 45)
    private String nome;

    @NotBlank
    @Size(max = 45)
    private String apelido;

    @NotBlank
    @Size(max = 100)
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Size(max = 45)
    private String senha;

    @Size(max = 200)
    private String biografia;

    @Past
    private LocalDate nascimento;

    @Lob
    @Column(name = "imagem_perfil", columnDefinition="BLOB")
    private Byte[] imagemPerfil;

    private String imagemBiografia;

    @NotNull
    private Integer nivel;

    @NotNull
    private Boolean autenticado;

    @NotNull
    private Boolean notificacao;

    @OneToMany(mappedBy = "usuario")
    private List<TarefaEntity> tarefas;

    @OneToMany(mappedBy = "usuario")
    private List<ConquistaEntity> conquistas;

    @OneToMany(mappedBy = "usuario")
    private List<EtiquetaEntity> etiquetas;

    public UsuarioEntity() {}

    public UsuarioEntity(UsuarioCadastroDto usuarioCadastroDto) {
        this.idUsuario = null;
        this.nome = usuarioCadastroDto.getNome();
        this.apelido = usuarioCadastroDto.getApelido();
        this.email = usuarioCadastroDto.getEmail();
        this.senha = usuarioCadastroDto.getSenha();
        this.biografia = "Bem vindo ao iDo! Você pode editar sua biografia se quiser!";
        this.nascimento = usuarioCadastroDto.getNascimento();
        this.imagemPerfil = null;
        this.imagemBiografia = null;
        this.nivel = 0;
        this.autenticado = false;
        this.notificacao = false;
        this.tarefas = new ArrayList<>();
        this.conquistas = new ArrayList<>();
        this.etiquetas = new ArrayList<>();
    }

    public UsuarioEntity(Integer id, UsuarioAtualizadoDto usuarioAtualizadoDto) {
        this.idUsuario = id;
        this.nome = usuarioAtualizadoDto.getNome();
        this.apelido = usuarioAtualizadoDto.getApelido();
        this.email = usuarioAtualizadoDto.getEmail();
        this.senha = usuarioAtualizadoDto.getSenha();
        this.biografia = usuarioAtualizadoDto.getBiografia();
        this.nascimento = usuarioAtualizadoDto.getNascimento();
        this.imagemPerfil = usuarioAtualizadoDto.getImagemPerfil();
        this.imagemBiografia = usuarioAtualizadoDto.getImagemBiografia();
        this.nivel = usuarioAtualizadoDto.getNivel();
        this.autenticado = true;
        this.notificacao = false;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public Boolean getAutenticado() {
        return autenticado;
    }

    public void setAutenticado(Boolean autenticado) {
        this.autenticado = autenticado;
    }

    public Boolean getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(Boolean notificacao) {
        this.notificacao = notificacao;
    }

    public List<TarefaEntity> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<TarefaEntity> tarefas) {
        this.tarefas = tarefas;
    }

    public List<ConquistaEntity> getConquistas() {
        return conquistas;
    }

    public void setConquistas(List<ConquistaEntity> conquistas) {
        this.conquistas = conquistas;
    }

    public List<EtiquetaEntity> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<EtiquetaEntity> etiquetas) {
        this.etiquetas = etiquetas;
    }
}
