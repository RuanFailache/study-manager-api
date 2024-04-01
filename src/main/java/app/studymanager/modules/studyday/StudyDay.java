package app.studymanager.modules.studyday;

import app.studymanager.modules.studyday.history.StudyDayHistory;
import app.studymanager.modules.studyday.status.StudyDayStatus;
import app.studymanager.modules.studyday.subject.StudyDaySubject;
import app.studymanager.modules.user.User;
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
@Table(name = "tab_study_days")
public class StudyDay extends AbstractAggregateRoot<StudyDay> {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_study_day", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_status", nullable = false)
    private StudyDayStatus status;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "scheduled_at")
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime scheduledAt;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "studyDay")
    private Set<StudyDaySubject> subjects = new HashSet<>();

    @OneToMany(mappedBy = "studyDay")
    private Set<StudyDayHistory> histories = new HashSet<>();
}
