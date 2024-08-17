package ga.banga.patterndto.service;

import ga.banga.patterndto.domain.Agence_;
import ga.banga.patterndto.domain.Utilisateur;
import ga.banga.patterndto.domain.Utilisateur_;
import ga.banga.patterndto.repository.UtilisateurRepository;
import ga.banga.patterndto.service.criteria.UtilisateurCriteria;
import ga.banga.patterndto.service.dto.UtilisateurDTO;
import ga.banga.patterndto.service.mapper.UtilisateurMapper;
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
 * Service for executing complex queries for {@link Utilisateur} entities in the database.
 * The main input is a {@link UtilisateurCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link UtilisateurDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UtilisateurQueryService extends QueryService<Utilisateur> {

    private static final Logger log = LoggerFactory.getLogger(UtilisateurQueryService.class);

    private final UtilisateurRepository utilisateurRepository;

    private final UtilisateurMapper utilisateurMapper;

    public UtilisateurQueryService(UtilisateurRepository utilisateurRepository, UtilisateurMapper utilisateurMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
    }

    /**
     * Return a {@link Page} of {@link UtilisateurDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UtilisateurDTO> findByCriteria(UtilisateurCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Utilisateur> specification = createSpecification(criteria);
        return utilisateurRepository.findAll(specification, page).map(utilisateurMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(UtilisateurCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Utilisateur> specification = createSpecification(criteria);
        return utilisateurRepository.count(specification);
    }

    /**
     * Function to convert {@link UtilisateurCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Utilisateur> createSpecification(UtilisateurCriteria criteria) {
        Specification<Utilisateur> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Utilisateur_.id));
            }
            if (criteria.getNom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNom(), Utilisateur_.nom));
            }
            if (criteria.getPrenom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrenom(), Utilisateur_.prenom));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Utilisateur_.email));
            }
            if (criteria.getPhoneNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhoneNumber(), Utilisateur_.phoneNumber));
            }
            if (criteria.getPassword() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPassword(), Utilisateur_.password));
            }
            if (criteria.getAgenceId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getAgenceId(), root -> root.join(Utilisateur_.agence, JoinType.LEFT).get(Agence_.id))
                );
            }
        }
        return specification;
    }
}
