package es.qabit.pm.repository;

import es.qabit.pm.domain.UserPoints;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the UserPoints entity.
 */
@Repository
public interface UserPointsRepository extends JpaRepository<UserPoints, Long>, JpaSpecificationExecutor<UserPoints> {
    default Optional<UserPoints> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<UserPoints> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<UserPoints> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select userPoints from UserPoints userPoints left join fetch userPoints.user",
        countQuery = "select count(userPoints) from UserPoints userPoints"
    )
    Page<UserPoints> findAllWithToOneRelationships(Pageable pageable);

    @Query("select userPoints from UserPoints userPoints left join fetch userPoints.user")
    List<UserPoints> findAllWithToOneRelationships();

    @Query("select userPoints from UserPoints userPoints left join fetch userPoints.user where userPoints.id =:id")
    Optional<UserPoints> findOneWithToOneRelationships(@Param("id") Long id);
}
