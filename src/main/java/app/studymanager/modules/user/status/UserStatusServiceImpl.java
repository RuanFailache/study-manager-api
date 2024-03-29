package app.studymanager.modules.user.status;

import app.studymanager.shared.exception.NotFoundException;
import app.studymanager.shared.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class UserStatusServiceImpl implements UserStatusService {
    private static final Logger logger = LoggerFactory.getLogger(UserStatusService.class);

    private final UserStatusRepository userStatusRepository;

    public UserStatusServiceImpl(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    public UserStatus findOrThrow(UserStatusEnum status) {
        logger.info(UserStatusLogger.FIND_OR_THROW);
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
