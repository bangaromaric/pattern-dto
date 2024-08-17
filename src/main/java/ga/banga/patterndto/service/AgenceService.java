package ga.banga.patterndto.service;

import ga.banga.patterndto.domain.Agence;
import ga.banga.patterndto.repository.AgenceRepository;
import ga.banga.patterndto.service.dto.AgenceDTO;
import ga.banga.patterndto.service.mapper.AgenceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * Service Implementation for managing {@link Agence}.
 */
@Service
@Transactional
public class AgenceService {

    private static final Logger log = LoggerFactory.getLogger(AgenceService.class);

    private final AgenceRepository agenceRepository;

    private final AgenceMapper agenceMapper;

    public AgenceService(AgenceRepository agenceRepository, AgenceMapper agenceMapper) {
        this.agenceRepository = agenceRepository;
        this.agenceMapper = agenceMapper;
    }

    /**
     * Save a agence.
     *
     * @param agenceDTO the entity to save.
     * @return the persisted entity.
     */
    public AgenceDTO save(AgenceDTO agenceDTO) {
        log.debug("Request to save Agence : {}", agenceDTO);
        Agence agence = agenceMapper.toEntity(agenceDTO);
        agence = agenceRepository.save(agence);
        return agenceMapper.toDto(agence);
    }

    /**
     * Update a agence.
     *
     * @param agenceDTO the entity to save.
     * @return the persisted entity.
     */
    public AgenceDTO update(AgenceDTO agenceDTO) {
        log.debug("Request to update Agence : {}", agenceDTO);
        Agence agence = agenceMapper.toEntity(agenceDTO);
        agence = agenceRepository.save(agence);
        return agenceMapper.toDto(agence);
    }

    /**
     * Partially update a agence.
     *
     * @param agenceDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AgenceDTO> partialUpdate(AgenceDTO agenceDTO) {
        log.debug("Request to partially update Agence : {}", agenceDTO);

        return agenceRepository
            .findById(agenceDTO.getId())
            .map(existingAgence -> {
                agenceMapper.partialUpdate(existingAgence, agenceDTO);

                return existingAgence;
            })
            .map(agenceRepository::save)
            .map(agenceMapper::toDto);
    }

    /**
     * Get one agence by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AgenceDTO> findOne(UUID id) {
        log.debug("Request to get Agence : {}", id);
        return agenceRepository.findById(id).map(agenceMapper::toDto);
    }

    /**
     * Delete the agence by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Agence : {}", id);
        agenceRepository.deleteById(id);
    }
}
