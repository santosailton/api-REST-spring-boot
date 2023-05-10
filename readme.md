# API REST com spring boot
Exemplo de API REST com relacionamento entre tabelas com JPA.

### Entidades MySQL
O sript SQL abaixo pode ser usado para criação das entidades no banco caso não estiver habilitado a 
propriedade em `spring.jpa.hibernate.ddl-auto=create` no arquivo [application.properties](pessoas%2Fsrc%2Fmain%2Fresources%2Fapplication.properties) do projeto.

```roomsql
CREATE TABLE PESSOAS(
    ID bigint auto_increment PRIMARY KEY,
    NOME VARCHAR(255),
    DATA_NASCIMENTO VARCHAR(255)
);
```

```roomsql
CREATE TABLE ENDERECOS(
    ID bigint auto_increment PRIMARY KEY,
    ID_PESSOA bigint,
    LOGRADOURO VARCHAR(255),
    CEP VARCHAR(255),
    CIDADE VARCHAR(255),
    NUMERO VARCHAR(255),
    PRINCIPAL VARCHAR(1)
    foreign key (ID_PESSOA) references PESSOAS(id)
);
```
## Build .jar

`mvn package -DskipTests`

## Container
Comandos que podem ser usados para dockerizar a aplicação, dividindo em dois conteiners que comunicam entre si através da docker network.


`docker network create minha-rede`

`docker run -d --name meu-banco --network minha-rede -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=users mysql`

`docker build -t minha-aplicacao .`

`docker run -d --name minha-aplicacao -p 8080:8080 --network minha-rede minha-aplicacao`
