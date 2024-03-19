package app.studymanager.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_users_history")
public class UserHistory {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_user_history", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "user_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType type;

    @Column(name = "responsible", nullable = false)
    private String responsible;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime createdAt;

    public void insert(User user, String responsible, String description) {
        setUser(user);
        setType(user.getType());
        setDescription(description);
        setResponsible(responsible);
    }
}
