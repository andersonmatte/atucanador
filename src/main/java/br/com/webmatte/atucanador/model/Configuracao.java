package br.com.webmatte.atucanador.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Configuracao {

    private String smtpHost;
    private int smtpPort;
    private String usuario;
    private String senha;
    private String destinatario;
    private String assunto;
    private long intervalo;
    private boolean tls;
    private boolean enviarAoIniciar;

    public Configuracao() {
    }

}