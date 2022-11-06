package school.sptech.ido.application.controller.dto.Request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class SubTarefaCadastroDto {

    @NotBlank
    @Size(max = 20)
    private String titulo;

    @NotNull
    private boolean status;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
