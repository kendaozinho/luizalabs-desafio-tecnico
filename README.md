<h1 align="center">Desafio Técnico - Luizalabs</h1>
<p align="center">API de gerenciamento dos produtos favoritos de um cliente.</p>

<div align="center">
<table>
<tr>
<td>

[![Build Status](https://circleci.com/gh/kendaozinho/luizalabs-desafio-tecnico.svg?style=svg)](https://circleci.com/gh/kendaozinho/luizalabs-desafio-tecnico/?branch=master)

</td>
<td>

[![Coverage Status](https://coveralls.io/repos/github/kendaozinho/luizalabs-desafio-tecnico/badge.svg?branch=master)](https://coveralls.io/github/kendaozinho/luizalabs-desafio-tecnico?branch=master)

</td>
</tr>
</table>
</div>

## Pré-requisitos

* Java 11
* Docker

## Instalação

Antes da inicialização do projeto, é necessário instalar algumas dependências externas:

* __Redis__ - Banco de dados utilizado para cachear os produtos;
* __PostgreSQL__ - Banco de dados utilizado para armazenar os dados dos clientes e a relação deles com os produtos;

Para isto, é aconselhável a utilização do `Docker` para baixá-las:

```sh
docker-compose up -d
```

Desta forma, os bancos serão criados e populados pelo `Flyway` logo após a primeira execução do projeto.

Com o Java 11 instalado, basta executar o comando abaixo para baixar as dependências internas e compilar projeto:

```sh
./mvnw clean install -DskipTests
```

## Inicialização

Para inicializar a API basta executar o seguinte comando:

```sh
./mvnw spring-boot:run
```

## Testes

Os testes foram implementados de forma a validar os fluxos como um todo.

A aplicação possui o plugin `Jacoco`, que é responsável por verificar a cobertura de testes da aplicação:

```sh
./mvnw clean test
```

O resultado pode ser encontrado dentro do arquivo `/target/jacoco/index.html`.

Este projeto possui CI/CD integrados com o `CircleCI` e o `Heroku`.

## Documentação

Esta API possui dois endpoints:

* __/v1/customers__ - Obter, cadastrar, atualizar e remover clientes;
* __/v1/customers/products__ - Obter, cadastrar e remover os produtos favoritos de um cliente;

Por possuir o `Actuator`, o projeto também possui a rota `/actuator/health` como Health Check.

As rotas fazem a autenticação via JWT com base na chave informada na variável de ambiente.

No ambiente de desenvolvimento, o Swagger exibe uma chave de autenticação válida em sua URL:

```sh
http://localhost:8080/swagger-ui/ ou https://luizalabs-customer-api.herokuapp.com/swagger-ui/
```

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
