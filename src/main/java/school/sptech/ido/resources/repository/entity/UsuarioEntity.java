package school.sptech.ido.resources.repository.entity;

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

    @NotBlank
    @Size(min = 8, max = 11)
    private String telefone;

    @Size(max = 200)
    private String biografia;

    @Past
    private LocalDate nascimento;

    @Lob
    @Column(name = "imagem_perfil", columnDefinition="BLOB")
    private byte[] imagemPerfil;

    @Lob
    @Column(name = "imagem_biografia", columnDefinition="BLOB")
    private byte[] imagemBiografia;

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

    public UsuarioEntity(
        Integer idUsuario,
        String nome,
        String apelido,
        String email,
        String senha,
        String telefone,
        String biografia,
        LocalDate nascimento,
        byte[] imagemPerfil,
        byte[] imagemBiografia,
        Integer nivel,
        Boolean autenticado,
        Boolean notificacao,
        List<TarefaEntity> tarefas,
        List<ConquistaEntity> conquistas,
        List<EtiquetaEntity> etiquetas
    ) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.biografia = biografia;
        this.nascimento = nascimento;
        this.imagemPerfil = imagemPerfil;
        this.imagemBiografia = imagemBiografia;
        this.nivel = nivel;
        this.autenticado = autenticado;
        this.notificacao = notificacao;
        this.tarefas = tarefas;
        this.conquistas = conquistas;
        this.etiquetas = etiquetas;
    }

    public UsuarioEntity(UsuarioCadastroDto usuarioCadastroDto) {
        this.idUsuario = null;
        this.nome = usuarioCadastroDto.getNome();
        this.apelido = usuarioCadastroDto.getApelido();
        this.email = usuarioCadastroDto.getEmail();
        this.senha = usuarioCadastroDto.getSenha();
        this.telefone = usuarioCadastroDto.getTelefone();
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
        this.biografia = usuarioAtualizadoDto.getBiografia();
        this.imagemPerfil = usuarioAtualizadoDto.getImagemPerfil().getBytes();
        this.imagemBiografia = usuarioAtualizadoDto.getImagemBiografia().getBytes();
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

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public byte[] getImagemPerfil() {
        return imagemPerfil;
    }

    public void setImagemPerfil(byte[] imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }

    public byte[] getImagemBiografia() {
        return imagemBiografia;
    }

    public void setImagemBiografia(byte[] imagemBiografia) {
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
