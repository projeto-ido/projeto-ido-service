package school.sptech.ido.application.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SubTarefaCadastroDto {

    @NotBlank
    @Size(max = 20)
    private String titulo;

    @NotNull
    private Integer prioridade;

}
