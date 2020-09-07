package fr.lionelcollidor.historique.controller;

import fr.lionelcollidor.historique.model.Statut;
import fr.lionelcollidor.historique.repository.StatutRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatutController {
    private final StatutRepository repository;

    StatutController (StatutRepository repository){
        this.repository = repository;
    }

    @GetMapping("/statuts")
    public List<Statut> getAllStatut(){
        return repository.findAll();
    }

    @PostMapping("/statuts")
    public Statut createStatut(@RequestBody Statut newStatut){
        return repository.save(newStatut);
    }

}
