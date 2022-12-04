package school.sptech.ido.application.controller.dto.Response;

import school.sptech.ido.resources.repository.entity.EtiquetaEntity;

public class EtiquetaDto {

    private Integer idEtiqueta;

    private String titulo;

    private String cor;


    public EtiquetaDto() {}

    public EtiquetaDto(Integer idEtiqueta, String titulo, String cor) {
        this.idEtiqueta = idEtiqueta;
        this.titulo = titulo;
        this.cor = cor;
    }

    public EtiquetaDto(EtiquetaEntity etiquetaEntity) {
        this.idEtiqueta = etiquetaEntity.getIdEtiqueta();
        this.titulo = etiquetaEntity.getTitulo();
        this.cor = etiquetaEntity.getCor();
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

}
