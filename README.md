<h1 align="center">Desafio Técnico - Luizalabs</h1>
<p align="center">API de gerenciamento dos produtos favoritos de um cliente.</p>

<div align="center">

[![Build Status](https://circleci.com/gh/kendaozinho/luizalabs-desafio-tecnico.svg?style=svg)](https://circleci.com/gh/kendaozinho/luizalabs-desafio-tecnico/?branch=master) [![Coverage Status](https://coveralls.io/repos/github/kendaozinho/luizalabs-desafio-tecnico/badge.svg?branch=master&service=github)](https://coveralls.io/github/kendaozinho/luizalabs-desafio-tecnico?branch=master)

</div>

## Pré-requisitos

* Java 11
* Docker

## Instalação

Antes de inicializar o projeto, são necessários os seguintes recursos:

* __Redis__ (opcional) - Banco de dados utilizado para cachear os produtos;
* __PostgreSQL__ - Banco de dados utilizado para armazenar as informações dos clientes e a relação que eles possuem com os produtos;

Caso não exista estes recursos, eles podem ser adquiridos e instalados na máquina local através do `Docker`:

```sh
docker-compose up -d
```

Os bancos de dados estarão vazios e só serão populados pelo `Flyway` após a primeira execução do projeto.

Por fim, com o `Java 11` instalado, basta baixar as dependências do projeto e realizar a compilação:

```sh
./mvnw clean install -DskipTests
```

## Inicialização

Para inicializar a API é só executar o seguinte comando:

```sh
./mvnw spring-boot:run
```

## Testes

Os testes foram implementados de forma à validar os fluxos como um todo.

Para rodar os testes basta executar o seguinte comando:

```sh
./mvnw test
```

O resultado da cobertura dos testes fica [neste arquivo](./target/jacoco/index.html) (gerado através do plugin `Jacoco`).

Este projeto possui CI/CD integrados com o `CircleCI/Coveralls` e o `Heroku`.

## Documentação

Esta API possui os seguintes endpoints:

* __/v1/customers__ - Obter, cadastrar, atualizar e remover clientes;
* __/v1/customers/products__ - Obter, cadastrar e remover os produtos favoritos de um cliente;
* __/actuator/health__ - Analisar o status da aplicação (gerado automaticamento pelo `Actuator`);

As rotas realizam a autenticação via `JWT` com base na chave informada na variável de ambiente.

O `Swagger` é responsável por gerar uma documentação da API de maneira automática:

* http://localhost:8080/swagger-ui/
* https://luizalabs-customer-api.herokuapp.com/swagger-ui/

Estas URLs disponibilizam um token de autenticação válido (apenas no ambiente de desenvolvimento).

## Variáveis de ambiente

Esta é a lista das variáveis de ambiente utilizas pela aplicação, basta alterar os valores conforme a necessidade.

| Nome | Descrição | Tipo | Valor Padrão |
|------|:---------:|:----:|-------------:|
| SPRING_DATASOURCE_HOST | Endereço de conexão com o banco de dados | `String` | `localhost` |
| SPRING_DATASOURCE_PORT | Porta de conexão com o banco de dados | `Integer` | `5432` |
| SPRING_DATASOURCE_DATABASE | Nome do banco de dados | `String` | `customer` |
| SPRING_DATASOURCE_USERNAME | Usuário do banco de dados | `String` | `usr_customer` |
| SPRING_DATASOURCE_PASSWORD | Senha do banco de dados | `String` | `12345` |
| SPRING_DATASOURCE_DRIVER_CLASS_NAME | Nome do driver de conexão com o banco de dados | `String` | `org.postgresql.Driver` |
| SPRING_FLYWAY_ENABLED | Habilitar o Flyway | `Boolean` | `true` |
| SPRING_REDIS_ENABLED | Habilitar o Redis | `Boolean` | `true` |
| SPRING_REDIS_HOST | Endereço de conexão com o Redis | `String` | `localhost` |
| SPRING_REDIS_PORT | Porta de conexão com o Redis | `Integer` | `6379` |
| SPRING_REDIS_PRODUCT_TIMEOUT | Tempo que o produto vai ficar em cache (em milissegundos) | `Integer` | `1800000` |
| SPRING_APPLICATION_ENV | Ambiente da aplicação (DEVELOPMENT, STAGING, PRODUCTION) | `enum` | `DEVELOPMENT` |
| SPRING_APPLICATION_CLIENT_PRODUCT_URL | URL da API de produtos | `String` | `http://challenge-api.luizalabs.com/api/product` |
| SPRING_APPLICATION_CLIENT_PRODUCT_TIMEOUT | Timeout da API de produtos | `Integer` | `5000` |
| SPRING_APPLICATION_JWT_SECRET_KEY | Token de autenticação | `UUID` | `11111111-2222-3333-4444-555555555555` |
| LOGGING_LEVEL_ROOT | Nível de log da aplicação | `enum` | `INFO` |

## Sobre

Este projeto foi estruturado com base nos princípios da Clean Architecture.

## Autor

Aplicação desenvolvida por Kenneth Gottschalk de Azevedo.
