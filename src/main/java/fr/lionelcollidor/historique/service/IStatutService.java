package fr.lionelcollidor.historique.service;

import fr.lionelcollidor.historique.exception.InternalErrorException;
import fr.lionelcollidor.historique.exception.NotFoundException;
import fr.lionelcollidor.historique.model.Statut;

import java.util.List;
import java.util.Optional;

public interface IStatutService {
    List<Statut> getAllStatut();

    Optional<Statut> getStatutById(Long id) throws NotFoundException, InternalErrorException;

    Statut createOrUpdateStatut(Statut s);

    void deleteStatutById(Long id);
}
