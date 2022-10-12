package school.sptech.ido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.ido.repository.entity.SubTarefaEntity;

import java.util.List;

public interface SubTarefaRepository extends JpaRepository<SubTarefaEntity, Integer> {

    @Query(value = "SELECT st.* FROM sub_tarefa AS st WHERE st.fk_tarefa = ?1", nativeQuery = true)
    List<SubTarefaEntity> findAllByIdTarefa(Integer idTarefa);
}
