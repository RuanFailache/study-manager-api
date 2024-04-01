package app.studymanager.modules.subject.priority;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_subjects_priorities")
public class SubjectPriority {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_subject_priority", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private SubjectPriorityEnum type;
}
