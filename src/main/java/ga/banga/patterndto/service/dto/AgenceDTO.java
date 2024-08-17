package ga.banga.patterndto.service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link ga.banga.patterndto.domain.Agence} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AgenceDTO implements Serializable {

    private UUID id;

    @NotNull
    @Size(max = 50)
    private String libelle;

    private String adresse;

    private String rib;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AgenceDTO)) {
            return false;
        }

        AgenceDTO agenceDTO = (AgenceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, agenceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AgenceDTO{" +
            "id='" + getId() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", rib='" + getRib() + "'" +
            "}";
    }
}
