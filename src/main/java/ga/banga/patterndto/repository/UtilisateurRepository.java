package ga.banga.patterndto.repository;

import ga.banga.patterndto.domain.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for the Utilisateur entity.
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, UUID>, JpaSpecificationExecutor<Utilisateur> {
    default Optional<Utilisateur> findOneWithEagerRelationships(UUID id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Utilisateur> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Utilisateur> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select utilisateur from Utilisateur utilisateur left join fetch utilisateur.agence",
        countQuery = "select count(utilisateur) from Utilisateur utilisateur"
    )
    Page<Utilisateur> findAllWithToOneRelationships(Pageable pageable);

    @Query("select utilisateur from Utilisateur utilisateur left join fetch utilisateur.agence")
    List<Utilisateur> findAllWithToOneRelationships();

    @Query("select utilisateur from Utilisateur utilisateur left join fetch utilisateur.agence where utilisateur.id =:id")
    Optional<Utilisateur> findOneWithToOneRelationships(@Param("id") UUID id);
}
