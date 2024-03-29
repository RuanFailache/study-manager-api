package app.studymanager.modules.user.status;

import app.studymanager.shared.exception.NotFoundException;
import app.studymanager.shared.util.ExceptionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserStatusServiceImpl implements UserStatusService {
    private final UserStatusRepository userStatusRepository;

    public UserStatus findOrThrow(UserStatusEnum status) {
        log.info(UserStatusLogger.FIND_OR_THROW);
        try {
            var foundStatus = userStatusRepository.findByStatus(status);

            if (isNull(foundStatus)) {
                throw new NotFoundException(UserStatusLogger.FIND_OR_THROW_NOT_FOUND);
            }

            return foundStatus;
        } catch (Exception exception) {
            throw ExceptionUtil.handle(exception, UserStatusLogger.FIND_OR_THROW_ERROR);
        }
    }
}
