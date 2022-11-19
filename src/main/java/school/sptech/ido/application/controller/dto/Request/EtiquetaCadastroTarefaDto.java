package school.sptech.ido.application.controller.dto.Request;

import javax.validation.constraints.NotNull;

public class EtiquetaCadastroTarefaDto {

    @NotNull
    private Integer idEtiqueta;

    public Integer getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(Integer idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }
}
