package school.sptech.ido.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.ido.application.controller.dto.Response.SubTarefaDto;
import school.sptech.ido.resources.repository.entity.SubTarefaEntity;

import java.util.List;

public interface SubTarefaRepository extends JpaRepository<SubTarefaEntity, Integer> {

    @Query(value = "SELECT st.* FROM sub_tarefa AS st WHERE st.fk_tarefa = ?1", nativeQuery = true)
    List<SubTarefaEntity> findAllByIdTarefa(Integer idTarefa);


    @Query("SELECT new school.sptech.ido.application.controller.dto.Response.SubTarefaDto(s) " +
            "FROM SubTarefaEntity s where s.tarefa.idTarefa = ?1")
    List<SubTarefaDto> getSubtarefasDto(int idTarefa);
}
