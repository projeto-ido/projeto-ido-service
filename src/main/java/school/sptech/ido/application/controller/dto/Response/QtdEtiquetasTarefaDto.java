package school.sptech.ido.application.controller.dto.Response;

public class QtdEtiquetasTarefaDto {

    private Integer qtdEtiquetaPresente;

    private String titulo;

    public QtdEtiquetasTarefaDto(Integer qtdEtiquetaPresenteNaTarefa, String titulo) {
        this.qtdEtiquetaPresente = qtdEtiquetaPresenteNaTarefa;
        this.titulo = titulo;
    }

    public Integer getQtdEtiquetaPresente() {
        return qtdEtiquetaPresente;
    }

    public void setQtdEtiquetaPresente(Integer qtdEtiquetaPresente) {
        this.qtdEtiquetaPresente = qtdEtiquetaPresente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
