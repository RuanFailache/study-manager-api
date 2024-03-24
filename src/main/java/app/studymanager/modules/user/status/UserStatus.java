package app.studymanager.modules.user.status;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_users_status")
public class UserStatus {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_users_status", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatusEnum status;
}
