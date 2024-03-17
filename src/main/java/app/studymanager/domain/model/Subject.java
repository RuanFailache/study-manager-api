package app.studymanager.domain.model;

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

    @Column(name = "level", nullable = false)
    @Enumerated(EnumType.STRING)
    private SubjectLevel level;

    @Column(name = "name", nullable = false)
    private String name;
}
