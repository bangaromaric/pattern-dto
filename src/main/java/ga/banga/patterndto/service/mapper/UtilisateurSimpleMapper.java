package ga.banga.patterndto.service.mapper;

import ga.banga.patterndto.domain.Agence;
import ga.banga.patterndto.domain.Utilisateur;
import ga.banga.patterndto.service.dto.AgenceDTO;
import ga.banga.patterndto.service.dto.UtilisateurDTO;
import ga.banga.patterndto.service.dto.UtilisateurSimpleDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link Utilisateur} and its DTO {@link UtilisateurDTO}.
 */
@Mapper(componentModel = "spring")
public interface UtilisateurSimpleMapper extends EntityMapper<UtilisateurSimpleDTO, Utilisateur> {
    @Mapping(target = "agence", source = "agence", qualifiedByName = "agenceLibelle")
    @Mapping(target = "fullName", expression = "java(s.getNom() +' '+ s.getPrenom() )")
    UtilisateurSimpleDTO toDto(Utilisateur s);

    @Named("agenceLibelle")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "libelle", source = "libelle")
//   @Mapping(target = "totalPrice", expression = "java(product.getPrice() * 1.08)")
//    @Mapping(target = "libelle", expression = "java(agence.getNom() )"))
    AgenceDTO toDtoAgenceLibelle(Agence agence);
}
