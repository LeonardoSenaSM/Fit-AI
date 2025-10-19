# FitAI

## 📝 Descrição

FitAI é uma aplicação Spring Boot projetada para ajudar os usuários a gerenciar seus alimentos e criar refeições saudáveis. A aplicação permite cadastrar os alimentos que você possui e, utilizando a inteligência artificial da OpenAI (GPT-4o), gera um cardápio para a semana focado em receitas saudáveis, de baixa caloria e que aproveitam os ingredientes próximos da data de validade.

## ✨ Funcionalidades

* **Gerenciamento de Alimentos**: Funcionalidades CRUD (Criar, Ler, Atualizar, Deletar) completas para seus itens alimentícios.
* **Geração de Receitas com IA**: Integração com a API da OpenAI para gerar receitas criativas e saudáveis com base nos alimentos disponíveis.
* **Otimização de Ingredientes**: A IA é instruída a priorizar o uso de alimentos que estão perto de vencer, ajudando a reduzir o desperdício.
* **Foco em Saúde**: As receitas geradas são focadas em serem simples e de baixo valor calórico, ideais para uma dieta equilibrada.
* **Banco de Dados com Migrations**: Utiliza Flyway para gerenciar as versões do banco de dados de forma automática e segura.

## 🛠️ Tecnologias Utilizadas

* **Backend**:
    * [Java 17](https://www.oracle.com/java/)
    * [Spring Boot 3.5.5](https://spring.io/projects/spring-boot)
    * [Spring Web & WebFlux](https://docs.spring.io/spring-framework/reference/web.html)
    * [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* **Banco de Dados**:
    * [H2 Database](https://www.h2database.com/html/main.html)
    * [Flyway](https://flywaydb.org/)
* **Build e Dependências**:
    * [Apache Maven](https://maven.apache.org/)
    * [Lombok](https://projectlombok.org/)
* **Inteligência Artificial**:
    * [OpenAI API (GPT-4o)](https://openai.com/api/)

## 🚀 Como Executar o Projeto

### Pré-requisitos

* JDK 17 ou superior
* Apache Maven
* Uma chave de API da OpenAI

### Configuração

1.  **Clone o repositório:**
    ```bash
    git clone <URL-DO-SEU-REPOSITORIO>
    cd FitAI
    ```

2.  **Configure as variáveis de ambiente:**
    A aplicação utiliza variáveis de ambiente para configurar a conexão com o banco de dados e a API da OpenAI. Crie um arquivo `.env` na raiz do projeto (este arquivo está no `.gitignore` por segurança) com o seguinte conteúdo:

    ```env
    #CONFIG DATABASE
    DATABASE_URL=jdbc:h2:mem:fitai
    DATABASE_USERNAME=sa
    DATABASE_PASSWORD=password

    #API GPT
    API_KEY=SUA_CHAVE_DE_API_DA_OPENAI_AQUI
    ```

### Execução

Utilize o Maven para iniciar a aplicação:

```bash
./mvnw spring-boot:run
