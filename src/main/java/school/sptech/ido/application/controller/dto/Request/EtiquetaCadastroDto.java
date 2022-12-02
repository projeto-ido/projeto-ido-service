package school.sptech.ido.application.controller.dto.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EtiquetaCadastroDto {

    @NotBlank
    @Size(max = 10)
    private String titulo;

    @NotBlank
    @Size(min = 7, max = 7)
    private String cor;

    public EtiquetaCadastroDto(String titulo, String cor) {
        this.titulo = titulo;
        this.cor = cor;
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
