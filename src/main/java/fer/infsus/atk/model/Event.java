package fer.infsus.atk.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "dogadaj")
@ToString
@RequiredArgsConstructor
public class Event {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "pocetak", nullable = false)
    private LocalDate startDate;

    @Column(name = "kraj", nullable = false)
    private LocalDate endDate;

    @Column(name = "brojmjesta")
    private Integer numberOfSeats;

    @Column(name = "naziv", nullable = false, length = 256)
    private String name;

    @Column(name = "opis", nullable = false, length = 1024)
    private String description;

    @Column(name = "adresa", nullable = false, length = 256)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organizatorid", nullable = false)
    @ToString.Exclude
    private Organizer organizer;

    @OneToMany(mappedBy = "event")
    @ToString.Exclude
    private Set<Feedback> feedbacks = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Event event = (Event) o;
        return getId() != null && Objects.equals(getId(), event.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}