package fr.lionelcollidor.historique.service;

import fr.lionelcollidor.historique.exception.NotFoundException;
import fr.lionelcollidor.historique.model.Tache;

import java.util.List;
import java.util.Optional;

public interface ITacheService {
    List<Tache> getAllTache();

    Optional<Tache> getOptionalTacheById(String numero) throws NotFoundException;

    Tache getTacheById(String numero) throws NotFoundException;

    Tache createOrUpdateTache(Tache t);

    void deleteTacheById(String numero);
}
