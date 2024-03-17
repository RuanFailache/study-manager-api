package app.studymanager.domain.event;

import app.studymanager.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatedUserEvent {
    private User user;
}
