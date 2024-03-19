package app.studymanager.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_user_sessions")
public class UserSession {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_user_session", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "access_token", nullable = false)
    private String token;

    @Column(name = "expires_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime expiresAt;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime createdAt;
}
