package school.sptech.ido.resources.repository.entity;

import school.sptech.ido.application.controller.dto.Response.EtiquetaDto;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "etiqueta")
public class EtiquetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEtiqueta;

    @NotBlank
    @Size(max = 45)
    private String titulo;

    @NotBlank
    @Size(max = 20)
    private String cor;

    @ManyToOne
    @JoinColumn(name="fk_usuario", nullable=false)
    private UsuarioEntity usuario;

    @ManyToMany(mappedBy = "etiquetasTarefa")
    private List<TarefaEntity> tarefa;

    public EtiquetaEntity() {}

    public EtiquetaEntity(
        Integer idEtiqueta,
        String titulo,
        String cor,
        UsuarioEntity usuario,
        List<TarefaEntity> tarefa
    ) {
        this.idEtiqueta = idEtiqueta;
        this.titulo = titulo;
        this.cor = cor;
        this.usuario = usuario;
        this.tarefa = tarefa;
    }

    public EtiquetaEntity(EtiquetaDto etiquetaDto, UsuarioEntity usuario) {
        this.idEtiqueta = etiquetaDto.getIdEtiqueta();
        this.titulo = etiquetaDto.getTitulo();
        this.cor = etiquetaDto.getCor();
        this.usuario = usuario;
        this.tarefa = new ArrayList<>();
    }

    public Integer getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(Integer idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public List<TarefaEntity> getTarefa() {
        return tarefa;
    }

    public void setTarefa(List<TarefaEntity> tarefa) {
        this.tarefa = tarefa;
    }
}
