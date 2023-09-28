package es.qabit.pm.service;

import es.qabit.pm.domain.*; // for static metamodels
import es.qabit.pm.domain.UserPoints;
import es.qabit.pm.repository.UserPointsRepository;
import es.qabit.pm.service.criteria.UserPointsCriteria;
import es.qabit.pm.service.dto.UserPointsDTO;
import es.qabit.pm.service.mapper.UserPointsMapper;
import jakarta.persistence.criteria.JoinType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link UserPoints} entities in the database.
 * The main input is a {@link UserPointsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UserPointsDTO} or a {@link Page} of {@link UserPointsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UserPointsQueryService extends QueryService<UserPoints> {

    private final Logger log = LoggerFactory.getLogger(UserPointsQueryService.class);

    private final UserPointsRepository userPointsRepository;

    private final UserPointsMapper userPointsMapper;

    public UserPointsQueryService(UserPointsRepository userPointsRepository, UserPointsMapper userPointsMapper) {
        this.userPointsRepository = userPointsRepository;
        this.userPointsMapper = userPointsMapper;
    }

    /**
     * Return a {@link List} of {@link UserPointsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UserPointsDTO> findByCriteria(UserPointsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<UserPoints> specification = createSpecification(criteria);
        return userPointsMapper.toDto(userPointsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UserPointsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UserPointsDTO> findByCriteria(UserPointsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<UserPoints> specification = createSpecification(criteria);
        return userPointsRepository.findAll(specification, page).map(userPointsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(UserPointsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<UserPoints> specification = createSpecification(criteria);
        return userPointsRepository.count(specification);
    }

    /**
     * Function to convert {@link UserPointsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<UserPoints> createSpecification(UserPointsCriteria criteria) {
        Specification<UserPoints> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), UserPoints_.id));
            }
            if (criteria.getPoints() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPoints(), UserPoints_.points));
            }
            if (criteria.getUserId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getUserId(), root -> root.join(UserPoints_.user, JoinType.LEFT).get(User_.id))
                    );
            }
        }
        return specification;
    }
}
