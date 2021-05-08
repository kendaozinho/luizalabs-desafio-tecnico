# Desafio Técnico - Luizalabs
Customer API.

## Pré-requisitos
* Java 11
* Docker

## Variáveis de ambiente

| Nome | Descrição | Tipo | Valor Padrão |
|------|:---------:|:----:|-------------:|
| SPRING_DATASOURCE_HOST | Endereço de conexão com o banco de dados | `String` | `localhost` |
| SPRING_DATASOURCE_PORT | Porta de conexão com o banco de dados | `Integer` | `5432` |
| SPRING_DATASOURCE_DATABASE | Nome do banco de dados | `String` | `customer` |
| SPRING_DATASOURCE_USERNAME | Usuário do banco de dados | `String` | `usr_customer` |
| SPRING_DATASOURCE_PASSWORD | Senha do banco de dados | `String` | `12345` |
| SPRING_DATASOURCE_DRIVER_CLASS_NAME | Nome do driver de conexão com o banco de dados | `String` | `org.postgresql.Driver` |
| SPRING_FLYWAY_ENABLED | Habilitar o flyway | `Boolean` | `true` |
| SPRING_DATASOURCE_HIKARI_MAXIMUM_POOL_SIZE | Quantidade máxima de pools de conexão | `Integer` | `10` |
| SPRING_REDIS_HOST | Endereço de conexão com o Redis | `String` | `localhost` |
| SPRING_REDIS_PORT | Porta de conexão com o Redis | `Integer` | `6379` |
| JWT_SECRET_KEY | Token de autenticação | `UUID` | `11111111-2222-3333-4444-555555555555` |

## Instalação
Antes de rodar o projeto na máquina local é necessário instalar as dependências externas (no caso, o banco de dados).

Para isso é aconselhável a utilização do Docker, já que ele faz tudo automaticamente através do comando:

```sh
docker compose up -d
```

Será criado um banco de dados limpo na sua máquina local que será populado após a primeira vez que executar o projeto.

## Inicialização

```sh
mvn spring-boot:run
```

## Documentação

O Swagger da API se encontra na seguinte URL:

```sh
http://localhost:8080/swagger-ui/
```

## Sobre

Aplicação desenvolvida por Kenneth Gottschalk de Azevedo.
