package app.studymanager.modules.user.type;

import app.studymanager.shared.exception.NotFoundException;
import app.studymanager.shared.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class UserTypeServiceImpl implements UserTypeService {
    private static final Logger logger = LoggerFactory.getLogger(UserTypeService.class);

    private final UserTypeRepository userTypeRepository;

    public UserTypeServiceImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    public UserType findOrThrow(UserTypeEnum type) {
        logger.info(UserTypeLogger.FIND_OR_THROW);
        try {
            var foundType = userTypeRepository.findByType(type);

            if (isNull(foundType)) {
                throw new NotFoundException(UserTypeLogger.FIND_OR_THROW_NOT_FOUND);
            }

            return foundType;
        } catch (Exception exception) {
            logger.error(UserTypeLogger.FIND_OR_THROW_ERROR, exception);
            throw ExceptionUtil.handle(exception, UserTypeLogger.FIND_OR_THROW_ERROR);
        }
    }
}
