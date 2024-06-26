package app.studymanager.modules.studyday.history;

import app.studymanager.modules.studyday.StudyDay;
import app.studymanager.modules.studyday.status.StudyDayStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_study_days_history")
public class StudyDayHistory {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_study_days_history", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_study_day", nullable = false)
    private StudyDay studyDay;

    @ManyToOne
    @JoinColumn(name = "id_study_days_status", nullable = false)
    private StudyDayStatus status;

    @Column(name = "responsible", nullable = false)
    private String responsible;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime createdAt;
}
