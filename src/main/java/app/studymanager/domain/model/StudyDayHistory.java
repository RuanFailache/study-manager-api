package app.studymanager.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "tab_study_days_history")
public class StudyDayHistory extends AbstractAggregateRoot<StudyDayHistory> {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_study_day_history", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_study_day", nullable = false)
    private StudyDay studyDay;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
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
