package app.studymanager.modules.subject;

import app.studymanager.modules.subject.level.SubjectLevel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.AbstractAggregateRoot;

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

    @Column(name = "name", nullable = false)
    private String name;
}
