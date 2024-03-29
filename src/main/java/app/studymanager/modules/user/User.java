package app.studymanager.modules.user;

import app.studymanager.modules.user.history.UserHistory;
import app.studymanager.modules.user.session.UserSession;
import app.studymanager.modules.user.status.UserStatus;
import app.studymanager.modules.user.subject.UserSubject;
import app.studymanager.modules.user.type.UserType;
import app.studymanager.modules.user.validationcode.UserValidationCode;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "tab_users")
public class User extends AbstractAggregateRoot<User> {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_user", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_users_status", nullable = false)
    private UserStatus status;

    @ManyToOne
    @JoinColumn(name = "id_users_type", nullable = false)
    private UserType type;

    @Column(name = "name")
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "user")
    private Set<UserHistory> users = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserHistory> histories = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserSession> sessions = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserSubject> subjects = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserValidationCode> validationCodes = new HashSet<>();
}