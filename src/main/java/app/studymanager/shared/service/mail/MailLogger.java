package app.studymanager.shared.service.mail;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class MailLogger {
    static final String SEND_MAIL = "Enviando email";
    static final String SEND_MAIL_ERROR = "Falha ao enviar email";
}
