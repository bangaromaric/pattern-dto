package ga.banga.patterndto.service;

import ga.banga.patterndto.domain.Agence;
import ga.banga.patterndto.domain.Agence_;
import ga.banga.patterndto.domain.Utilisateur_;
import ga.banga.patterndto.repository.AgenceRepository;
import ga.banga.patterndto.service.criteria.AgenceCriteria;
import ga.banga.patterndto.service.dto.AgenceDTO;
import ga.banga.patterndto.service.mapper.AgenceMapper;
import jakarta.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Agence} entities in the database.
 * The main input is a {@link AgenceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link AgenceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AgenceQueryService extends QueryService<Agence> {

    private static final Logger log = LoggerFactory.getLogger(AgenceQueryService.class);

    private final AgenceRepository agenceRepository;

    private final AgenceMapper agenceMapper;

    public AgenceQueryService(AgenceRepository agenceRepository, AgenceMapper agenceMapper) {
        this.agenceRepository = agenceRepository;
        this.agenceMapper = agenceMapper;
    }

    /**
     * Return a {@link Page} of {@link AgenceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AgenceDTO> findByCriteria(AgenceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Agence> specification = createSpecification(criteria);
        return agenceRepository.findAll(specification, page).map(agenceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AgenceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Agence> specification = createSpecification(criteria);
        return agenceRepository.count(specification);
    }

    /**
     * Function to convert {@link AgenceCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Agence> createSpecification(AgenceCriteria criteria) {
        Specification<Agence> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Agence_.id));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Agence_.libelle));
            }
            if (criteria.getAdresse() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAdresse(), Agence_.adresse));
            }
            if (criteria.getRib() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRib(), Agence_.rib));
            }
            if (criteria.getUtilisateurId() != null) {
                specification = specification.and(
                    buildSpecification(
                        criteria.getUtilisateurId(),
                        root -> root.join(Agence_.utilisateurs, JoinType.LEFT).get(Utilisateur_.id)
                    )
                );
            }
        }
        return specification;
    }
}
