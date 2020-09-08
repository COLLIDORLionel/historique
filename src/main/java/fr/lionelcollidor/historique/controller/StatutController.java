package fr.lionelcollidor.historique.controller;

import fr.lionelcollidor.historique.exception.NotFoundException;
import fr.lionelcollidor.historique.model.Statut;
import fr.lionelcollidor.historique.model.assembleur.StatutModelAssembleur;
import fr.lionelcollidor.historique.service.IStatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StatutController {

    @Autowired
    private IStatutService service;
    @Autowired
    private StatutModelAssembleur assembleur;

    @GetMapping("/statuts")
    public CollectionModel<EntityModel<Statut>> getAllStatut(){
        List<EntityModel<Statut>> statuts = this.service.getAllStatut()
                .stream()
                .map(assembleur::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(statuts, WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(StatutController.class).getAllStatut()).withSelfRel()
        );
    }

    @PostMapping("/statuts")
    public EntityModel<Statut> createStatut(@RequestBody Statut newStatut){
        Statut sCreate = this.service.createOrUpdateStatut(newStatut);

        return assembleur.toModel(sCreate);
    }

    @GetMapping("/statuts/{id}")
    public EntityModel<Statut> getStatutById(@PathVariable Long id){
        Statut s = this.service.getStatutById(id)
                .orElseThrow( ()-> new NotFoundException(
                        "Le statut numéro : " + id + " est introuvable."
                ) );

        return assembleur.toModel(s);

    }

    @PutMapping("/statuts/{id}")
    public EntityModel<Statut> updateStatut(@RequestBody Statut updateStatut, @PathVariable Long id){
        Statut sUpdate = this.service.getStatutById(id)
                .map(statut -> {
                    statut.setNom(updateStatut.getNom());
                    return this.service.createOrUpdateStatut(statut);
                })
                .orElseThrow( ()-> new NotFoundException("Le statut n'existe pas."));

        return assembleur.toModel(sUpdate);
    }

    @DeleteMapping("/statuts/{id}")
    public void deleteStatut(@PathVariable Long id){
        this.service.deleteStatutById(id);
    }

}
