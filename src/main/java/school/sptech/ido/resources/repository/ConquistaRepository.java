package school.sptech.ido.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.ido.resources.repository.entity.ConquistaEntity;

import java.util.List;

public interface ConquistaRepository extends JpaRepository<ConquistaEntity, Integer> {

    @Query(value = "SELECT c.* FROM conquista AS c WHERE c.fk_usuario = ?1", nativeQuery = true)
    List<ConquistaEntity> findByIdUsuario(Integer idUsuario);
}
