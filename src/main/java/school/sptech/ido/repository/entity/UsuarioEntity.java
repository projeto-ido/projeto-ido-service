package school.sptech.ido.repository.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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
}
