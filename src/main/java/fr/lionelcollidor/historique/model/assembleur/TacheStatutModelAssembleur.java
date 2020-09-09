package fr.lionelcollidor.historique.model.assembleur;

import fr.lionelcollidor.historique.controller.TacheStatutController;
import fr.lionelcollidor.historique.model.TacheStatut;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class TacheStatutModelAssembleur implements RepresentationModelAssembler<TacheStatut, EntityModel<TacheStatut>> {
    @Override
    public EntityModel<TacheStatut> toModel(TacheStatut tStatut) {
        return EntityModel.of(tStatut,
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(TacheStatutController.class).getTacheStatutById(tStatut.getId())
                ).withSelfRel(),
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(TacheStatutController.class).getAllTacheStatut()
                ).withRel("tacheStatuts"));
    }
}
