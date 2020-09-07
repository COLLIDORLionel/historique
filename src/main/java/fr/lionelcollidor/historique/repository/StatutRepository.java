package fr.lionelcollidor.historique.repository;

import fr.lionelcollidor.historique.model.Statut;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StatutRepository extends CrudRepository<Statut, Long> {
    @Override
    List<Statut> findAll();

    @Override
    Optional<Statut> findById(Long aLong);

    @Transactional
    @Override
    <S extends Statut> S save(S s);

    @Override
    void deleteById(Long aLong);
}
