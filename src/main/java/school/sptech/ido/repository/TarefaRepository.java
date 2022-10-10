package school.sptech.ido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.ido.repository.entity.TarefaEntity;
import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<TarefaEntity, Integer> {

    @Query(value = "SELECT t.* FROM tarefa AS t WHERE t.fk_usuario = ?1", nativeQuery = true)
    List<TarefaEntity> findByFkUsuario(Integer fkUsuario);

    @Query(value = "SELECT t.* FROM tarefa AS t WHERE t.fk_usuario = ?1 AND t.id_tarefa = ?2", nativeQuery = true)
    Optional<TarefaEntity> findByFkUsuarioAndIdTarefa(Integer fkUsuario, Integer idTarefa);
}
