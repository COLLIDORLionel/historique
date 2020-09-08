package fr.lionelcollidor.historique.controller;

import fr.lionelcollidor.historique.exception.NotFoundException;
import fr.lionelcollidor.historique.model.Statut;
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

    @GetMapping("/statuts")
    public CollectionModel<EntityModel<Statut>> getAllStatut(){
        List<EntityModel<Statut>> statuts = this.service.getAllStatut()
                .stream()
                .map(
                        statut -> EntityModel.of(statut,
                                WebMvcLinkBuilder.linkTo(
                                        WebMvcLinkBuilder.methodOn(StatutController.class).getStatutById(statut.getId())).withSelfRel(),
                                WebMvcLinkBuilder.linkTo(
                                        WebMvcLinkBuilder.methodOn(StatutController.class).getAllStatut()).withRel("statuts")
                        )
                ).collect(Collectors.toList());

        return CollectionModel.of(statuts, WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(StatutController.class).getAllStatut()).withSelfRel()
        );
    }

    @PostMapping("/statuts")
    public EntityModel<Statut> createStatut(@RequestBody Statut newStatut){
        Statut sCreate = this.service.createOrUpdateStatut(newStatut);

        return EntityModel.of(sCreate, WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(StatutController.class).getStatutById(sCreate.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(StatutController.class).getAllStatut()).withRel("statuts")
        );
    }

    @GetMapping("/statuts/{id}")
    public EntityModel<Statut> getStatutById(@PathVariable Long id){
        Statut s = this.service.getStatutById(id)
                .orElseThrow( ()-> new NotFoundException(
                        "Le statut numéro : " + id + " est introuvable."
                ) );

        return EntityModel.of(s, WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(StatutController.class).getStatutById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(StatutController.class).getAllStatut()).withRel("statuts")
        );

    }

    @PutMapping("/statuts/{id}")
    public EntityModel<Statut> updateStatut(@RequestBody Statut updateStatut, @PathVariable Long id){
        Statut sUpdate = this.service.getStatutById(id)
                .map(statut -> {
                    statut.setNom(updateStatut.getNom());
                    return this.service.createOrUpdateStatut(statut);
                })
                .orElseThrow( ()-> new NotFoundException("Le statut n'existe pas."));

        return EntityModel.of(sUpdate, WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(StatutController.class).getStatutById(sUpdate.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(StatutController.class).getAllStatut()).withRel("statuts")
        );
    }

    @DeleteMapping("/statuts/{id}")
    public void deleteStatut(@PathVariable Long id){
        this.service.deleteStatutById(id);
    }

}
