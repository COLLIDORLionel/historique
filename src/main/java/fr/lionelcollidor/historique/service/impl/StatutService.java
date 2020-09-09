package fr.lionelcollidor.historique.service.impl;

import fr.lionelcollidor.historique.exception.InternalErrorException;
import fr.lionelcollidor.historique.exception.NotFoundException;
import fr.lionelcollidor.historique.model.Statut;
import fr.lionelcollidor.historique.repository.StatutRepository;
import fr.lionelcollidor.historique.service.IStatutService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatutService implements IStatutService {

    private final StatutRepository statutRepository;

    public StatutService(StatutRepository statutRepository) {
        this.statutRepository = statutRepository;
    }

    public List<Statut> getAllStatut(){
        return this.statutRepository.findAll();
    }

    public Optional<Statut> getOptionalStatutById(Long id) throws NotFoundException {

        Optional<Statut> statut = this.statutRepository.findById(id);

        if (!statut.isPresent())
            throw new NotFoundException("Le statut qui a pour identifiant : " + id + " n'est pas trouvable");

        return statut;
    }

    public Statut getStatutById(Long id) throws NotFoundException {

        Optional<Statut> oStatut = this.statutRepository.findById(id);

        if (!oStatut.isPresent())
            throw new NotFoundException("Le statut qui a pour identifiant : " + id + " n'est pas trouvable");

        return new Statut(oStatut.get().getId(), oStatut.get().getNom());
    }

    public Statut createOrUpdateStatut(Statut s){
        return this.statutRepository.save(s);
    }

    public void deleteStatutById(Long id){
        this.statutRepository.deleteById(id);
    }

}
