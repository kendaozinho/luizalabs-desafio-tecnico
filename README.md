# Desafio Técnico - Luizalabs
Customer API.

## Pré-requisitos
* Java 11
* Docker

## Variáveis de ambiente

| Nome | Descrição | Valor Padrão |
|------|:---------:|-------------:|
| SPRING_DATASOURCE_HOST | Endereço de conexão com o banco de dados | `localhost` |
| SPRING_DATASOURCE_PORT | Porta de conexão com o banco de dados | `5432` |
| SPRING_DATASOURCE_DATABASE | Nome do banco de dados | `customer` |
| SPRING_DATASOURCE_USERNAME | Usuário do banco de dados | `usr_customer` |
| SPRING_DATASOURCE_PASSWORD | Senha do banco de dados | `12345` |
| SPRING_DATASOURCE_DRIVER_CLASS_NAME | Nome do driver de conexão com o banco de dados | `org.postgresql.Driver` |
| SPRING_FLYWAY_ENABLED | Habilitar o flyway | `true` |
| SPRING_DATASOURCE_HIKARI_MAXIMUM_POOL_SIZE | Quantidade máxima de pools de conexão | `10` |

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

## Sobre

Aplicação desenvolvida por Kenneth Gottschalk de Azevedo.
