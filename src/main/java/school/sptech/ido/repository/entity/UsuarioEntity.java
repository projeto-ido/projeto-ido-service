package school.sptech.ido.repository.entity;

import lombok.Data;
import school.sptech.ido.application.dto.UsuarioAtualizadoDto;
import school.sptech.ido.application.dto.UsuarioCadastroDto;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
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
}
