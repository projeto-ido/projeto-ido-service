package school.sptech.ido.application.controller.dto;

import lombok.Data;
import school.sptech.ido.resources.repository.entity.EtiquetaEntity;

@Data
public class EtiquetaDto {

    private Integer idEtiqueta;

    private String titulo;

    private String cor;

    private Integer fkUsuario;

    public EtiquetaDto(EtiquetaEntity etiquetaEntity) {
        this.idEtiqueta = etiquetaEntity.getIdEtiqueta();
        this.titulo = etiquetaEntity.getTitulo();
        this.cor = etiquetaEntity.getCor();
        this.fkUsuario = etiquetaEntity.getUsuario().getIdUsuario();
    }
}
