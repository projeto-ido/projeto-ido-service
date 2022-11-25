package school.sptech.ido.domain.model;

import school.sptech.ido.application.controller.dto.Response.EtiquetaDto;

public class Acao {

    private String tipoAcao;

    private EtiquetaDto etiqueta;

    public Acao(String tipoAcao, EtiquetaDto etiqueta) {
        this.tipoAcao = tipoAcao;
        this.etiqueta = etiqueta;
    }

    public String getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(String tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public EtiquetaDto getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(EtiquetaDto etiqueta) {
        this.etiqueta = etiqueta;
    }
}
