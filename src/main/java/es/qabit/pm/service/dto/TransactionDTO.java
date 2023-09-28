package es.qabit.pm.service.dto;

import es.qabit.pm.domain.enumeration.Status;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link es.qabit.pm.domain.Transaction} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TransactionDTO implements Serializable {

    private Long id;

    private Integer points;

    private Instant date;

    private Status status;

    private UserDTO userFrom;

    private UserDTO userTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserDTO getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(UserDTO userFrom) {
        this.userFrom = userFrom;
    }

    public UserDTO getUserTo() {
        return userTo;
    }

    public void setUserTo(UserDTO userTo) {
        this.userTo = userTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransactionDTO)) {
            return false;
        }

        TransactionDTO transactionDTO = (TransactionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, transactionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransactionDTO{" +
            "id=" + getId() +
            ", points=" + getPoints() +
            ", date='" + getDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", userFrom=" + getUserFrom() +
            ", userTo=" + getUserTo() +
            "}";
    }
}
