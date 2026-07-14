package br.com.webmatte.atucanador.service;

import br.com.webmatte.atucanador.model.Configuracao;
import br.com.webmatte.atucanador.util.Constantes;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDateTime;
import java.util.Properties;

public class EnvioService {

    private final Configuracao config;

    public EnvioService(Configuracao config) {
        this.config = config;
    }

    public void enviar() {

        System.out.println("==========================================");
        System.out.println("Iniciando envio de e-mail");
        System.out.println("Data/Hora: " + LocalDateTime.now());
        System.out.println("Destino: " + config.getDestinatario());

        try {

            Properties props = criarPropriedades();
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                            config.getUsuario(),
                            config.getSenha());

                }
            });

            MimeMessage mensagem = criarMensagem(session);
            Transport.send(mensagem);
            System.out.println("E-mail enviado com sucesso.");
        } catch (MessagingException e) {
            System.err.println("Erro ao enviar e-mail.");
            e.printStackTrace();
        }
        System.out.println("==========================================");

    }

    private Properties criarPropriedades() {
        Properties props = new Properties();
        props.put("mail.smtp.host", config.getSmtpHost());
        props.put("mail.smtp.port", String.valueOf(config.getSmtpPort()));
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable",
                String.valueOf(config.isTls()));
        props.put("mail.smtp.connectiontimeout", "10000");
        props.put("mail.smtp.timeout", "10000");
        props.put("mail.smtp.writetimeout", "10000");
        return props;

    }

    private MimeMessage criarMensagem(Session session)
            throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(config.getUsuario()));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(config.getDestinatario()));
        message.setSubject(config.getAssunto(), "UTF-8");
        message.setText(Constantes.MENSAGEM, "UTF-8");
        return message;
    }

}