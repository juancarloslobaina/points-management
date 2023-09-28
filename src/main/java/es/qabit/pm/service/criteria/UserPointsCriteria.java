package es.qabit.pm.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link es.qabit.pm.domain.UserPoints} entity. This class is used
 * in {@link es.qabit.pm.web.rest.UserPointsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /user-points?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserPointsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter points;

    private LongFilter userId;

    private Boolean distinct;

    public UserPointsCriteria() {}

    public UserPointsCriteria(UserPointsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.points = other.points == null ? null : other.points.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public UserPointsCriteria copy() {
        return new UserPointsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getPoints() {
        return points;
    }

    public IntegerFilter points() {
        if (points == null) {
            points = new IntegerFilter();
        }
        return points;
    }

    public void setPoints(IntegerFilter points) {
        this.points = points;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public LongFilter userId() {
        if (userId == null) {
            userId = new LongFilter();
        }
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UserPointsCriteria that = (UserPointsCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(points, that.points) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, points, userId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserPointsCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (points != null ? "points=" + points + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
