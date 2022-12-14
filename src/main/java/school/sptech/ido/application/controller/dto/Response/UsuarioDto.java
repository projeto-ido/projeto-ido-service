package school.sptech.ido.application.controller.dto.Response;


import school.sptech.ido.resources.repository.entity.UsuarioEntity;

import java.time.LocalDate;

public class UsuarioDto {

    private Integer idUsuario;

    private String nome;

    private String apelido;

    private String email;
    private String telefone;
    private String biografia;

    private LocalDate nascimento;

    private byte[] imagemPerfil;

    private byte[] imagemBiografia;

    private Integer nivel;

    private Boolean autenticado;

    private Boolean notificacao;

    public UsuarioDto() {}

    public UsuarioDto(
        Integer idUsuario,
        String nome,
        String apelido,
        String email,
        String telefone,
        String biografia,
        LocalDate nascimento,
        byte[] imagemPerfil,
        byte[] imagemBiografia,
        Integer nivel,
        Boolean autenticado,
        Boolean notificacao
    ) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.telefone = telefone;
        this.biografia = biografia;
        this.nascimento = nascimento;
        this.imagemPerfil = imagemPerfil;
        this.imagemBiografia = imagemBiografia;
        this.nivel = nivel;
        this.autenticado = autenticado;
        this.notificacao = notificacao;
    }

    public UsuarioDto(UsuarioEntity usuarioEntity) {
        this.idUsuario = usuarioEntity.getIdUsuario();
        this.nome = usuarioEntity.getNome();
        this.apelido = usuarioEntity.getApelido();
        this.email = usuarioEntity.getEmail();
        this.telefone = usuarioEntity.getTelefone();
        this.biografia = usuarioEntity.getBiografia();
        this.nascimento = usuarioEntity.getNascimento();
        this.imagemPerfil = usuarioEntity.getImagemPerfil();
        this.imagemBiografia = usuarioEntity.getImagemBiografia();
        this.nivel = usuarioEntity.getNivel();
        this.autenticado = usuarioEntity.getAutenticado();
        this.notificacao = usuarioEntity.getNotificacao();
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

    public String getTelefone() {
        return telefone;
    }

    public void setSenha(String telefone) {
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
}
