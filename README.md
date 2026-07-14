# Atucanador

Aplicação Java para envio automático de e-mails utilizando SMTP.

---

## Requisitos

- Java 21
- Maven 3.9+

---

## Configuração

Edite o arquivo:

config/email.json

Exemplo:

```json
{
  "smtpHost": "smtp.office365.com",
  "smtpPort": 587,
  "usuario": "email@hotmail.com",
  "senha": "senha",
  "destinatario": "destino@email.com",
  "assunto": "Solicitação de atualização",
  "intervalo": 5,
  "tls": true,
  "enviarAoIniciar": true
}
```

---

## Compilar

```bash
mvn clean package
```

Será gerado:

```
target/atucanador-1.0.0-jar-with-dependencies.jar
```

---

## Executar

```bash
java -jar target/atucanador-1.0.0-jar-with-dependencies.jar
```

---

## Funcionamento

Ao iniciar:

- lê o arquivo `email.json`;
- envia um e-mail imediatamente (caso `enviarAoIniciar = true`);
- agenda novos envios conforme o intervalo configurado;
- permanece em execução até ser interrompido.

---

## Encerrar

Windows:

```
CTRL+C
```

Linux:

```
CTRL+C
```