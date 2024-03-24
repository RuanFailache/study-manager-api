package app.studymanager.modules.studyday.status;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_study_days_status")
public class StudyDayStatus {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_study_days_status", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StudyDayStatusEnum status;
}
