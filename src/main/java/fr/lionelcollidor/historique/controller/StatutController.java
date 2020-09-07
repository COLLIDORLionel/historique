package fr.lionelcollidor.historique.controller;

import fr.lionelcollidor.historique.model.Statut;
import fr.lionelcollidor.historique.service.IStatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatutController {

    @Autowired
    private IStatutService service;

    @GetMapping("/statuts")
    public List<Statut> getAllStatut(){
        return this.service.getAllStatut();
    }

    @PostMapping("/statuts")
    public Statut createStatut(@RequestBody Statut newStatut){
        return this.service.createStatut(newStatut);
    }

}
