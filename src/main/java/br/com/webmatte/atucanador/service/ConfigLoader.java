package br.com.webmatte.atucanador.service;

import br.com.webmatte.atucanador.model.Configuracao;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConfigLoader {

    private static final String CAMINHO = "config/email.json";

    private ConfigLoader() {
    }

    public static Configuracao carregar() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(CAMINHO), Configuracao.class);

    }

}