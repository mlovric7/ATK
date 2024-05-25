package fer.infsus.atk.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "clandogadaj")
@ToString
@RequiredArgsConstructor
public class UserEvent {
    @EmbeddedId
    private UserEventId id;

    @MapsId("dogadajid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dogadajid", nullable = false)
    @ToString.Exclude
    private Event event;

    @MapsId("clanid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clanid", nullable = false)
    @ToString.Exclude
    private User user;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        UserEvent userEvent = (UserEvent) o;
        return getId() != null && Objects.equals(getId(), userEvent.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}