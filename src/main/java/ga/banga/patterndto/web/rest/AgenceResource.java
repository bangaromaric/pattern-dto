package ga.banga.patterndto.web.rest;

import ga.banga.patterndto.repository.AgenceRepository;
import ga.banga.patterndto.service.AgenceQueryService;
import ga.banga.patterndto.service.AgenceService;
import ga.banga.patterndto.service.criteria.AgenceCriteria;
import ga.banga.patterndto.service.dto.AgenceDTO;
import ga.banga.patterndto.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for managing {@link ga.banga.patterndto.domain.Agence}.
 */
@RestController
@RequestMapping("/api/agences")
public class AgenceResource {

    private static final Logger log = LoggerFactory.getLogger(AgenceResource.class);

    private static final String ENTITY_NAME = "agence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AgenceService agenceService;

    private final AgenceRepository agenceRepository;

    private final AgenceQueryService agenceQueryService;

    public AgenceResource(AgenceService agenceService, AgenceRepository agenceRepository, AgenceQueryService agenceQueryService) {
        this.agenceService = agenceService;
        this.agenceRepository = agenceRepository;
        this.agenceQueryService = agenceQueryService;
    }

    /**
     * {@code POST  /agences} : Create a new agences.
     *
     * @param agenceDTO the agenceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new agenceDTO, or with status {@code 400 (Bad Request)} if the agence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<AgenceDTO> createAgence(@Valid @RequestBody AgenceDTO agenceDTO) throws URISyntaxException {
        log.debug("REST request to save Agence : {}", agenceDTO);
        if (agenceDTO.getId() != null) {
            throw new BadRequestAlertException("A new agence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        agenceDTO = agenceService.save(agenceDTO);
        return ResponseEntity.created(new URI("/api/agences/" + agenceDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, agenceDTO.getId().toString()))
            .body(agenceDTO);
    }

    /**
     * {@code PUT  /agences/:id} : Updates an existing agence.
     *
     * @param id the id of the agenceDTO to save.
     * @param agenceDTO the agenceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated agenceDTO,
     * or with status {@code 400 (Bad Request)} if the agenceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the agenceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AgenceDTO> updateAgence(
        @PathVariable(value = "id", required = false) final UUID id,
        @Valid @RequestBody AgenceDTO agenceDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Agence : {}, {}", id, agenceDTO);
        if (agenceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, agenceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!agenceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        agenceDTO = agenceService.update(agenceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, agenceDTO.getId().toString()))
            .body(agenceDTO);
    }

    /**
     * {@code PATCH  /agences/:id} : Partial updates given fields of an existing agence, field will ignore if it is null
     *
     * @param id the id of the agenceDTO to save.
     * @param agenceDTO the agenceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated agenceDTO,
     * or with status {@code 400 (Bad Request)} if the agenceDTO is not valid,
     * or with status {@code 404 (Not Found)} if the agenceDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the agenceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AgenceDTO> partialUpdateAgence(
        @PathVariable(value = "id", required = false) final UUID id,
        @NotNull @RequestBody AgenceDTO agenceDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Agence partially : {}, {}", id, agenceDTO);
        if (agenceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, agenceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!agenceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AgenceDTO> result = agenceService.partialUpdate(agenceDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, agenceDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /agences} : get all the agences.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of agences in body.
     */
    @GetMapping("")
    public ResponseEntity<List<AgenceDTO>> getAllAgences(
        AgenceCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Agences by criteria: {}", criteria);

        Page<AgenceDTO> page = agenceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /agences/count} : count all the agences.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countAgences(AgenceCriteria criteria) {
        log.debug("REST request to count Agences by criteria: {}", criteria);
        return ResponseEntity.ok().body(agenceQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /agences/:id} : get the "id" agence.
     *
     * @param id the id of the agenceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the agenceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AgenceDTO> getAgence(@PathVariable("id") UUID id) {
        log.debug("REST request to get Agence : {}", id);
        Optional<AgenceDTO> agenceDTO = agenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(agenceDTO);
    }

    /**
     * {@code DELETE  /agences/:id} : delete the "id" agence.
     *
     * @param id the id of the agenceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgence(@PathVariable("id") UUID id) {
        log.debug("REST request to delete Agence : {}", id);
        agenceService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
