package school.sptech.ido.application.controller.dto;

import school.sptech.ido.resources.repository.entity.SubTarefaEntity;

public class SubTarefaExportacaoDto {

    private String titulo;

    private Boolean status;

    public SubTarefaExportacaoDto() {}

    public SubTarefaExportacaoDto(SubTarefaEntity subTarefaEntity) {
        this.titulo = subTarefaEntity.getTitulo();
        this.status = subTarefaEntity.getStatus();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format(
                "Subtarefa - %s, %b", this.titulo, this.status);
    }
}
