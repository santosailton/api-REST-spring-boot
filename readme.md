# Exemplo de Aplicação com Padrões de Projeto e Arquitetura

Este é um exemplo de aplicação que utiliza padrões de projeto e arquitetura para melhorar a organização e manutenção do código, além de Exemplo de API REST com relacionamento entre tabelas utilizando Spring Data JPA.
A aplicação simula um sistema de cadastro de pessoas com seus endereços.

## Estrutura de arquivos do projeto
- [controller](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fcontroller)
    - [PessoaController.java](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fcontroller%2FPessoaController.java)
- [dto](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fdto)
  - [EditarEnderecoDTO.java](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fdto%2FEditarEnderecoDTO.java)
  - [EditarPessoaDTO.java](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fdto%2FEditarPessoaDTO.java)
  - [EnderecoDTO.java](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fdto%2FEnderecoDTO.java)
  - [PessoaDTO.java](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fdto%2FPessoaDTO.java)
- [mappers](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fmappers)
  - [EnderecoMapper.java](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fmappers%2FEnderecoMapper.java)
  - [PessoaMapper.java](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fmappers%2FPessoaMapper.java)
- [model](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fmodel)
  - [Endereco.java](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fmodel%2FEndereco.java)
  - [Pessoa.java](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fmodel%2FPessoa.java)
- [repositories](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Frepositories)
  - [EnderecoRepository.java](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Frepositories%2FEnderecoRepository.java)
  - [PessoaRepository.java](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Frepositories%2FPessoaRepository.java)
- [service](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fservice)
  - [PessoaService.java](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fservice%2FPessoaService.java)




## Padrões de Projeto Utilizados

### Data Mapper
O padrão Data Mapper foi utilizado para separar a lógica de mapeamento entre as entidades e os objetos DTO (Data Transfer Objects). As classes 
[EnderecoMapper](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fmappers%2FEnderecoMapper.java) e 
[PessoaMapper](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fmappers%2FPessoaMapper.java) atuam como mappers para converter objetos entre a camada de domínio (entidades) e a camada de transferência de dados (DTOs). Isso permite uma melhor separação de responsabilidades e flexibilidade na representação dos dados.

### DTO (Data Transfer Object)
Os DTOs foram utilizados para representar os dados transferidos entre as camadas da aplicação. As classes 
[EditarEnderecoDTO](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fdto%2FEditarEnderecoDTO.java),
[EditarPessoaDTO](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fdto%2FEditarPessoaDTO.java),
[EnderecoDTO](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fdto%2FEnderecoDTO.java) e
[PessoaDTO](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fdto%2FPessoaDTO.java)
são usadas para encapsular as informações relevantes de uma pessoa e um endereço, respectivamente. Isso permite que os dados sejam transferidos de forma eficiente e com uma estrutura adequada para cada contexto de uso.

## Arquitetura Utilizada

A aplicação segue uma arquitetura em camadas (ou layered architecture), que organiza o código em camadas distintas com responsabilidades específicas. A estrutura geral da arquitetura é a seguinte:

1. Camada de Apresentação (Presentation Layer): Responsável por receber as requisições do usuário e exibir os dados. Neste exemplo, a camada de apresentação é representada pela classe [PessoaController](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fcontroller%2FPessoaController.java).

2. Camada de Serviço (Service Layer): Responsável por conter a lógica de negócio da aplicação. Neste exemplo, a camada de serviço é representada pela classe [PessoaService](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fservice%2FPessoaService.java).

3. Camada de Persistência (Persistence Layer): Responsável por lidar com a persistência dos dados. Neste exemplo, a camada de persistência é representada pelas interfaces 
[EnderecoRepository](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Frepositories%2FEnderecoRepository.java) e 
[PessoaRepository](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Frepositories%2FPessoaRepository.java).

4. Camada de Domínio (Domain Layer): Contém as entidades e regras de negócio da aplicação. Neste exemplo, as entidades são representadas pelas classes 
[Endereco](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fmodel%2FEndereco.java) e
[Pessoa](pessoas%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fapi%2Fmodel%2FPessoa.java).

## Banco de Dados

