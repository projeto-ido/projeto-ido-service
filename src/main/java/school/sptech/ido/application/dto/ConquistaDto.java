package school.sptech.ido.application.dto;

import lombok.Data;
import school.sptech.ido.repository.entity.ConquistaEntity;

import javax.validation.constraints.NotBlank;

@Data
public class ConquistaDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;
}
