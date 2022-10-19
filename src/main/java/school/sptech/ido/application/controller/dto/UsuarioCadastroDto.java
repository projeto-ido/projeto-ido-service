package school.sptech.ido.application.controller.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class UsuarioCadastroDto {

    @NotBlank
    @Size(max = 45)
    private String nome;

    @NotBlank
    @Size(max = 45)
    private String apelido;

    @Past
    private LocalDate nascimento;

    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    @NotBlank
    @Size(max = 45)
    private String senha;
}
