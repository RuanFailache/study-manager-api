package app.studymanager.modules.studyday.user;

import app.studymanager.modules.studyday.StudyDay;
import app.studymanager.modules.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_study_days_users")
public class StudyDayUser {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_study_days_user", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_study_day")
    private StudyDay studyDay;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime createdAt;
}