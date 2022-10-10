package school.sptech.ido.application.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class TarefaCadastroDto {

    @NotBlank
    @Size(max = 30)
    private String titulo;

    private LocalDate dataInicio;

    private LocalDate dataFinal;

    @NotNull
    private Boolean urgencia;

    @NotNull
    private Boolean importancia;
}
