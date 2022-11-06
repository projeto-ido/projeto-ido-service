package school.sptech.ido.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.ido.application.controller.dto.EtiquetaExportacaoDto;
import school.sptech.ido.application.controller.dto.Response.EtiquetaDto;
import school.sptech.ido.application.controller.dto.Response.SubTarefaDto;
import school.sptech.ido.application.controller.dto.SubTarefaExportacaoDto;
import school.sptech.ido.resources.repository.entity.EtiquetaEntity;
import java.util.List;
import java.util.Optional;

public interface EtiquetaRepository extends JpaRepository<EtiquetaEntity, Integer> {

    @Query(value = "SELECT e.* FROM etiqueta AS e WHERE e.fk_usuario = ?1", nativeQuery = true)
    List<EtiquetaEntity> findByFkUsuario(Integer idUsuario);

    @Query(value = "SELECT e.* FROM etiqueta AS e WHERE e.fk_usuario = ?1 AND e.id_etiqueta = ?2", nativeQuery = true)
    Optional<EtiquetaEntity> findByFkUsuarioAndIdEtiqueta(Integer fkUsuario, Integer idEtiqueta);

    @Query(value = "SELECT e.* FROM etiqueta AS e join tarefa_etiqueta AS te on e.id_etiqueta = te.id_etiqueta " +
            "join tarefa AS t on te.id_tarefa = t.id_tarefa WHERE t.id_tarefa= ?1", nativeQuery = true)
    List<EtiquetaEntity> findByIdTarefa(Integer idTarefa);

    @Query(value = "INSERT INTO tarefa_etiqueta VALUES ( ?1, ?2)", nativeQuery = true)
    void saveEtiquetaTarefa(Integer idTarefa, Integer idEtiqueta);

    @Query(value = "DELETE FROM tarefa_etiqueta AS te WHERE te.id_etiqueta = ?1", nativeQuery = true)
    void deletaAllEtiquetaById(Integer idEtiqueta);

    @Query(value = "DELETE FROM tarefa_etiqueta AS te WHERE te.id_etiqueta = ?1 AND te.id_tarefa = ?2", nativeQuery = true)
    void deleteEtiquetaByIdAndIdTarefa(Integer idTarefa, Integer idEtiqueta);


    @Query("SELECT new school.sptech.ido.application.controller.dto.Response.EtiquetaDto(e) " +
            "FROM EtiquetaEntity e JOIN e.tarefa t WHERE t.idTarefa = ?1")
    List<EtiquetaDto> getEtiquetasDto(int idTarada);

    @Query("SELECT new school.sptech.ido.application.controller.dto.EtiquetaExportacaoDto(e) " +
            "FROM EtiquetaEntity e JOIN e.tarefa t WHERE t.idTarefa = ?1")
    List<EtiquetaExportacaoDto> getEtiquestaExportacaoDto(int idTarefa);
}
