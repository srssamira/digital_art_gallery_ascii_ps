# Documentação Técnica: Digital Art Gallery

Este documento detalha a arquitetura, as tecnologias e as especificações
dos serviços de API REST do projeto **Digital Art Gallery**, um sistema
de gerenciamento para uma galeria de arte digital.

## 1. Visão Geral do Projeto {#visão-geral-do-projeto}

O projeto **Digital Art Gallery** é uma aplicação backend construída com
**Spring Boot 3.x** e **Java 21**, focada no gerenciamento de artistas e
suas respectivas obras de arte digitais.

| **Característica**       | **Detalhe**                                |
|--------------------------|--------------------------------------------|
| **Nome da Aplicação**    | digital_art_gallery                        |
| **Tecnologia Principal** | Spring Boot 3.x, Java 21                   |
| **Banco de Dados**       | PostgreSQL                                 |
| **Porta do Servidor**    | 8080                                       |
| **Documentação API**     | SpringDoc OpenAPI (Swagger UI)             |
| **Arquitetura**          | Clean Architecture / Arquitetura Hexagonal |

### 1.1 Configuração de Conexão (PostgreSQL) {#configuração-de-conexão-postgresql}

A aplicação está configurada para se conectar a uma instância local do
PostgreSQL.

spring:  
datasource:  
url: jdbc:postgresql://localhost:5432/db_gallery  
username: postgres  
password: root  
driver-class-name: org.postgresql.Driver  
jpa:  
hibernate:  
ddl-auto: update \# Atualiza o schema do DB automaticamente

## 2. Arquitetura do Sistema {#arquitetura-do-sistema}

O projeto adota uma **Arquitetura Limpa (Clean Architecture)**, também
conhecida como **Arquitetura Hexagonal (Ports and Adapters)**. Esta
abordagem visa isolar a lógica de domínio (negócio) das dependências
externas (banco de dados, frameworks, APIs).

A estrutura de pacotes é organizada por **Módulos** (artists, artworks)
e **Camadas** (verticalmente: app, domain, infra).

### 2.1 Estrutura de Camadas {#estrutura-de-camadas}

| **Camada** | **Pacotes Chave**                   | **Responsabilidade**                                                                                                                               |
|------------|-------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------|
| **app**    | dtos, ports/controllers             | Interface com o mundo externo. Recebe requisições HTTP (ArtistController) e lida com a entrada/saída de dados via DTOs.                            |
| **domain** | ports, usecases, exceptions         | O **Coração** do sistema. Contém a lógica de negócio pura, as interfaces de serviço (PortIn) e as regras, completamente desacoplada de frameworks. |
| **infra**  | adapters, entities, config, mappers | Implementação de detalhes técnicos. Lida com a persistência (JPA Entities, Adapters/Repositories) e configurações globais (Exception Handlers).    |

## 3. Módulo: Artistas (artists) {#módulo-artistas-artists}

Este módulo gerencia as informações dos artistas da galeria.

### 3.1 Entidades e DTOs {#entidades-e-dtos}

| **Nome**              | **Tipo**      | **Descrição**                                                                        |
|-----------------------|---------------|--------------------------------------------------------------------------------------|
| **ArtistEntity**      | JPA Entity    | Representação do artista no banco de dados. Mapeamento @OneToMany com ArtWorkEntity. |
| **ArtistCreateDTO**   | Entrada/Saída | Contém dados para criação ou atualização (Nome, Bio, Email, Redes Sociais).          |
| **ArtistResponseDTO** | Saída         | Inclui o id gerado e a lista de artworks (obras) associadas.                         |

### 3.2 Porta de Domínio (Caso de Uso) {#porta-de-domínio-caso-de-uso}

A interface de domínio define as operações de gerenciamento de artistas.

//
gallery.example.digital_art_gallery.artists.domain.ports.ArtistManagementPortIn  
public interface ArtistManagementPortIn {  
ArtistResponseDTO createArtist(ArtistCreateDTO newArtist);  
List\<ArtistResponseDTO\> getAllArtists();  
ArtistResponseDTO getArtistById(Long artistId);  
ArtistResponseDTO updateArtist(Long artistId, ArtistCreateDTO
updatedArtist);  
void deleteArtist(Long artistId);  
}

### 3.3 Endpoints da API {#endpoints-da-api}

