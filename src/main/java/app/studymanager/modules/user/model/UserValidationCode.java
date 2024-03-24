package app.studymanager.modules.user.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_user_validation_codes")
public class UserValidationCode {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_user_validation_code", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "validation_code", nullable = false)
    private String code;

    @Column(name = "expires_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime expiresAt;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime createdAt;

    public void create(User user, String code) {
        setCode(code);
        setUser(user);
        setExpiresAt(OffsetDateTime.now().plusMinutes(5));
    }
}
