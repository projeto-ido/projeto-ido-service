package school.sptech.ido.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.ido.application.controller.dto.Response.UsuarioDto;
import school.sptech.ido.resources.repository.entity.TarefaEntity;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByEmailAndSenha(String email, String senha);


    @Query("SELECT new school.sptech.ido.application.controller.dto.Response.UsuarioDto(u)" +
            " FROM UsuarioEntity u where u.idUsuario = ?1")
    Optional<UsuarioDto> getusuarioDto(int idUsuario);
}
