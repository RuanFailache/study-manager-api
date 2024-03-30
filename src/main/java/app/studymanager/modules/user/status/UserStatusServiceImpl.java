package app.studymanager.modules.user.status;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserStatusServiceImpl implements UserStatusService {
    private final UserStatusRepository userStatusRepository;

    public UserStatus findOrThrow(UserStatusEnum status) {
        log.info("Buscando status do usuário");
        return userStatusRepository.findByStatus(status).orElseThrow(UserStatusException::notFound);
    }
}
