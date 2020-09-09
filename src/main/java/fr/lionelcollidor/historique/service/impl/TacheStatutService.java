package fr.lionelcollidor.historique.service.impl;

import fr.lionelcollidor.historique.exception.NotFoundException;
import fr.lionelcollidor.historique.model.TacheStatut;
import fr.lionelcollidor.historique.repository.TacheStatutRepository;
import fr.lionelcollidor.historique.service.ITacheStatutService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TacheStatutService implements ITacheStatutService {

    private final TacheStatutRepository tacheStatutRepository;

    public TacheStatutService(TacheStatutRepository tacheStatutRepository) {
        this.tacheStatutRepository = tacheStatutRepository;
    }

    @Override
    public List<TacheStatut> getAllTacheStatut() {
        return this.tacheStatutRepository.findAll();
    }

    @Override
    public Optional<TacheStatut> getOptionalTacheStatutById(Long id) throws NotFoundException {
        Optional<TacheStatut> tStatut = this.tacheStatutRepository.findById(id);

        if (!tStatut.isPresent())
            throw new NotFoundException("L'association tache-statut n: " + id + "est introuvable.");

        return tStatut;
    }

    @Override
    public Optional<TacheStatut> getOptionalTacheStatutByTacheAndStatut(String numeroTache, Long idStatut) throws NotFoundException {
        Optional<TacheStatut> tStatut = this.tacheStatutRepository.findByTacheAndStatut(numeroTache, idStatut);

        if (!tStatut.isPresent())
            throw new NotFoundException("L'association tache-statut recherché est introuvable.");

        return tStatut;
    }

    @Override
    public TacheStatut getTacheStatutById(Long id) throws NotFoundException {
        Optional<TacheStatut> tStatut = this.tacheStatutRepository.findById(id);

        if (!tStatut.isPresent())
            throw new NotFoundException("L'association tache-statut n: " + id + "est introuvable.");

        return new TacheStatut(tStatut.get().getId(), tStatut.get().getTache(), tStatut.get().getStatut(), tStatut.get().getDateStatut());
    }

    @Override
    public TacheStatut getTacheStatutByTacheAndStatut(String numeroTache, Long idStatut) throws NotFoundException {
        Optional<TacheStatut> tStatut = this.tacheStatutRepository.findByTacheAndStatut(numeroTache, idStatut);

        if (!tStatut.isPresent())
            throw new NotFoundException("L'association tache-statut recherché est introuvable.");

        return new TacheStatut(tStatut.get().getId(), tStatut.get().getTache(), tStatut.get().getStatut(), tStatut.get().getDateStatut());
    }

    @Override
    public TacheStatut createOrUpdateTacheStatut(TacheStatut tStatut) {
        return this.tacheStatutRepository.save(tStatut);
    }

    @Override
    public void deleteTacheStatutById(Long id) {
        this.tacheStatutRepository.deleteById(id);
    }
}