| **Método** | **Endpoint**        | **Descrição**                         | **Status de Resposta**         |
|------------|---------------------|---------------------------------------|--------------------------------|
| POST       | /artists            | Cria um novo artista.                 | 201 Created                    |
| GET        | /artists            | Retorna a lista de todos os artistas. | 200 OK                         |
| GET        | /artists/{artistId} | Retorna um artista específico por ID. | 200 OK / 404 Not Found         |
| PUT        | /artists/{artistId} | Atualiza um artista existente.        | 200 OK / 404 Not Found         |
| DELETE     | /artists/{artistId} | Exclui um artista por ID.             | 204 No Content / 404 Not Found |

### 3.4 Tratamento de Exceções {#tratamento-de-exceções}

O ArtistGlobalExceptionHandler lida especificamente com:

- **404 Not Found**: Lançado via ArtistNotFoundException quando um
  > artista não é encontrado.

- **400 Bad Request**: Lançado via MethodArgumentNotValidException (para
  > erros de validação).

- **500 Internal Server Error**: Handler genérico.

## 4. Módulo: Obras de Arte (artworks) {#módulo-obras-de-arte-artworks}

Este módulo gerencia as obras de arte digitais, sempre vinculadas a um
artista.

### 4.1 Entidades e DTOs {#entidades-e-dtos-1}

| **Nome**               | **Tipo**   | **Descrição**                                                                                                 |
|------------------------|------------|---------------------------------------------------------------------------------------------------------------|
| **ArtWorkEntity**      | JPA Entity | Representação da obra. Mapeamento @ManyToOne com ArtistEntity (a coluna artist_id é obrigatória).             |
| **ArtWorkCreateDTO**   | Entrada    | Contém Título, Descrição, imageUrl e, **obrigatoriamente**, o artistId (com validações @NotNull e @Positive). |
| **ArtWorkResponseDTO** | Saída      | Inclui id, dados da obra e o artistName (nome do artista), para consumo amigável.                             |

### 4.2 Lógica de Negócio e Relacionamento {#lógica-de-negócio-e-relacionamento}

O serviço (ArtWorkService) implementa uma regra de negócio crucial:

- Para criar ou atualizar uma obra, ele utiliza o ArtistJpaAdapterOut
  > para verificar se o **artistId** fornecido existe. Se o artista não
  > for encontrado, a exceção ArtistNotFoundException é lançada.

### 4.3 Endpoints da API {#endpoints-da-api-1}

| **Método** | **Endpoint**          | **Descrição**                                                      | **Status de Resposta**         |
|------------|-----------------------|--------------------------------------------------------------------|--------------------------------|
| POST       | /artworks             | Cria uma nova obra, vinculando-a a um artistId.                    | 201 Created                    |
| GET        | /artworks             | Retorna a lista de todas as obras de arte.                         | 200 OK                         |
| GET        | /artworks/{artWorkId} | Retorna uma obra específica por ID.                                | 200 OK / 404 Not Found         |
| PUT        | /artworks/{artWorkId} | Atualiza uma obra existente, podendo mudar seu artista (artistId). | 200 OK / 404 Not Found         |
| DELETE     | /artworks/{artWorkId} | Exclui uma obra por ID.                                            | 204 No Content / 404 Not Found |

### 4.4 Tratamento de Exceções {#tratamento-de-exceções-1}

O ArtWorkGlobalExceptionHandler lida com:

- **404 Not Found**: Lançado via ArtWorkNotFoundException (obra não
  > encontrada) ou ArtistNotFoundException (artista de vínculo não
  > encontrado).

- **400 Bad Request**: Lançado via MethodArgumentNotValidException
  > (erros de validação, especialmente do artistId).

## 5. Dependências Chave (pom.xml) {#dependências-chave-pom.xml}

O projeto utiliza o Maven para gerenciamento de dependências.

| **Dependência**                     | **Versão** | **Descrição**                                                                            |
|-------------------------------------|------------|------------------------------------------------------------------------------------------|
| spring-boot-starter-web             | 3.5.6      | Criação de serviços RESTful.                                                             |
| spring-boot-starter-data-jpa        | 3.5.6      | Persistência de dados com JPA e Hibernate.                                               |
| spring-boot-starter-validation      | 3.5.6      | Validação de beans utilizando o padrão Jakarta Bean Validation.                          |
| postgresql                          | \-         | Driver de banco de dados PostgreSQL.                                                     |
| springdoc-openapi-starter-webmvc-ui | 2.8.13     | Geração automática de documentação da API no formato OpenAPI (Swagger UI).               |
| lombok                              | \-         | Geração automática de *getters*, *setters* e construtores (evitando código boilerplate). |

