package school.sptech.ido.application.controller.dto;

import school.sptech.ido.resources.repository.entity.EtiquetaEntity;

public class EtiquetaDto {

    private Integer idEtiqueta;

    private String titulo;

    private String cor;

    private Integer fkUsuario;

    public EtiquetaDto() {}

    public EtiquetaDto(EtiquetaEntity etiquetaEntity) {
        this.idEtiqueta = etiquetaEntity.getIdEtiqueta();
        this.titulo = etiquetaEntity.getTitulo();
        this.cor = etiquetaEntity.getCor();
        this.fkUsuario = etiquetaEntity.getUsuario().getIdUsuario();
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

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
