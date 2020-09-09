package fr.lionelcollidor.historique.service.impl;

import fr.lionelcollidor.historique.exception.NotFoundException;
import fr.lionelcollidor.historique.model.Tache;
import fr.lionelcollidor.historique.repository.TacheRepository;
import fr.lionelcollidor.historique.service.ITacheService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
    public Optional<Tache> getOptionalTacheById(String numero) throws NotFoundException {
        Optional<Tache> tache = this.tacheRepository.findById(numero);

        if (tache.isPresent())
            throw new NotFoundException("La tache qui a pour numéro: " + numero + " n'est pas trouvable.");

        return tache;
    }

    @Override
    public Tache getTacheById(String numero) throws NotFoundException {
        Optional<Tache> oTache = this.tacheRepository.findById(numero);

        if (!oTache.isPresent())
            throw new NotFoundException("La tache qui a pour numéro: " + numero + " n'est pas trouvable.");

        return new Tache(oTache.get().getNumero(),
                oTache.get().getCom_1N(),
                oTache.get().getDate_1N(),
                oTache.get().getStatuts());
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
