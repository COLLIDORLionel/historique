package fr.lionelcollidor.historique.controller;

import fr.lionelcollidor.historique.exception.NotFoundException;
import fr.lionelcollidor.historique.model.Statut;
import fr.lionelcollidor.historique.service.IStatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return this.service.createOrUpdateStatut(newStatut);
    }

    @GetMapping("/statuts/{id}")
    public Statut getStatutById(@PathVariable Long id){
        return this.service.getStatutById(id)
                .orElseThrow( ()-> new NotFoundException(
                        "Le statut numéro : " + id + " est introuvable."
                ) );
    }

    @PutMapping("/statuts/{id}")
    public Statut updateStatut(@RequestBody Statut updateStatut, @PathVariable Long id){
        return this.service.getStatutById(id)
                .map(statut -> {
                    statut.setNom(updateStatut.getNom());
                    return this.service.createOrUpdateStatut(statut);
                })
                .orElseThrow( ()-> new NotFoundException("Le statut n'existe pas."));
    }

}
