package br.com.webmatte.atucanador;

import br.com.webmatte.atucanador.model.Configuracao;
import br.com.webmatte.atucanador.scheduler.Agendador;
import br.com.webmatte.atucanador.service.ConfigLoader;
import br.com.webmatte.atucanador.service.EnvioService;

public class APP {

    public static void main(String[] args) {

        try {
            System.out.println("==============================================");
            System.out.println("ATUCANADOR");
            System.out.println("==============================================");
            Configuracao config = ConfigLoader.carregar();
            System.out.println("Configuração carregada.");
            EnvioService envioService = new EnvioService(config);
            Agendador agendador =
                    new Agendador(envioService, config);
            agendador.iniciar();
            System.out.println();
            System.out.println("Sistema em execução.");
            System.out.println("Pressione CTRL+C para finalizar.");
            Thread.currentThread().join();
        } catch (Exception e) {
            System.err.println("Erro fatal.");
            e.printStackTrace();
        }
    }

}