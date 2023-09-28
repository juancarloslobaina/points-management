package es.qabit.pm.service;

import es.qabit.pm.service.dto.UserPointsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link es.qabit.pm.domain.UserPoints}.
 */
public interface UserPointsService {
    /**
     * Save a userPoints.
     *
     * @param userPointsDTO the entity to save.
     * @return the persisted entity.
     */
    UserPointsDTO save(UserPointsDTO userPointsDTO);

    /**
     * Updates a userPoints.
     *
     * @param userPointsDTO the entity to update.
     * @return the persisted entity.
     */
    UserPointsDTO update(UserPointsDTO userPointsDTO);

    /**
     * Partially updates a userPoints.
     *
     * @param userPointsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UserPointsDTO> partialUpdate(UserPointsDTO userPointsDTO);

    /**
     * Get all the userPoints.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserPointsDTO> findAll(Pageable pageable);

    /**
     * Get all the userPoints with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserPointsDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" userPoints.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserPointsDTO> findOne(Long id);

    /**
     * Delete the "id" userPoints.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
