package app.studymanager.modules.user.type;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_users_types")
public class UserType {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_users_type", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserTypeEnum type;
}
