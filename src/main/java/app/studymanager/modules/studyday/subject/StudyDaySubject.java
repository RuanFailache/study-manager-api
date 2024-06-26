package app.studymanager.modules.studyday.subject;

import app.studymanager.modules.studyday.StudyDay;
import app.studymanager.modules.subject.Subject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_study_days_subjects")
public class StudyDaySubject {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_study_days_subject", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_study_day")
    private StudyDay studyDay;

    @ManyToOne
    @JoinColumn(name = "id_subject")
    private Subject subject;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime createdAt;
}
