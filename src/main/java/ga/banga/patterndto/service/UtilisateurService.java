package ga.banga.patterndto.service;

import ga.banga.patterndto.domain.Utilisateur;
import ga.banga.patterndto.repository.UtilisateurRepository;
import ga.banga.patterndto.service.dto.UtilisateurDTO;
import ga.banga.patterndto.service.dto.UtilisateurSimpleDTO;
import ga.banga.patterndto.service.mapper.UtilisateurMapper;
import ga.banga.patterndto.service.mapper.UtilisateurSimpleMapper;
import ga.banga.patterndto.service.utils.EncryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Implementation for managing {@link Utilisateur}.
 */
@Service
@Transactional
public class UtilisateurService {

    private static final Logger log = LoggerFactory.getLogger(UtilisateurService.class);

    private final UtilisateurRepository utilisateurRepository;

    private final UtilisateurMapper utilisateurMapper;
    private final UtilisateurSimpleMapper utilisateurSimpleMapper;
    private SecretKey secretKey;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, UtilisateurMapper utilisateurMapper, UtilisateurSimpleMapper utilisateurSimpleMapper) throws Exception {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
        this.utilisateurSimpleMapper = utilisateurSimpleMapper;
        // Générer une clé au démarrage ou charger depuis un stockage sécurisé
        this.secretKey = EncryptionUtil.generateKey();
    }

    /**
     * Save a utilisateur.
     *
     * @param utilisateurDTO the entity to save.
     * @return the persisted entity.
     */
    public UtilisateurSimpleDTO save(UtilisateurDTO utilisateurDTO) throws Exception {
        log.debug("Request to save Utilisateur : {}", utilisateurDTO);
        utilisateurDTO.setPassword(EncryptionUtil.encrypt(utilisateurDTO.getPassword(), secretKey));
        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDTO);
        utilisateur = utilisateurRepository.save(utilisateur);
        return utilisateurSimpleMapper.toDto(utilisateur);
//        return utilisateurMapper.toDto(utilisateur);
    }

    /**
     * Save a utilisateur.
     *
     * @param utilisateurDTO the entity to save.
     * @return the persisted entity.
     */
    public UtilisateurSimpleDTO save_2(UtilisateurDTO utilisateurDTO) throws Exception {
        log.debug("Request to save Utilisateur : {}", utilisateurDTO);
        utilisateurDTO.setPassword(EncryptionUtil.encrypt(utilisateurDTO.getPassword(), secretKey));
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setPrenom(utilisateurDTO.getPrenom());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setPassword(utilisateurDTO.getPassword());
        utilisateur.setPhoneNumber(utilisateurDTO.getPhoneNumber());

        utilisateur = utilisateurRepository.save(utilisateur);
        return utilisateurSimpleMapper.toDto(utilisateur);
    }


    /**
     * Save a utilisateur.
     *
     * @param utilisateur the entity to save.
     * @return the persisted entity.
     */
    public Utilisateur save_1(Utilisateur utilisateur) throws Exception {
        log.debug("Request to save Utilisateur : {}", utilisateur);
        utilisateur.setPassword(EncryptionUtil.encrypt(utilisateur.getPassword(), secretKey));
        utilisateur = utilisateurRepository.save(utilisateur);
        return utilisateur;
    }

    /**
     * Update a utilisateur.
     *
     * @param utilisateurDTO the entity to save.
     * @return the persisted entity.
     */
    public UtilisateurDTO update(UtilisateurDTO utilisateurDTO) {
        log.debug("Request to update Utilisateur : {}", utilisateurDTO);
        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDTO);
        utilisateur = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.toDto(utilisateur);
    }

    /**
     * Partially update a utilisateur.
     *
     * @param utilisateurDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UtilisateurDTO> partialUpdate(UtilisateurDTO utilisateurDTO) {
        log.debug("Request to partially update Utilisateur : {}", utilisateurDTO);

        return utilisateurRepository
            .findById(utilisateurDTO.getId())
            .map(existingUtilisateur -> {
                utilisateurMapper.partialUpdate(existingUtilisateur, utilisateurDTO);

                return existingUtilisateur;
            })
            .map(utilisateurRepository::save)
            .map(utilisateurMapper::toDto);
    }

    /**
     * Get all the utilisateurs with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<UtilisateurDTO> findAllWithEagerRelationships(Pageable pageable) {
        return utilisateurRepository.findAllWithEagerRelationships(pageable).map(utilisateurMapper::toDto);
    }

    /**
     * Get one utilisateur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UtilisateurDTO> findOne(UUID id) {
        log.debug("Request to get Utilisateur : {}", id);
        return utilisateurRepository.findOneWithEagerRelationships(id).map(utilisateurMapper::toDto);
    }

    /**
     * Delete the utilisateur by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Utilisateur : {}", id);
        utilisateurRepository.deleteById(id);
    }

    public String decryptAttribute(String encryptedAttribute) throws Exception {
        return EncryptionUtil.decrypt(encryptedAttribute, secretKey);
    }

    // Optionnel : stocker et charger la clé en tant que chaîne
    public void saveKey(String filePath) throws IOException {
        String keyString = EncryptionUtil.keyToString(secretKey);
        Files.write(Paths.get(filePath), keyString.getBytes());
    }

    public void loadKey(String filePath) throws IOException {
        String keyString = new String(Files.readAllBytes(Paths.get(filePath)));
        this.secretKey = EncryptionUtil.stringToKey(keyString);
    }
}
