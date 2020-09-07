package fr.lionelcollidor.historique.repository;

import fr.lionelcollidor.historique.models.Statut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatutRepository extends JpaRepository<Statut, Long> {
    @Override
    List<Statut> findAll();

    @Override
    Optional<Statut> findById(Long aLong);

    @Override
    <S extends Statut> S save(S s);

    @Override
    void delete(Statut statut);
}
