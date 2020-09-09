package fr.lionelcollidor.historique.service;

import fr.lionelcollidor.historique.exception.NotFoundException;
import fr.lionelcollidor.historique.model.TacheStatut;

import java.util.List;
import java.util.Optional;

public interface ITacheStatutService {

    List<TacheStatut> getAllTacheStatut();

    Optional<TacheStatut> getOptionalTacheStatutById(Long id) throws NotFoundException;

    Optional<TacheStatut> getOptionalTacheStatutByTacheAndStatut(String numeroTache, Long idStatut) throws NotFoundException;

    TacheStatut getTacheStatutById(Long id) throws NotFoundException;

    TacheStatut getTacheStatutByTacheAndStatut(String numeroTache, Long idStatut) throws NotFoundException;

    TacheStatut createOrUpdateTacheStatut(TacheStatut tStatut);

    void deleteTacheStatutById(Long id);
}
