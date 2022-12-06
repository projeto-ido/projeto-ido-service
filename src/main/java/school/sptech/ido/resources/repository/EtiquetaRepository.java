package school.sptech.ido.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.ido.application.controller.dto.EtiquetaExportacaoDto;
import school.sptech.ido.application.controller.dto.Response.EtiquetaDto;
import school.sptech.ido.resources.repository.entity.EtiquetaEntity;
import java.util.List;
import java.util.Optional;

public interface EtiquetaRepository extends JpaRepository<EtiquetaEntity, Integer> {

    @Query(value = "SELECT e.* FROM etiqueta AS e WHERE e.fk_usuario = ?1", nativeQuery = true)
    List<EtiquetaEntity> findByFkUsuario(Integer idUsuario);

    @Query(value = "SELECT e FROM EtiquetaEntity e WHERE e.usuario.idUsuario = ?1 AND e.idEtiqueta = ?2")
    Optional<EtiquetaEntity> findByFkUsuarioAndIdEtiqueta(Integer fkUsuario, Integer idEtiqueta);

    @Query(value = "DELETE FROM tarefa_etiqueta AS te WHERE te.id_etiqueta = ?1 AND te.id_tarefa = ?2", nativeQuery = true)
    void deleteEtiquetaByIdAndIdTarefa(Integer idTarefa, Integer idEtiqueta);

    @Query(value = "SELECT new school.sptech.ido.application.controller.dto.Response.EtiquetaDto(e) " +
            "FROM EtiquetaEntity e JOIN e.usuario U WHERE U.idUsuario = :id")
    List<EtiquetaDto> findAllEtiquetasDto(int id);

    @Query("SELECT new school.sptech.ido.application.controller.dto.Response.EtiquetaDto(e) " +
            "FROM EtiquetaEntity e JOIN e.tarefa t WHERE t.idTarefa = ?1")
    List<EtiquetaDto> getEtiquetasDto(int idTarefa);

    @Query("SELECT new school.sptech.ido.application.controller.dto.EtiquetaExportacaoDto(e) " +
            "FROM EtiquetaEntity e JOIN e.tarefa t WHERE t.idTarefa = ?1")
    List<EtiquetaExportacaoDto> getEtiquestaExportacaoDto(int idTarefa);
}
