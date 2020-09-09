package fr.lionelcollidor.historique.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tache_statuts",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_tache_statut_date",
                columnNames = {
                        "tache_numero",
                        "statut_id",
                        "date_statut"
                }
        )
)
public class TacheStatut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tache_numero", foreignKey = @ForeignKey(name = "TACHE_FK"))
    private Tache tache;

    @ManyToOne
    @JoinColumn(name = "statut_id", foreignKey = @ForeignKey(name = "STATUT_FK"))
    private Statut statut;

    @Column(name = "date_statut", nullable = false)
    private LocalDate dateStatut;

    public TacheStatut() {
        super();
    }

    public TacheStatut(Long id, Tache tache, Statut statut, LocalDate dateStatut) {
        this();
        this.id = id;
        this.tache = tache;
        this.statut = statut;
        this.dateStatut = dateStatut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public LocalDate getDateStatut() {
        return dateStatut;
    }

    public void setDateStatut(LocalDate dateStatut) {
        this.dateStatut = dateStatut;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.tache, this.statut, this.dateStatut );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof TacheStatut))
            return false;
        TacheStatut tStatut = (TacheStatut) obj;
        return Objects.equals(this.id, tStatut.id)
                && Objects.equals(this.tache, tStatut.tache)
                && Objects.equals(this.statut, tStatut.statut)
                && Objects.equals(this.dateStatut, tStatut.dateStatut);
    }
}
