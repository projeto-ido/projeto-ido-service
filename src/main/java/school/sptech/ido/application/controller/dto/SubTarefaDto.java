package school.sptech.ido.application.controller.dto;

import school.sptech.ido.resources.repository.entity.SubTarefaEntity;

public class SubTarefaDto {

    private Integer idSubTarefa;

    private String titulo;

    private Boolean status;


    private Integer fkTarefa;

    public SubTarefaDto() {}

    public SubTarefaDto(SubTarefaEntity subTarefaEntity) {
        this.idSubTarefa = subTarefaEntity.getIdSubTarefa();
        this.titulo = subTarefaEntity.getTitulo();
        this.status = subTarefaEntity.getStatus();
        this.fkTarefa = subTarefaEntity.getTarefa().getIdTarefa();
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

    public Integer getFkTarefa() {
        return fkTarefa;
    }

    public void setFkTarefa(Integer fkTarefa) {
        this.fkTarefa = fkTarefa;
    }
}
