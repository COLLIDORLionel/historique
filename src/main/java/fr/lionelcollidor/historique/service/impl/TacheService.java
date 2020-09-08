package fr.lionelcollidor.historique.service.impl;

import fr.lionelcollidor.historique.exception.InternalErrorException;
import fr.lionelcollidor.historique.exception.NotFoundException;
import fr.lionelcollidor.historique.model.Tache;
import fr.lionelcollidor.historique.repository.TacheRepository;
import fr.lionelcollidor.historique.service.ITacheService;

import java.util.List;
import java.util.Optional;

public class TacheService implements ITacheService {

    private final TacheRepository tacheRepository;

    public TacheService(TacheRepository tacheRepository) {
        this.tacheRepository = tacheRepository;
    }

    @Override
    public List<Tache> getAllTache() {
        return this.tacheRepository.findAll();
    }

    @Override
    public Optional<Tache> getTacheById(String numero) throws NotFoundException, InternalErrorException {
        Optional<Tache> tache = this.tacheRepository.findById(numero);

        if (tache.equals(null))
            throw new NotFoundException("La tache qui a pour numéro: " + numero + " n'est pas trouvable.");

        if (!tache.equals(Tache.class))
            throw new InternalErrorException("Une erreur technique s'est produite. Merci de contacter le support." +
                    "Veuillez réessayer dans quelques instants, si c'est fait contactez le support.");

        return tache;
    }

    @Override
    public Tache createOrUpdateTache(Tache t) {
        return this.tacheRepository.save(t);
    }

    @Override
    public void deleteTacheById(String numero) {
        this.tacheRepository.deleteById(numero);
    }
}
