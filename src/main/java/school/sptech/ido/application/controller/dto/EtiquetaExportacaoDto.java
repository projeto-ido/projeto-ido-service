package school.sptech.ido.application.controller.dto;

import school.sptech.ido.resources.repository.entity.EtiquetaEntity;

public class EtiquetaExportacaoDto {

    private String titulo;

    public EtiquetaExportacaoDto(EtiquetaEntity etiquetaEntity) {
        this.titulo = etiquetaEntity.getTitulo();
    }

    @Override
    public String toString() {
        return "Etiqueta - %s";
    }
}
