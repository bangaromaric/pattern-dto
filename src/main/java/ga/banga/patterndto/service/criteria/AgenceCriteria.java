package ga.banga.patterndto.service.criteria;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * Criteria class for the {@link ga.banga.patterndto.domain.Agence} entity. This class is used
 * in {@link ga.banga.patterndto.web.rest.AgenceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /agences?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AgenceCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private UUIDFilter id;

    private StringFilter libelle;

    private StringFilter adresse;

    private StringFilter rib;

    private UUIDFilter utilisateurId;

    private Boolean distinct;

    public AgenceCriteria() {}

    public AgenceCriteria(AgenceCriteria other) {
        this.id = other.optionalId().map(UUIDFilter::copy).orElse(null);
        this.libelle = other.optionalLibelle().map(StringFilter::copy).orElse(null);
        this.adresse = other.optionalAdresse().map(StringFilter::copy).orElse(null);
        this.rib = other.optionalRib().map(StringFilter::copy).orElse(null);
        this.utilisateurId = other.optionalUtilisateurId().map(UUIDFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public AgenceCriteria copy() {
        return new AgenceCriteria(this);
    }

    public UUIDFilter getId() {
        return id;
    }

    public Optional<UUIDFilter> optionalId() {
        return Optional.ofNullable(id);
    }

    public UUIDFilter id() {
        if (id == null) {
            setId(new UUIDFilter());
        }
        return id;
    }

    public void setId(UUIDFilter id) {
        this.id = id;
    }

    public StringFilter getLibelle() {
        return libelle;
    }

    public Optional<StringFilter> optionalLibelle() {
        return Optional.ofNullable(libelle);
    }

    public StringFilter libelle() {
        if (libelle == null) {
            setLibelle(new StringFilter());
        }
        return libelle;
    }

    public void setLibelle(StringFilter libelle) {
        this.libelle = libelle;
    }

    public StringFilter getAdresse() {
        return adresse;
    }

    public Optional<StringFilter> optionalAdresse() {
        return Optional.ofNullable(adresse);
    }

    public StringFilter adresse() {
        if (adresse == null) {
            setAdresse(new StringFilter());
        }
        return adresse;
    }

    public void setAdresse(StringFilter adresse) {
        this.adresse = adresse;
    }

    public StringFilter getRib() {
        return rib;
    }

    public Optional<StringFilter> optionalRib() {
        return Optional.ofNullable(rib);
    }

    public StringFilter rib() {
        if (rib == null) {
            setRib(new StringFilter());
        }
        return rib;
    }

    public void setRib(StringFilter rib) {
        this.rib = rib;
    }

    public UUIDFilter getUtilisateurId() {
        return utilisateurId;
    }

    public Optional<UUIDFilter> optionalUtilisateurId() {
        return Optional.ofNullable(utilisateurId);
    }

    public UUIDFilter utilisateurId() {
        if (utilisateurId == null) {
            setUtilisateurId(new UUIDFilter());
        }
        return utilisateurId;
    }

    public void setUtilisateurId(UUIDFilter utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AgenceCriteria that = (AgenceCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(adresse, that.adresse) &&
            Objects.equals(rib, that.rib) &&
            Objects.equals(utilisateurId, that.utilisateurId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libelle, adresse, rib, utilisateurId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AgenceCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalLibelle().map(f -> "libelle=" + f + ", ").orElse("") +
            optionalAdresse().map(f -> "adresse=" + f + ", ").orElse("") +
            optionalRib().map(f -> "rib=" + f + ", ").orElse("") +
            optionalUtilisateurId().map(f -> "utilisateurId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
