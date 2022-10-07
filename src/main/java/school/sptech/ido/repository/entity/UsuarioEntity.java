package school.sptech.ido.repository.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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
    @Size(max = 45)
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

    @NotBlank
    private String imagemBiografia;

    @NotNull
    private Integer nivel;

    @OneToMany(mappedBy = "usuario")
    private List<TarefaEntity> tarefas;

    @OneToMany(mappedBy = "usuario")
    private List<ConquistaEntity> conquistas;

    @OneToMany(mappedBy = "usuario")
    private List<EtiquetaEntity> etiquetas;

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
