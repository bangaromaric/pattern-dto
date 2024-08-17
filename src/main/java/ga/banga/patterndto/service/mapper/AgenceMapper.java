package ga.banga.patterndto.service.mapper;

import ga.banga.patterndto.domain.Agence;
import ga.banga.patterndto.service.dto.AgenceDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Agence} and its DTO {@link AgenceDTO}.
 */
@Mapper(componentModel = "spring")
public interface AgenceMapper extends EntityMapper<AgenceDTO, Agence> {}
