package school.sptech.ido.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByEmailAndSenha(String email, String senha);
}
