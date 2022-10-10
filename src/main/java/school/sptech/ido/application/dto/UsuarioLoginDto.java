package school.sptech.ido.application.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UsuarioLoginDto {

    @NotBlank
    @Size(max = 45)
    @Email
    private String email;

    @NotBlank
    @Size(max = 45)
    private String senha;
}
