<h1 align="center">Desafio Técnico - Luizalabs</h1>
<p align="center">API de gerenciamento dos produtos favoritos de um cliente.</p>

## Pré-requisitos

* Java 11
* Docker

## Instalação

Antes da inicialização do projeto é necessário a instalação de suas dependências.

De dependências externas, a aplicação utiliza dois bancos de dados diferentes:

* Redis - Realizar o cache dos produtos);
* PostgreSQL - Armazenar os dados dos clientes e a relação deles com os produtos);

É aconselhável a utilização do Docker para baixar estas dependências na máquina local.

Isto pode ser feito através do comando:

```sh
docker-compose up -d
```

Com isso, os bancos de dados serão criados na máquina local e serão populados pelo Flyway após a primeira execução do projeto.

Com o Java 11 instalado, basta executar o comando abaixo para baixar as dependências do projeto e rodar os testes da aplicação:

```sh
./mvnw clean install
```

## Inicialização

Para inicializar a API basta executar o comando abaixo:

```sh
./mvnw spring-boot:run
```

## Documentação

A API possui dois endpoints:

* __/v1/customers__ - Obter, cadastrar, atualizar e remover clientes;
* __/v1/customers/products__ - Obter, cadastrar e remover os produtos favoritos de um cliente;

As rotas fazem a autenticação via JWT com base na chave cadastrada na variável de ambiente.

No ambiente de desenvolvimento, o Swagger exibe uma chave de autenticação válida em sua URL:

```sh
http://localhost:8080/swagger-ui/
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
| SPRING_FLYWAY_ENABLED | Habilitar o flyway | `Boolean` | `true` |
| SPRING_REDIS_HOST | Endereço de conexão com o Redis | `String` | `localhost` |
| SPRING_REDIS_PORT | Porta de conexão com o Redis | `Integer` | `6379` |
| SPRING_REDIS_PRODUCT_TIMEOUT | Tempo que o produto vai ficar em cache (em milissegundos) | `Integer` | `1800000` |
| SPRING_APPLICATION_ENV | Ambiente da aplicação (DEVELOPMENT, STAGING, PRODUCTION) | `enum` | `DEVELOPMENT` |
| SPRING_APPLICATION_CLIENT_PRODUCT_URL | URL da API de produtos | `String` | `http://challenge-api.luizalabs.com/api/product` |
| SPRING_APPLICATION_CLIENT_PRODUCT_TIMEOUT | Timeout da API de produtos | `Integer` | `5000` |
| SPRING_APPLICATION_JWT_SECRET_KEY | Token de autenticação | `UUID` | `11111111-2222-3333-4444-555555555555` |
| LOGGING_LEVEL_ROOT | Nível de log da aplicação | `enum` | `INFO` |

## Estrutura do projeto

Esta API foi desenvolvida usando como base o Clean Architecture, separando as responsabilidades em camadas:

* Domínio - Camada principal, onde possui as entidades e as interfaces do que deve ser implementado;
* Aplicação - Camada que possui as regras internas do projeto. As classes dessa camada implementam os Interactors e recebem gateways/interactors como parâmetros;
* Infraestrutura - Camada onde se encontra os serviços externos que implementam os gateways (APIs externas, bancos de dados etc).
* Entrypoint - Camada onde é feita a inicialização do projeto (por APIs, workers, cron jobs etc), fazendo a comunicação com os Interactors;

## Sobre

Aplicação desenvolvida por Kenneth Gottschalk de Azevedo.
