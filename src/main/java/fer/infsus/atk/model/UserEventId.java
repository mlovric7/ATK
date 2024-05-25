package fer.infsus.atk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class UserEventId implements Serializable {
    @Serial
    private static final long serialVersionUID = -6843417982012145699L;
    @Column(name = "dogadajid", nullable = false)
    private Integer eventId;

    @Column(name = "clanid", nullable = false)
    private Integer userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEventId entity = (UserEventId) o;
        return Objects.equals(this.eventId, entity.eventId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, userId);
    }

}