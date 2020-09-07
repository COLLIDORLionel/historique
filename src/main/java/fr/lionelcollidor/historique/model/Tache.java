package fr.lionelcollidor.historique.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "tache")
public class Tache {
    @Id
    @Column(name = "numero", length = 11)
    private String numero;

    @Column(name = "com_1N", length = 2)
    private String com_1N;

    @Column(name = "date_1N")
    private LocalDate date_1N;

    @ManyToMany
    private Map<LocalDate, Statut> statuts;


    public Tache() {
        super();
    }

    public Tache(String numero, String com_1N, LocalDate date_1N, Map<LocalDate, Statut> statuts) {
        this();
        this.numero = numero;
        this.com_1N = com_1N;
        this.date_1N = date_1N;
        this.statuts = statuts;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCom_1N() {
        return com_1N;
    }

    public void setCom_1N(String com_1N) {
        this.com_1N = com_1N;
    }

    public LocalDate getDate_1N() {
        return date_1N;
    }

    public void setDate_1N(LocalDate date_1N) {
        this.date_1N = date_1N;
    }

    public Map<LocalDate, Statut> getStatuts() {
        return statuts;
    }

    public void setStatuts(Map<LocalDate, Statut> statuts) {
        this.statuts = statuts;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.numero, this.com_1N, this.date_1N, this.statuts);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Tache))
            return false;
        Tache tache = (Tache) obj;
        return Objects.equals(this.numero, tache.numero)
                && Objects.equals(this.com_1N, tache.com_1N)
                && Objects.equals(this.date_1N, tache.date_1N)
                && Objects.equals(this.statuts, tache.statuts);
    }

    @Override
    public String toString() {
        return "Tache{"
                + "numero= " + this.numero
                + ", com 1N= " + this.com_1N
                + ", date 1N= " + this.date_1N
                + ", statuts= " + this.statuts.toString()
                + " }";
    }
}
