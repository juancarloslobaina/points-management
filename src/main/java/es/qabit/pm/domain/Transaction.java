package es.qabit.pm.domain;

import es.qabit.pm.domain.enumeration.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "points")
    private Integer points;

    @Column(name = "date")
    private Instant date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne(optional = false)
    @NotNull
    private User userFrom;

    @ManyToOne(optional = false)
    @NotNull
    private User userTo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Transaction id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoints() {
        return this.points;
    }

    public Transaction points(Integer points) {
        this.setPoints(points);
        return this;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Instant getDate() {
        return this.date;
    }

    public Transaction date(Instant date) {
        this.setDate(date);
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Status getStatus() {
        return this.status;
    }

    public Transaction status(Status status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUserFrom() {
        return this.userFrom;
    }

    public void setUserFrom(User user) {
        this.userFrom = user;
    }

    public Transaction userFrom(User user) {
        this.setUserFrom(user);
        return this;
    }

    public User getUserTo() {
        return this.userTo;
    }

    public void setUserTo(User user) {
        this.userTo = user;
    }

    public Transaction userTo(User user) {
        this.setUserTo(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        return id != null && id.equals(((Transaction) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transaction{" +
            "id=" + getId() +
            ", points=" + getPoints() +
            ", date='" + getDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
