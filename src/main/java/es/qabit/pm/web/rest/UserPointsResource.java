package es.qabit.pm.web.rest;

import es.qabit.pm.repository.UserPointsRepository;
import es.qabit.pm.security.AuthoritiesConstants;
import es.qabit.pm.security.SecurityUtils;
import es.qabit.pm.service.UserPointsQueryService;
import es.qabit.pm.service.UserPointsService;
import es.qabit.pm.service.UserService;
import es.qabit.pm.service.criteria.UserPointsCriteria;
import es.qabit.pm.service.dto.UserPointsDTO;
import es.qabit.pm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link es.qabit.pm.domain.UserPoints}.
 */
@RestController
@RequestMapping("/api")
public class UserPointsResource {

    private final Logger log = LoggerFactory.getLogger(UserPointsResource.class);

    private static final String ENTITY_NAME = "userPoints";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserPointsService userPointsService;

    private final UserPointsRepository userPointsRepository;

    private final UserService userService;

    private final UserPointsQueryService userPointsQueryService;

    public UserPointsResource(
        UserPointsService userPointsService,
        UserPointsRepository userPointsRepository,
        UserService userService,
        UserPointsQueryService userPointsQueryService
    ) {
        this.userPointsService = userPointsService;
        this.userPointsRepository = userPointsRepository;
        this.userService = userService;
        this.userPointsQueryService = userPointsQueryService;
    }

    /**
     * {@code POST  /user-points} : Create a new userPoints.
     *
     * @param userPointsDTO the userPointsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userPointsDTO, or with status {@code 400 (Bad Request)} if the userPoints has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-points")
    public ResponseEntity<UserPointsDTO> createUserPoints(@RequestBody UserPointsDTO userPointsDTO) throws URISyntaxException {
        log.debug("REST request to save UserPoints : {}", userPointsDTO);
        if (userPointsDTO.getId() != null) {
            throw new BadRequestAlertException("A new userPoints cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserPointsDTO result = userPointsService.save(userPointsDTO);
        return ResponseEntity
            .created(new URI("/api/user-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-points/:id} : Updates an existing userPoints.
     *
     * @param id the id of the userPointsDTO to save.
     * @param userPointsDTO the userPointsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userPointsDTO,
     * or with status {@code 400 (Bad Request)} if the userPointsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userPointsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-points/{id}")
    public ResponseEntity<UserPointsDTO> updateUserPoints(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserPointsDTO userPointsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update UserPoints : {}, {}", id, userPointsDTO);
        if (userPointsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userPointsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userPointsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UserPointsDTO result = userPointsService.update(userPointsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userPointsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /user-points/:id} : Partial updates given fields of an existing userPoints, field will ignore if it is null
     *
     * @param id the id of the userPointsDTO to save.
     * @param userPointsDTO the userPointsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userPointsDTO,
     * or with status {@code 400 (Bad Request)} if the userPointsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the userPointsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the userPointsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/user-points/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UserPointsDTO> partialUpdateUserPoints(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserPointsDTO userPointsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update UserPoints partially : {}, {}", id, userPointsDTO);
        if (userPointsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userPointsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userPointsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UserPointsDTO> result = userPointsService.partialUpdate(userPointsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userPointsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /user-points} : get all the userPoints.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userPoints in body.
     */
    @GetMapping("/user-points")
    public ResponseEntity<List<UserPointsDTO>> getAllUserPoints(
        UserPointsCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get UserPoints by criteria: {}", criteria);

        if (SecurityUtils.hasCurrentUserOnlyThisAuthorities(AuthoritiesConstants.USER)) {
            SecurityUtils
                .getCurrentUserLogin()
                .flatMap(userService::getUserWithAuthoritiesByLogin)
                .ifPresent(userId -> criteria.setUserId((LongFilter) new LongFilter().setEquals(userId.getId())));
        }

        Page<UserPointsDTO> page = userPointsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-points/count} : count all the userPoints.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/user-points/count")
    public ResponseEntity<Long> countUserPoints(UserPointsCriteria criteria) {
        log.debug("REST request to count UserPoints by criteria: {}", criteria);
        return ResponseEntity.ok().body(userPointsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /user-points/:id} : get the "id" userPoints.
     *
     * @param id the id of the userPointsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userPointsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-points/{id}")
    public ResponseEntity<UserPointsDTO> getUserPoints(@PathVariable Long id) {
        log.debug("REST request to get UserPoints : {}", id);
        Optional<UserPointsDTO> userPointsDTO = userPointsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userPointsDTO);
    }

    /**
     * {@code DELETE  /user-points/:id} : delete the "id" userPoints.
     *
     * @param id the id of the userPointsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-points/{id}")
    public ResponseEntity<Void> deleteUserPoints(@PathVariable Long id) {
        log.debug("REST request to delete UserPoints : {}", id);
        userPointsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
