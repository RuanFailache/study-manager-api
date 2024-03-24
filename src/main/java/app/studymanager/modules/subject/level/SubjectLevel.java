package app.studymanager.modules.subject.level;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_subjects_levels")
public class SubjectLevel {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_subjects_level", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private SubjectLevelEnum type;
}
