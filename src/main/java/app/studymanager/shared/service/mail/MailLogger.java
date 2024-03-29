package app.studymanager.shared.service.mail;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class MailLogger {
    static final String DEVELOPMENT = "E-mail enviado para {}";
    static final String CONFIGURE_MAIL = "Configurando envio de email para {}";
    static final String CONFIGURE_MAIL_ERROR = "Falha ao configurar envio de email";
    static final String SEND_MAIL = "Enviando email";
    static final String SEND_MAIL_ERROR = "Falha ao enviar email";
}
