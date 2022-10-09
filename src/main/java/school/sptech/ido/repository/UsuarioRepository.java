package school.sptech.ido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.ido.repository.entity.UsuarioEntity;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    @Query(value = "SELECT u.* FROM usuario AS u WHERE u.email = ?1 and u.senha = ?2", nativeQuery = true)
    Optional<UsuarioEntity> findByEmailAndSenha(String email, String senha);

}
