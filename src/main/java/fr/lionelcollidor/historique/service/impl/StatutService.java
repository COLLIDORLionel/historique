package fr.lionelcollidor.historique.service.impl;

import fr.lionelcollidor.historique.exception.NotFoundException;
import fr.lionelcollidor.historique.model.Statut;
import fr.lionelcollidor.historique.repository.StatutRepository;
import fr.lionelcollidor.historique.service.IStatutService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatutService implements IStatutService {

    private final StatutRepository repository;

    public StatutService(StatutRepository repository) {
        this.repository = repository;
    }

    public List<Statut> getAllStatut(){
        return this.repository.findAll();
    }

    public Optional<Statut> getStatutById(Long id) throws NotFoundException {

        Optional<Statut> statut = this.repository.findById(id);

        if (statut.equals(Statut.class))
            throw new NotFoundException("Le statut qui a pour identifiant : " + id + "n'est pas trouvable");

        return statut;
    }

    public Statut createStatut(Statut s){
        return this.repository.save(s);
    }

    public void deleteStatutById(Long id){
        this.repository.deleteById(id);
    }

}
