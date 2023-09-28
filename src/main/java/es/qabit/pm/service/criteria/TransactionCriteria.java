package es.qabit.pm.service.criteria;

import es.qabit.pm.domain.enumeration.Status;
import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link es.qabit.pm.domain.Transaction} entity. This class is used
 * in {@link es.qabit.pm.web.rest.TransactionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /transactions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TransactionCriteria implements Serializable, Criteria {

    /**
     * Class for filtering Status
     */
    public static class StatusFilter extends Filter<Status> {

        public StatusFilter() {}

        public StatusFilter(StatusFilter filter) {
            super(filter);
        }

        @Override
        public StatusFilter copy() {
            return new StatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter points;

    private InstantFilter date;

    private StatusFilter status;

    private LongFilter userFromId;

    private LongFilter userToId;

    private Boolean distinct;

    public TransactionCriteria() {}

    public TransactionCriteria(TransactionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.points = other.points == null ? null : other.points.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.userFromId = other.userFromId == null ? null : other.userFromId.copy();
        this.userToId = other.userToId == null ? null : other.userToId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public TransactionCriteria copy() {
        return new TransactionCriteria(this);
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

    public InstantFilter getDate() {
        return date;
    }

    public InstantFilter date() {
        if (date == null) {
            date = new InstantFilter();
        }
        return date;
    }

    public void setDate(InstantFilter date) {
        this.date = date;
    }

    public StatusFilter getStatus() {
        return status;
    }

    public StatusFilter status() {
        if (status == null) {
            status = new StatusFilter();
        }
        return status;
    }

    public void setStatus(StatusFilter status) {
        this.status = status;
    }

    public LongFilter getUserFromId() {
        return userFromId;
    }

    public LongFilter userFromId() {
        if (userFromId == null) {
            userFromId = new LongFilter();
        }
        return userFromId;
    }

    public void setUserFromId(LongFilter userFromId) {
        this.userFromId = userFromId;
    }

    public LongFilter getUserToId() {
        return userToId;
    }

    public LongFilter userToId() {
        if (userToId == null) {
            userToId = new LongFilter();
        }
        return userToId;
    }

    public void setUserToId(LongFilter userToId) {
        this.userToId = userToId;
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
        final TransactionCriteria that = (TransactionCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(points, that.points) &&
            Objects.equals(date, that.date) &&
            Objects.equals(status, that.status) &&
            Objects.equals(userFromId, that.userFromId) &&
            Objects.equals(userToId, that.userToId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, points, date, status, userFromId, userToId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransactionCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (points != null ? "points=" + points + ", " : "") +
            (date != null ? "date=" + date + ", " : "") +
            (status != null ? "status=" + status + ", " : "") +
            (userFromId != null ? "userFromId=" + userFromId + ", " : "") +
            (userToId != null ? "userToId=" + userToId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
