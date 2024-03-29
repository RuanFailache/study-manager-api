package app.studymanager.modules.user.type;

import app.studymanager.shared.exception.NotFoundException;
import app.studymanager.shared.util.ExceptionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTypeServiceImpl implements UserTypeService {
    private final UserTypeRepository userTypeRepository;

    public UserType findOrThrow(UserTypeEnum type) {
        log.info(UserTypeLogger.FIND_OR_THROW);
        try {
            var foundType = userTypeRepository.findByType(type);

            if (isNull(foundType)) {
                throw new NotFoundException(UserTypeLogger.FIND_OR_THROW_NOT_FOUND);
            }

            return foundType;
        } catch (Exception exception) {
            throw ExceptionUtil.handle(exception, UserTypeLogger.FIND_OR_THROW_ERROR);
        }
    }
}
