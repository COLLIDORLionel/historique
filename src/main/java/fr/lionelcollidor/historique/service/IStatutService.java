package fr.lionelcollidor.historique.service;

import fr.lionelcollidor.historique.exception.NotFoundException;
import fr.lionelcollidor.historique.model.Statut;

import java.util.List;
import java.util.Optional;

public interface IStatutService {
    List<Statut> getAllStatut();

    Optional<Statut> getOptionalStatutById(Long id) throws NotFoundException;

    Statut getStatutById(Long id) throws NotFoundException;

    Statut createOrUpdateStatut(Statut s);

    void deleteStatutById(Long id);
}
