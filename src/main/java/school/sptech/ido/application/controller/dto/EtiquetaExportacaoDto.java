package school.sptech.ido.application.controller.dto;

import school.sptech.ido.application.controller.dto.Response.EtiquetaDto;
import school.sptech.ido.resources.repository.entity.EtiquetaEntity;

public class EtiquetaExportacaoDto {

    private String titulo;

    public EtiquetaExportacaoDto() {}

    public EtiquetaExportacaoDto(EtiquetaEntity etiquetaEntity) {
        this.titulo = etiquetaEntity.getTitulo();
    }

    public EtiquetaExportacaoDto(EtiquetaDto etiquetaDto) {
        this.titulo = etiquetaDto.getTitulo();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Etiqueta - %s";
    }
}
