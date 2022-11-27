package school.sptech.ido.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.ido.application.controller.dto.Response.TarefaEtiquetasDto;
import school.sptech.ido.application.controller.dto.Response.TarefaTimeLine;
import school.sptech.ido.resources.repository.entity.TarefaEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<TarefaEntity, Integer> {

    @Query(value = "SELECT t.* FROM tarefa AS t WHERE t.fk_usuario = ?1", nativeQuery = true)
    List<TarefaEntity> findByFkUsuario(Integer fkUsuario);

    @Query(value = "SELECT t.* FROM tarefa AS t WHERE t.fk_usuario = ?1 AND t.id_tarefa = ?2", nativeQuery = true)
    Optional<TarefaEntity> findByFkUsuarioAndIdTarefa(Integer fkUsuario, Integer idTarefa);

    @Query(value = "SELECT new school.sptech.ido.application.controller.dto.Response.TarefaEtiquetasDto(t) " +
            "FROM TarefaEntity t")
    List<TarefaEtiquetasDto> getTarefaEtiquetaDto();

    @Query(value = "SELECT new school.sptech.ido.application.controller.dto.Response.TarefaTimeLine(t) " +
            "FROM TarefaEntity t")
    List<TarefaTimeLine> getTarefasTimeLine();

    @Query(value = "SELECT count(t) " +
            "FROM TarefaEntity t JOIN t.usuario u WHERE t.dataConclusao = ?1 AND u.idUsuario = ?2")
    Long getQtdTarefasConcluidas(LocalDate diaSemana,int id);

}
