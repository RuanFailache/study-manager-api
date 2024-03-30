package app.studymanager.modules.user.type;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTypeServiceImpl implements UserTypeService {
    private final UserTypeRepository userTypeRepository;

    public UserType findOrThrow(UserTypeEnum type) {
        log.info("Buscando tipo do usuário");
        return userTypeRepository.findByType(type).orElseThrow(UserTypeException::notFound);
    }
}
