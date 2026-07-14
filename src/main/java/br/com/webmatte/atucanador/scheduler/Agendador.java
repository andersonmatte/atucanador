package br.com.webmatte.atucanador.scheduler;

import br.com.webmatte.atucanador.model.Configuracao;
import br.com.webmatte.atucanador.service.EnvioService;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Agendador {

    private final ScheduledExecutorService scheduler;
    private final EnvioService envioService;
    private final Configuracao config;

    public Agendador(EnvioService envioService, Configuracao config) {
        this.envioService = envioService;
        this.config = config;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public void iniciar() {
        System.out.println("----------------------------------------");
        System.out.println("Agendador iniciado");
        System.out.println("Data/Hora: " + LocalDateTime.now());
        System.out.println("Intervalo: " + config.getIntervalo() + " minuto(s)");
        System.out.println("----------------------------------------");
        if (config.isEnviarAoIniciar()) {
            System.out.println("Realizando envio inicial...");
            envioService.enviar();
        }
        scheduler.scheduleAtFixedRate(
                envioService::enviar,
                config.getIntervalo(),
                config.getIntervalo(),
                TimeUnit.MINUTES);
    }

    public void parar() {
        scheduler.shutdown();
        System.out.println("Agendador encerrado.");
    }

}