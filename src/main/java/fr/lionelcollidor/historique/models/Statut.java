package fr.lionelcollidor.historique.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "statut")
public class Statut {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom", length = 10)
    private String nom;

    public Statut() {
        super();
    }

    public Statut(Long id, String nom) {
        this();
        this.id = id;
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nom);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Statut))
            return false;
        Statut statut = (Statut) obj;
        return Objects.equals(this.id, statut.id)
                && Objects.equals(this.nom, statut.nom);
    }

    @Override
    public String toString() {
        return "Statut{"
                + "id= " + this.id
                + ", nom= " + this.nom
                + " }";
    }
}
