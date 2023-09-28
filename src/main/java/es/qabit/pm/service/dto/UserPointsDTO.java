package es.qabit.pm.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link es.qabit.pm.domain.UserPoints} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserPointsDTO implements Serializable {

    private Long id;

    private Integer points;

    private UserDTO user;

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserPointsDTO)) {
            return false;
        }

        UserPointsDTO userPointsDTO = (UserPointsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userPointsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserPointsDTO{" +
            "id=" + getId() +
            ", points=" + getPoints() +
            ", user=" + getUser() +
            "}";
    }
}
