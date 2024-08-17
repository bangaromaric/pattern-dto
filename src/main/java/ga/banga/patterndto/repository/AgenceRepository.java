package ga.banga.patterndto.repository;

import ga.banga.patterndto.domain.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data JPA repository for the Agence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgenceRepository extends JpaRepository<Agence, UUID>, JpaSpecificationExecutor<Agence> {}
