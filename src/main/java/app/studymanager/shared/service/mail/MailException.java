package app.studymanager.shared.service.mail;

import app.studymanager.shared.exception.HttpRequestException;
import app.studymanager.shared.exception.InternalServerErrorException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
class MailException {
    static HttpRequestException fail() {
        return new InternalServerErrorException("Falha ao enviar email");
    }
}
