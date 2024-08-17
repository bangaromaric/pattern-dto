package ga.banga.patterndto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * A Agence.
 */
@Entity
@Table(name = "agence")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Agence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Size(max = 50)
    @Column(name = "libelle", length = 50, nullable = false)
    private String libelle;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "rib")
    private String rib;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agence")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "agence" }, allowSetters = true)
    private Set<Utilisateur> utilisateurs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return this.id;
    }

    public Agence id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Agence libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public Agence adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRib() {
        return this.rib;
    }

    public Agence rib(String rib) {
        this.setRib(rib);
        return this;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public Set<Utilisateur> getUtilisateurs() {
        return this.utilisateurs;
    }

    public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
        if (this.utilisateurs != null) {
            this.utilisateurs.forEach(i -> i.setAgence(null));
        }
        if (utilisateurs != null) {
            utilisateurs.forEach(i -> i.setAgence(this));
        }
        this.utilisateurs = utilisateurs;
    }

    public Agence utilisateurs(Set<Utilisateur> utilisateurs) {
        this.setUtilisateurs(utilisateurs);
        return this;
    }

    public Agence addUtilisateur(Utilisateur utilisateur) {
        this.utilisateurs.add(utilisateur);
        utilisateur.setAgence(this);
        return this;
    }

    public Agence removeUtilisateur(Utilisateur utilisateur) {
        this.utilisateurs.remove(utilisateur);
        utilisateur.setAgence(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Agence)) {
            return false;
        }
        return getId() != null && getId().equals(((Agence) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Agence{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", rib='" + getRib() + "'" +
            "}";
    }
}
