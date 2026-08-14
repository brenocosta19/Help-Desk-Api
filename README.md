# HelpDesk API

API REST para gerenciamento de chamados de suporte técnico em empresas.

O projeto foi desenvolvido com **Java e Spring Boot**, com foco em arquitetura de APIs REST, regras de negócio, persistência de dados, autenticação e autorização utilizando **Spring Security + JWT**.

A aplicação foi projetada para atender uma única organização, permitindo o gerenciamento de usuários, tickets, técnicos e comentários, com diferentes níveis de acesso de acordo com o perfil do usuário.

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow?style=for-the-badge)
![License](https://img.shields.io/badge/license-MIT-informational?style=for-the-badge)

---

## 📋 Índice

- [Sobre o projeto](#-sobre-o-projeto)
- [Objetivos](#-objetivos)
- [Tecnologias](#-tecnologias)
- [Arquitetura](#-arquitetura)
- [Funcionalidades](#-funcionalidades)
- [Perfis de acesso](#-perfis-de-acesso)
- [Regras de negócio](#-regras-de-negócio)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação](#-instalação)
- [Configuração](#-configuração)
- [Autenticação](#-autenticação)
- [Endpoints](#-endpoints)
- [Documentação](#-documentação)
- [Estrutura do projeto](#-estrutura-do-projeto)
- [Próximos passos](#-próximos-passos)
- [Licença](#-licença)
- [Contato](#-contato)

---

## 📖 Sobre o projeto

O **HelpDesk API** é uma API REST desenvolvida para centralizar e gerenciar solicitações de suporte técnico dentro de uma organização.

A aplicação permite que clientes abram chamados, técnicos assumam tickets e realizem o atendimento, enquanto administradores possuem acesso e controle sobre todo o sistema.

Além do gerenciamento dos chamados, a API possui um sistema de autenticação e autorização baseado em **JWT e roles**, garantindo que cada usuário tenha acesso somente às operações permitidas para seu perfil.

O projeto também foi estruturado com separação entre **controllers, services, repositories, entities e DTOs**, mantendo as regras de negócio isoladas da camada HTTP.

---

## 🎯 Objetivos

O projeto tem como principais objetivos:

- Praticar desenvolvimento de APIs REST com Spring Boot
- Trabalhar com persistência utilizando JPA/Hibernate
- Implementar autenticação utilizando JWT
- Implementar autorização baseada em roles
- Aplicar regras de negócio na camada de serviço
- Trabalhar com DTOs e mapeamento utilizando MapStruct
- Utilizar Bean Validation para validação de entradas
- Trabalhar com relacionamentos entre entidades
- Implementar controle de acesso por usuário
- Documentar uma API utilizando OpenAPI/Swagger
- Estruturar uma aplicação próxima de cenários encontrados em projetos reais

---

# 🚀 Tecnologias

| Tecnologia | Utilização |
|---|---|
| **Java 21** | Linguagem principal |
| **Spring Boot 4** | Framework principal |
| **Spring Web MVC** | Construção da API REST |
| **Spring Data JPA** | Persistência |
| **Hibernate** | ORM |
| **Spring Security** | Autenticação e autorização |
| **JWT** | Autenticação stateless |
| **PostgreSQL** | Banco de dados |
| **MapStruct** | Mapeamento Entity ↔ DTO |
| **Bean Validation** | Validação dos dados |
| **Lombok** | Redução de boilerplate |
| **SpringDoc OpenAPI** | Documentação da API |
| **Maven** | Gerenciamento de dependências |

---

# 🏗️ Arquitetura

A aplicação utiliza uma arquitetura em camadas:

```text
                    HTTP Request
                         │
                         ▼
                  ┌─────────────┐
                  │ Controller  │
                  └──────┬──────┘
                         │
                    DTO / Validation
                         │
                         ▼
                  ┌─────────────┐
                  │   Service   │
                  └──────┬──────┘
                         │
                  Regras de negócio
                         │
                         ▼
                  ┌─────────────┐
                  │ Repository  │
                  └──────┬──────┘
                         │
                         ▼
                  ┌─────────────┐
                  │ PostgreSQL  │
                  └─────────────┘
```

O projeto utiliza **DTOs para entrada e saída da API**, evitando expor diretamente as entidades JPA.

O **MapStruct** é responsável pelo mapeamento entre entidades e DTOs.

---

# ✨ Funcionalidades

### Autenticação

- [x] Cadastro de usuários
- [x] Login
- [x] Autenticação utilizando JWT
- [x] Senhas armazenadas utilizando `PasswordEncoder`
- [x] Sessão stateless
- [x] Proteção de endpoints utilizando Spring Security

### Usuários

- [x] Cadastro de usuários
- [x] Perfis de acesso
- [x] Relacionamento `User ↔ Role`
- [ ] Gerenciamento completo de usuários
- [ ] Gerenciamento de roles
- [ ] Desativação de usuários

### Tickets

- [x] Criação de tickets
- [x] Consulta de tickets
- [x] Consulta detalhada
- [x] Atribuição de técnicos
- [x] Atribuição do ticket ao próprio técnico
- [x] Atualização de status
- [x] Controle de prioridade
- [x] Controle de acesso aos tickets

### Comentários

- [x] Criação de comentários
- [x] Associação do comentário ao ticket
- [x] Associação automática do autor através do usuário autenticado
- [x] Listagem de comentários
- [x] Controle de acesso aos comentários

### Documentação

- [x] OpenAPI / Swagger

---

# 👥 Perfis de acesso

A aplicação possui três perfis principais:

| Perfil | Descrição |
|---|---|
| **CLIENT** | Usuário que abre e acompanha seus próprios tickets |
| **TECHNICIAN** | Usuário responsável pelo atendimento dos tickets |
| **ADMIN** | Usuário com acesso administrativo ao sistema |

As roles são armazenadas utilizando a convenção:

```text
ROLE_CLIENT
ROLE_TECHNICIAN
ROLE_ADMIN
```

Um usuário pode possuir **mais de uma role**.

Exemplo:

```text
User
 ├── ROLE_ADMIN
 └── ROLE_TECHNICIAN
```

---

## Regras de Negócio

O desenvolvimento da API tem girado em torno das seguintes regras de negócios:

``` text
AUTH
├── Usuário deve estar autenticado para operações protegidas
├── JWT inválido/expirado → 401
└── Sem permissão → 403

USUÁRIOS
├── Cadastro público → CLIENT
├── ADMIN pode criar/gerenciar usuários privilegiados
├── ADMIN gerencia roles
├── Usuário não pode alterar suas próprias roles
├── E-mail único
├── Senha nunca retorna na API
└── Usuário desativado não pode autenticar

TICKETS
├── Apenas CLIENT pode abrir
├── Novo ticket começa OPEN
├── CLIENT não escolhe technician/status/client
├── ADMIN pode atribuir
├── TECHNICIAN pode atribuir a si mesmo
├── TECHNICIAN só pode atuar nos próprios tickets
├── Um ticket possui no máximo um técnico
├── Apenas TECHNICIAN pode ser atribuído
├── ADMIN vê todos
├── CLIENT vê os próprios
├── TECHNICIAN vê os atribuídos a ele
├── OPEN → IN_PROGRESS → CLOSED
├── IN_PROGRESS exige técnico
├── CLOSED exige IN_PROGRESS
├── CLIENT não fecha
└── Operações em CLOSED são limitadas

COMENTÁRIOS
├── Ticket deve estar OPEN/IN_PROGRESS
├── Usuário deve estar associado
├── Autor = usuário autenticado
├── CLIENT só comenta nos próprios tickets
├── TECHNICIAN só comenta nos tickets atribuídos
├── ADMIN comenta em qualquer ticket
├── Usuário só vê comentários que pode acessar
├── Conteúdo obrigatório
└── Limite de caracteres

PROFILE
├── Usuário vê seus dados
├── Usuário vê seus tickets resumidos
├── ADMIN vê todos os usuários
└── ADMIN pode visualizar tickets dos usuários

```

---


# 🔑 Autenticação

A API utiliza **Spring Security + JWT**.

Após realizar login, o cliente recebe um token que deve ser enviado nas requisições protegidas através do header:

```http
Authorization: Bearer <token>
```

Fluxo:

```text
┌──────────┐
│  Login   │
└────┬─────┘
     │
     ▼
AuthenticationManager
     │
     ▼
JWT
     │
     ▼
Authorization: Bearer <token>
     │
     ▼
JwtAuthenticationFilter
     │
     ▼
SecurityContext
     │
     ▼
Controller
```

As rotas públicas incluem:

```text
POST /v1/auth/register
POST /v1/auth/login
```

As demais rotas exigem autenticação.

---

# ⚙️ Pré-requisitos

Antes de executar o projeto, certifique-se de possuir:

- Java 21+
- Maven 3.9+
- PostgreSQL
- Git
- IDE de sua preferência

Opcional:

- Docker
- Docker Compose

---

# 📥 Instalação

Clone o repositório:

```bash
git clone https://github.com/seu-usuario/helpdesk-api.git
```

Entre no diretório:

```bash
cd helpdesk-api
```

Configure o banco de dados.

Depois execute:

### Linux / macOS

```bash
./mvnw spring-boot:run
```

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

A aplicação será iniciada em:

```text
http://localhost:8080
```

---

# 🔧 Configuração

Exemplo de configuração:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/helpdesk
    username: postgres
    password: postgres

  jpa:
    hibernate:
      ddl-auto: update

jwt:
  key: ${JWT_KEY}
  expiration: ${JWT_EXPIRATION}
```

## Variáveis de ambiente

```text
JWT_KEY=uma-chave-secreta-segura
JWT_EXPIRATION=900000
```

`JWT_EXPIRATION` é definido em milissegundos.

Para ambientes de produção, recomenda-se utilizar migrations com **Flyway ou Liquibase** em vez de depender de `ddl-auto: update`.

---

# 📚 Documentação da API

A API utiliza **OpenAPI/Swagger** para documentação e testes dos endpoints.

Após iniciar a aplicação, acesse:

```text
http://localhost:8080/swagger-ui.html
```

A documentação permite:

- Visualizar endpoints
- Consultar DTOs
- Visualizar parâmetros
- Testar requisições
- Consultar respostas
- Utilizar autenticação Bearer Token

---

# 📁 Estrutura do projeto

```text
helpdeskapi/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── brenocosta/
│   │   │           └── helpdeskapi/
│   │   │
│   │   │               ├── config/
│   │   │               ├── controllers/
│   │   │               ├── domain/
│   │   │               │   ├── entities/
│   │   │               │   └── enums/
│   │   │               ├── dtos/
│   │   │               │   ├── auth/
│   │   │               │   ├── comment/
│   │   │               │   ├── ticket/
│   │   │               │   └── user/
│   │   │               ├── mapper/
│   │   │               ├── repositories/
│   │   │               └── services/
│   │   │
│   │   └── resources/
│   │       └── application.yml
│   │
│   └── test/
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

# 🧪 Testes

O projeto possui como objetivo testar tanto comportamentos técnicos quanto regras de negócio.

Exemplos:

```text
✓ Cliente não acessa ticket de outro cliente
✓ Técnico não acessa ticket de outro técnico
✓ Admin acessa todos os tickets
✓ Ticket OPEN não pode ser fechado diretamente
✓ Ticket IN_PROGRESS exige técnico
✓ Cliente não pode fechar ticket
✓ Ticket CLOSED não aceita comentários
✓ Usuário não pode comentar em ticket não associado
✓ Apenas técnico pode ser atribuído
✓ Técnico não pode atribuir outro técnico
✓ Usuário não pode alterar suas próprias roles
```

---

# 🗺️ Roadmap

- [x] Estrutura inicial da API
- [x] Gerenciamento de tickets
- [x] Relacionamento entre usuários e tickets
- [x] Sistema de comentários
- [x] DTOs
- [x] MapStruct
- [x] Bean Validation
- [x] Spring Security
- [x] Autenticação JWT
- [x] Roles
- [ ] Permissions
- [ ] Gerenciamento completo de usuários
- [ ] Reabertura de tickets
- [ ] Tratamento global de exceções
- [ ] Testes unitários
- [ ] Testes de integração
- [ ] Migrations com Flyway
- [ ] Dockerização
- [ ] CI/CD
- [ ] Deploy

---

# 📌 Decisões de arquitetura

### DTOs em vez de entidades nas respostas

As entidades JPA não são expostas diretamente pelos controllers.

```text
Entity
   │
   ▼
MapStruct
   │
   ▼
Response DTO
```

Isso evita expor informações internas da entidade e permite controlar exatamente o formato da API.

### Usuário autenticado como proprietário da operação

Informações como o autor de um comentário não são recebidas pelo cliente:

```json
{
  "content": "Problema resolvido."
}
```

O backend obtém o usuário através do contexto de segurança.

Isso evita que um usuário envie:

```json
{
  "content": "Problema resolvido.",
  "ownerId": 1
}
```

tentando criar um comentário em nome de outra pessoa.

### Regras de negócio no Service

Os controllers são responsáveis por receber e devolver HTTP.

As decisões de negócio ficam na camada de serviço:

```text
Controller
   ↓
Service
   ↓
Repository
```

---

# 👨‍💻 Contato

**Breno Costa do Nascimento**

Email: `brenocostanascimento031@gmail.com`

---

⭐ Se este projeto foi útil ou interessante, considere deixar uma estrela no repositório.
