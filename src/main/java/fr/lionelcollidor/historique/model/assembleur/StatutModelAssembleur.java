package fr.lionelcollidor.historique.model.assembleur;

import fr.lionelcollidor.historique.controller.StatutController;
import fr.lionelcollidor.historique.model.Statut;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class StatutModelAssembleur implements RepresentationModelAssembler<Statut, EntityModel<Statut>> {
    @Override
    public EntityModel<Statut> toModel(Statut statut) {
        return EntityModel.of(statut,
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(StatutController.class).getStatutById(statut.getId())
                ).withSelfRel(),
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(StatutController.class).getAllStatut()
                ).withRel("statuts"));
    }
}
