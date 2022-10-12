package school.sptech.ido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.ido.repository.entity.EtiquetaEntity;
import java.util.List;
import java.util.Optional;

public interface EtiquetaRepository extends JpaRepository<EtiquetaEntity, Integer> {

    @Query(value = "SELECT e.* FROM etiqueta AS e WHERE e.fk_usuario = ?1", nativeQuery = true)
    List<EtiquetaEntity> findByFkUsuario(Integer idUsuario);

    @Query(value = "SELECT e.* FROM etiqueta AS e WHERE e.fk_usuario = ?1 AND e.id_etiqueta = ?2", nativeQuery = true)
    Optional<EtiquetaEntity> findByFkUsuarioAndIdEtiqueta(Integer fkUsuario, Integer idEtiqueta);

    @Query(value = "INSERT INTO tarefa_etiqueta VALUES ( ?1, ?2)", nativeQuery = true)
    void saveEtiquetaTarefa(Integer idTarefa, Integer idEtiqueta);

    @Query(value = "DELETE FROM tarefa_etiqueta AS te WHERE te.id_etiqueta = ?1", nativeQuery = true)
    void deletaAllEtiquetaById(Integer idEtiqueta);

    @Query(value = "DELETE FROM tarefa_etiqueta AS te WHERE te.id_etiqueta = ?1 AND te.id_tarefa = ?2", nativeQuery = true)
    void deleteEtiquetaByIdAndIdTarefa(Integer idTarefa, Integer idEtiqueta);
}
