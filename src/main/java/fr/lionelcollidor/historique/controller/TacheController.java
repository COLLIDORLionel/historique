package fr.lionelcollidor.historique.controller;

import fr.lionelcollidor.historique.exception.NotFoundException;
import fr.lionelcollidor.historique.model.Tache;
import fr.lionelcollidor.historique.model.assembleur.TacheModelAssembleur;
import fr.lionelcollidor.historique.service.ITacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TacheController {

    @Autowired
    private ITacheService service;
    @Autowired
    private TacheModelAssembleur assembleur;

    @GetMapping("/taches")
    public CollectionModel<EntityModel<Tache>> getAllTache(){
        List<EntityModel<Tache>> taches = this.service.getAllTache()
                .stream()
                .map(assembleur::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(taches, WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(TacheController.class).getAllTache()).withSelfRel()
        );
    }

    @GetMapping("/taches/{numero}")
    public EntityModel<Tache> getTacheById(@PathVariable String numero){
        Tache t = this.service.getTacheById(numero);

        return assembleur.toModel(t);
    }

    @PostMapping("/taches")
    public EntityModel<Tache> creaTache(@RequestBody Tache newTache){
        Tache tCreate = this.service.createOrUpdateTache(newTache);

        return assembleur.toModel(tCreate);
    }

    @PutMapping("/taches/{id}")
    public EntityModel<Tache> updateTache(@RequestBody Tache updateTache, @PathVariable String numero) throws NotFoundException {
        Tache tUpdate = this.service.getOptionalTacheById(numero)
                .map(tache -> {
                    tache.setCom_1N(updateTache.getCom_1N());
                    tache.setDate_1N(updateTache.getDate_1N());
                    tache.setStatuts(updateTache.getStatuts());
                    return this.service.createOrUpdateTache(tache);
                })
                .orElseThrow(() -> new NotFoundException("La tache n'existe pas."));

        return assembleur.toModel(tUpdate);
    }

    @DeleteMapping("/taches/{id}")
    public void deleteStatut(@PathVariable String numero){
        this.service.deleteTacheById(numero);
    }
}
