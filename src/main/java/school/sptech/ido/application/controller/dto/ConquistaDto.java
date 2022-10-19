package school.sptech.ido.application.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ConquistaDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;
}