A aplicação utiliza um banco de dados MySQL para persistência dos dados. Para facilitar o ambiente de desenvolvimento e testes, a aplicação e o banco de dados podem ser executados em contêineres separados usando o Docker.
As tabelas e relacionamentos são feitos pela própria API Spring Data JPA por conta da configuração `spring.jpa.hibernate.ddl-auto=create` no arquivo [application.properties](pessoas%2Fsrc%2Fmain%2Fresources%2Fapplication.properties).

## Executando a Aplicação

Para executar a aplicação junto com o banco de dados em contêineres separados, siga os passos abaixo:

1. Caso não houver o diretório target no projeto, realize a construção da aplicação com a instrução:
- `mvn package -DskipTests`

2. Certifique-se de ter o Docker instalados em sua máquina.
3. Clone o repositório para sua máquina local.
4. Abra o terminal na raiz do projeto.
5. Execute os comandos abaixo para criar e iniciar os conteiners em segundo plano.

- `docker network create minha-rede`


- `docker run -d --name meu-banco --network minha-rede -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=users mysql`


- `docker build -t minha-aplicacao .`


- `docker run -d --name minha-aplicacao -p 8080:8080 --network minha-rede minha-aplicacao`

6. Aguarde até que os contêineres sejam iniciados corretamente.
7. Acesse a aplicação em `http://localhost:8080`.
8. Utilize uma ferramenta como o Postman para fazer requisições para a aplicação e testar as funcionalidades.

## Serviços disponíveis na aplicação

1. Adicionar pessoa com ou sem endereços:
```
POST /pessoas
```
#### Estrutura de requisição aceita:
```json
{
    "nome" : "nome pessoa",
    "dataNascimento" : "01/01/2000",
    "endereco" : [
            {
            "logradouro": "nome da rua",
            "cep": "1234567-8",
            "numero": "100",
            "cidade" :"São Paulo"
        }
    ]
}
```

2. Listar pessoas com ou sem endereços:
```
GET /pessoas
```
#### Estrutura de resposta:
```json
[
  {
    "nome": "nome pessoa",
    "dataNascimento": "01/01/2000",
    "endereco": [
      {
        "cep": "1234567-8",
        "cidade": "São Paulo",
        "logradouro": "nome da rua",
        "numero": "100",
        "principal": null
      }
    ]
  }
]
```

3. Buscar pessoa por `idPessoa` com ou sem endereços:
```
GET /pessoas/{idPessoa}
```
#### Estrutura de resposta:
```json
{
  "nome": "nome pessoa",
  "dataNascimento": "01/01/2000",
  "endereco": [
    {
      "cep": "1234567-8",
      "cidade": "São Paulo",
      "logradouro": "nome da rua",
      "numero": "100",
      "principal": null
    }
  ]
}
```

4. Adicionar endereço à uma pessoa com ou sem endereços:
```
POST /pessoas/{idPessoa}/endereco
```
#### Estrutura de requisição aceita:
```json
{
    "logradouro": "novo endereço",
    "cep": "1234567-8",
    "numero": "200",
    "cidade": "São Paulo"
}
```

5. Lista de endereços por `idPessoa`:
```
GET /pessoas/{idPessoa}/enderecos
```
#### Estrutura de resposta:
```json
[
  {
    "cep": "1234567-8",
    "cidade": "São Paulo",
    "logradouro": "nome da rua",
    "numero": "100",
    "principal": null
  },
  {
    "cep": "1234567-8",
    "cidade": "São Paulo",
    "logradouro": "novo endereço",
    "numero": "200",
    "principal": null
  }
]
```
6. Editar pessoa por `idPessoa`:
```
PATCH /pessoas/{idPessoa}/endereco
```
#### Estrutura de requisição aceita:
```json
{
  "nome": "novo nome",
  "dataNascimento": "01/001/2000"
}
```
6. Editar endereço por `idPessoa` e `idEndereco`:
```
PUT /pessoas/{idPessoa}/{idEndereco}
```
#### Estrutura de requisição aceita:
```json
{
  "logradouro": "novo endereço editado",
  "cep": "1234567-8",
  "numero": "200",
  "cidade": "São Paulo"
}
```
7. Definir endereço principal por `idPessoa` e `idEndereco`:
```
PATCH /pessoas/{idPessoa}/{idEndereco}
```
#### Estrutura de resposta da requisição:
```json
{
  "id": 1,
  "logradouro": "nome da rua",
  "cep": "1234567-8",
  "numero": "100",
  "cidade": "São Paulo",
  "principal": "P"
}
```

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE.txt).