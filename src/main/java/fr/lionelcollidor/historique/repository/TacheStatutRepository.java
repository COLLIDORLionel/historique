package fr.lionelcollidor.historique.repository;

import fr.lionelcollidor.historique.model.TacheStatut;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TacheStatutRepository extends CrudRepository<TacheStatut, Long> {
    @Override
    List<TacheStatut> findAll();

    @Override
    Optional<TacheStatut> findById(Long aLong);

    @Query("SELECT ts " +
            "FROM tache_statuts ts " +
            "WHERE ts.tache_numero like %:numero% " +
            "AND ts.statut_id = %:statut_id%")
    Optional<TacheStatut> findByTacheAndStatut(@Param("numero") String numero_tache, @Param("statut_id") Long id_statut);

    @Override
    <S extends TacheStatut> S save(S s);

    @Override
    void deleteById(Long aLong);
}
