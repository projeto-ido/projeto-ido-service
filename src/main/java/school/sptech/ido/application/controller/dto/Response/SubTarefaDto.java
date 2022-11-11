package school.sptech.ido.application.controller.dto.Response;

import school.sptech.ido.resources.repository.entity.SubTarefaEntity;

public class SubTarefaDto {

    private Integer idSubTarefa;

    private String titulo;

    private Boolean status;

    public SubTarefaDto() {}

    public SubTarefaDto(SubTarefaEntity subTarefaEntity) {
        this.idSubTarefa = subTarefaEntity.getIdSubTarefa();
        this.titulo = subTarefaEntity.getTitulo();
        this.status = subTarefaEntity.getStatus();
    }

    public Integer getIdSubTarefa() {
        return idSubTarefa;
    }

    public void setIdSubTarefa(Integer idSubTarefa) {
        this.idSubTarefa = idSubTarefa;
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

}
