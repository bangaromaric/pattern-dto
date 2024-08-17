package ga.banga.patterndto.service.criteria;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * Criteria class for the {@link ga.banga.patterndto.domain.Utilisateur} entity. This class is used
 * in {@link ga.banga.patterndto.web.rest.UtilisateurResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /utilisateurs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UtilisateurCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private UUIDFilter id;

    private StringFilter nom;

    private StringFilter prenom;

    private StringFilter email;

    private StringFilter phoneNumber;

    private StringFilter password;

    private UUIDFilter agenceId;

    private Boolean distinct;

    public UtilisateurCriteria() {}

    public UtilisateurCriteria(UtilisateurCriteria other) {
        this.id = other.optionalId().map(UUIDFilter::copy).orElse(null);
        this.nom = other.optionalNom().map(StringFilter::copy).orElse(null);
        this.prenom = other.optionalPrenom().map(StringFilter::copy).orElse(null);
        this.email = other.optionalEmail().map(StringFilter::copy).orElse(null);
        this.phoneNumber = other.optionalPhoneNumber().map(StringFilter::copy).orElse(null);
        this.password = other.optionalPassword().map(StringFilter::copy).orElse(null);
        this.agenceId = other.optionalAgenceId().map(UUIDFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public UtilisateurCriteria copy() {
        return new UtilisateurCriteria(this);
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

    public StringFilter getNom() {
        return nom;
    }

    public Optional<StringFilter> optionalNom() {
        return Optional.ofNullable(nom);
    }

    public StringFilter nom() {
        if (nom == null) {
            setNom(new StringFilter());
        }
        return nom;
    }

    public void setNom(StringFilter nom) {
        this.nom = nom;
    }

    public StringFilter getPrenom() {
        return prenom;
    }

    public Optional<StringFilter> optionalPrenom() {
        return Optional.ofNullable(prenom);
    }

    public StringFilter prenom() {
        if (prenom == null) {
            setPrenom(new StringFilter());
        }
        return prenom;
    }

    public void setPrenom(StringFilter prenom) {
        this.prenom = prenom;
    }

    public StringFilter getEmail() {
        return email;
    }

    public Optional<StringFilter> optionalEmail() {
        return Optional.ofNullable(email);
    }

    public StringFilter email() {
        if (email == null) {
            setEmail(new StringFilter());
        }
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getPhoneNumber() {
        return phoneNumber;
    }

    public Optional<StringFilter> optionalPhoneNumber() {
        return Optional.ofNullable(phoneNumber);
    }

    public StringFilter phoneNumber() {
        if (phoneNumber == null) {
            setPhoneNumber(new StringFilter());
        }
        return phoneNumber;
    }

    public void setPhoneNumber(StringFilter phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public StringFilter getPassword() {
        return password;
    }

    public Optional<StringFilter> optionalPassword() {
        return Optional.ofNullable(password);
    }

    public StringFilter password() {
        if (password == null) {
            setPassword(new StringFilter());
        }
        return password;
    }

    public void setPassword(StringFilter password) {
        this.password = password;
    }

    public UUIDFilter getAgenceId() {
        return agenceId;
    }

    public Optional<UUIDFilter> optionalAgenceId() {
        return Optional.ofNullable(agenceId);
    }

    public UUIDFilter agenceId() {
        if (agenceId == null) {
            setAgenceId(new UUIDFilter());
        }
        return agenceId;
    }

    public void setAgenceId(UUIDFilter agenceId) {
        this.agenceId = agenceId;
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
        final UtilisateurCriteria that = (UtilisateurCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(nom, that.nom) &&
            Objects.equals(prenom, that.prenom) &&
            Objects.equals(email, that.email) &&
            Objects.equals(phoneNumber, that.phoneNumber) &&
            Objects.equals(password, that.password) &&
            Objects.equals(agenceId, that.agenceId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, email, phoneNumber, password, agenceId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UtilisateurCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalNom().map(f -> "nom=" + f + ", ").orElse("") +
            optionalPrenom().map(f -> "prenom=" + f + ", ").orElse("") +
            optionalEmail().map(f -> "email=" + f + ", ").orElse("") +
            optionalPhoneNumber().map(f -> "phoneNumber=" + f + ", ").orElse("") +
            optionalPassword().map(f -> "password=" + f + ", ").orElse("") +
            optionalAgenceId().map(f -> "agenceId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
