package ga.banga.patterndto.service.mapper;

import ga.banga.patterndto.domain.Agence;
import ga.banga.patterndto.domain.Utilisateur;
import ga.banga.patterndto.service.dto.AgenceDTO;
import ga.banga.patterndto.service.dto.UtilisateurDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link Utilisateur} and its DTO {@link UtilisateurDTO}.
 */
@Mapper(componentModel = "spring")
public interface UtilisateurMapper extends EntityMapper<UtilisateurDTO, Utilisateur> {
    @Mapping(target = "agence", source = "agence", qualifiedByName = "agenceLibelle")
    UtilisateurDTO toDto(Utilisateur s);

    @Named("agenceLibelle")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "libelle", source = "libelle")
//    @Mapping(target = "totalPrice", expression = "java(product.getPrice() * 1.08)")

    AgenceDTO toDtoAgenceLibelle(Agence agence);
}
