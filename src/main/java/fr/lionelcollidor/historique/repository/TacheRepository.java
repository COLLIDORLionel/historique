package fr.lionelcollidor.historique.repository;

import fr.lionelcollidor.historique.model.Tache;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TacheRepository extends CrudRepository<Tache, String> {
    @Override
    List<Tache> findAll();

    @Override
    Optional<Tache> findById(String s);

    @Override
    <S extends Tache> S save(S s);

    @Override
    void deleteById(String s);
}
