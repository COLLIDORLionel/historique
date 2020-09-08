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

    public Optional<Statut> getStatutById(Long id) throws NotFoundException, InternalErrorException {

        Optional<Statut> statut = this.statutRepository.findById(id);

        if (statut.equals(null))
            throw new NotFoundException("Le statut qui a pour identifiant : " + id + " n'est pas trouvable");

        if (!statut.equals(Statut.class))
            throw new InternalErrorException("Une erreur technique s'est produite. Merci de contacter le support." +
                    "Veuillez réessayer dans quelques instants, si c'est fait contactez le support.");

        return statut;
    }

    public Statut createOrUpdateStatut(Statut s){
        return this.statutRepository.save(s);
    }

    public void deleteStatutById(Long id){
        this.statutRepository.deleteById(id);
    }

}
