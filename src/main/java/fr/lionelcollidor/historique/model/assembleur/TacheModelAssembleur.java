package fr.lionelcollidor.historique.model.assembleur;

import fr.lionelcollidor.historique.controller.TacheController;
import fr.lionelcollidor.historique.model.Tache;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class TacheModelAssembleur implements RepresentationModelAssembler<Tache, EntityModel<Tache>> {
    @Override
    public EntityModel<Tache> toModel(Tache tache) {
        return EntityModel.of(tache,
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(TacheController.class).getTacheById(tache.getNumero())
                ).withSelfRel(),
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(TacheController.class).getAllTache()
                ).withRel("taches")
        );
    }

}
