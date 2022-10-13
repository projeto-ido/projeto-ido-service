package school.sptech.ido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.ido.repository.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

}