## 6. Guia de Início Rápido e Execução {#guia-de-início-rápido-e-execução}

Este guia fornece os passos necessários para configurar e executar a
aplicação localmente.

### 6.1 Pré-requisitos {#pré-requisitos}

- **Java Development Kit (JDK):** Versão 21 ou superior.

- **Maven:** Versão 3.x ou superior.

- **PostgreSQL:** Instância local rodando com um banco de dados criado:

    - **Nome do Banco de Dados:** db_gallery

    - **Usuário:** postgres

    - **Senha:** root *(Seus dados de conexão estão configurados em
      > application.properties)*

### 6.2 Configuração do Banco de Dados {#configuração-do-banco-de-dados}

Crie o banco de dados no seu servidor PostgreSQL:

CREATE DATABASE db_gallery;

O Hibernate (ddl-auto: update) se encarregará de criar automaticamente
as tabelas artist_entity e art_work_entity ao iniciar a aplicação.

### 6.3 Execução do Projeto {#execução-do-projeto}

1.  **Compilar e Empacotar (Build):** Navegue até o diretório raiz do
    > projeto (digital_art_gallery) e execute o comando Maven para
    > compilar e gerar o arquivo JAR executável:  
    > ./mvnw clean install

2.  **Executar a Aplicação:** Execute o arquivo JAR gerado na pasta
    > target/:  
    > java -jar target/digital_art_gallery-0.0.1-SNAPSHOT.jar

### 6.4 Acesso à API {#acesso-à-api}

Após a inicialização bem-sucedida, a API estará acessível em
http://localhost:8080.

- **Acesso à Documentação (Swagger UI):** A documentação interativa da
  > API, gerada pelo SpringDoc OpenAPI, estará disponível em:
  > http://localhost:8080/swagger-ui/index.html

- **Exemplo de Teste (Criação de Artista):** Para testar a criação de um
  > novo artista, envie uma requisição POST para
  > http://localhost:8080/artists com o seguinte corpo JSON:  
  > {  
  > \"name\": \"Evelyn Reed\",  
  > \"bio\": \"Artista digital especializada em arte generativa.\",  
  > \"email\": \"evelyn@example.com\",  
  > \"instagram\": \"@evelyn_art\",  
  > \"twitter\": \"@evelyn_tweets\",  
  > \"website\": \"\[http://evelynreed.com\](http://evelynreed.com)\"  
  > }

## 7. Plano de Evolução e Próximos Passos {#plano-de-evolução-e-próximos-passos}

A arquitetura atual é robusta (Clean Architecture), mas o projeto pode
ser expandido significativamente para atender a requisitos de mercado e
de segurança.

| **Prioridade** | **Funcionalidade**                        | **Descrição**                                                                                                                                                               | **Módulos Afetados**                      |
|----------------|-------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------|
| **Alta**       | **Autenticação e Autorização (Security)** | Implementar Spring Security para proteger endpoints, utilizando JWT ou OAuth2. Isso é essencial para diferenciar usuários (galeria) e artistas.                             | Todos, Infra (Config)                     |
| **Alta**       | **Paginação (Sorting & Filtering)**       | Adicionar suporte a paginação e filtros nas listagens (e.g., GET /artworks?page=0&size=10&sort=title).                                                                      | Artworks, Artists (Services, Controllers) |
| **Média**      | **Upload de Mídias (S3/Cloud Storage)**   | Substituir o campo imageUrl (que atualmente é apenas uma URL) por um serviço de upload de arquivos que armazene as imagens em serviços como AWS S3 ou Google Cloud Storage. | Artworks, Infra (Adapters)                |
| **Média**      | **Busca por Tags**                        | Adicionar um novo campo de tags (coleção) à entidade ArtWorkEntity e implementar endpoints de busca para pesquisa por tags.                                                 | Artworks (Domain, Infra)                  |
| **Baixa**      | **Sistema de Comentários**                | Criar um novo módulo (comments) para permitir que usuários comentem nas obras de arte.                                                                                      | Novo Módulo                               |
| **Baixa**      | **Internacionalização (i18n)**            | Suporte a múltiplas linguagens para as mensagens de erro e validação.                                                                                                       | Infra (Config)                            |
| **Baixa**      | **Testes Automatizados (Unit & Integration)** | Implementar uma suíte de testes abrangente utilizando JUnit e Mockito para garantir a qualidade do código.                                                                  | Todos                                     |
