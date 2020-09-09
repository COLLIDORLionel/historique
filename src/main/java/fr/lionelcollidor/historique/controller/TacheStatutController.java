package fr.lionelcollidor.historique.controller;

import fr.lionelcollidor.historique.exception.NotFoundException;
import fr.lionelcollidor.historique.model.TacheStatut;
import fr.lionelcollidor.historique.model.assembleur.TacheStatutModelAssembleur;
import fr.lionelcollidor.historique.service.ITacheStatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TacheStatutController {
    @Autowired
    private ITacheStatutService service;

    @Autowired
    private TacheStatutModelAssembleur assembleur;

    @GetMapping("/tacheStatuts")
    public CollectionModel<EntityModel<TacheStatut>> getAllTacheStatut(){
        List<EntityModel<TacheStatut>> tacheStatuts = this.service.getAllTacheStatut()
                .stream()
                .map(assembleur::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tacheStatuts, WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(TacheStatutController.class).getAllTacheStatut()).withSelfRel()
        );
    }

    @GetMapping("/tacheStatus/{id}")
    public EntityModel<TacheStatut> getTacheStatutById(@PathVariable Long id) throws NotFoundException{
        TacheStatut tacheStatut = this.service.getTacheStatutById(id);

        return assembleur.toModel(tacheStatut);
    }

    @PostMapping("/tacheStatus")
    public EntityModel<TacheStatut> createTacheStatut(@RequestBody TacheStatut newTacheStatut){
        TacheStatut tStatutCreate = this.service.createOrUpdateTacheStatut(newTacheStatut);
        return assembleur.toModel(tStatutCreate);
    }

    @PutMapping("/tacheStatus/{id}")
    public EntityModel<TacheStatut> updateTacheStatut(@RequestBody TacheStatut updateTS, @PathVariable Long id) throws NotFoundException{
        TacheStatut tStatutUpdate = this.service.getOptionalTacheStatutById(id)
                .map(tacheStatut -> {
                    tacheStatut.setTache(updateTS.getTache());
                    tacheStatut.setStatut(updateTS.getStatut());
                    tacheStatut.setDateStatut(updateTS.getDateStatut());
                    return this.service.createOrUpdateTacheStatut(tacheStatut);
                })
                .orElseThrow(() -> new NotFoundException());

        return assembleur.toModel(tStatutUpdate);
    }

    @DeleteMapping("/tacheStatus/{id}")
    public void deleteTacheStatut(@PathVariable Long id){
        this.service.deleteTacheStatutById(id);
    }
}
