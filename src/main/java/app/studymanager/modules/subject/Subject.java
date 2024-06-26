package app.studymanager.modules.subject;

import app.studymanager.modules.subject.level.SubjectLevel;
import app.studymanager.modules.subject.priority.SubjectPriority;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "tab_subjects")
public class Subject extends AbstractAggregateRoot<Subject> {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_subject", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_subject_level", nullable = false)
    private SubjectLevel level;

    @ManyToOne
    @JoinColumn(name = "id_subject_priority", nullable = false)
    private SubjectPriority priority;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime createdAt;

    @Column(name = "upated_at", nullable = false)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime updatedAt;
}
