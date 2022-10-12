package school.sptech.ido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.ido.repository.entity.TarefaEntity;

public interface TarefaRepository extends JpaRepository<TarefaEntity, Integer> {

}
