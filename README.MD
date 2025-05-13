

# API RESTful Simples com Spring Boot

## 📖 Visão Geral

Este projeto é uma **API RESTful** desenvolvida com **Spring Boot**, projetada para exemplificar a criação de uma API básica com operações **CRUD** (Criar, Ler, Atualizar, Excluir). O objetivo é seguir boas práticas de desenvolvimento, garantindo escalabilidade, manutenibilidade e simplicidade.

---

## ✨ Funcionalidades

- 🌐 **Design de API RESTful**: Estrutura clara e organizada para comunicação entre cliente e servidor.
- 🛠️ **Operações CRUD**: Gerenciamento completo de recursos.
- 🗄️ **Integração com Banco de Dados Relacional**: Utilização do **PostgreSQL** para persistência de dados.
- ✅ **Validação e Tratamento de Exceções**: Garantia de dados consistentes e respostas adequadas.
- 🔄 **Injeção de Dependência**: Implementada com o **Spring Framework**.
- 🚀 **Configuração e Implantação Simplificadas**: Pronto para rodar com poucos passos.

---

## 🛠️ Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **Spring Boot**: Framework para desenvolvimento rápido e eficiente de aplicações Java.
- **PostgreSQL**: Banco de dados relacional para persistência.
- **Maven**: Gerenciador de dependências e build.

---

## 🧩 Design Patterns Utilizados

- **Repository Pattern**: Para abstração da camada de persistência.
- **Service Layer Pattern**: Para encapsular a lógica de negócios.
- **DTO (Data Transfer Object)**: Para transferência de dados entre camadas.
- **Singleton**: Para gerenciar instâncias únicas de componentes.
- **Factory Method**: Para criação de objetos de forma flexível.
- **Result Pattern**: Para encapsular resultados e erros de forma consistente.
- **Builder Pattern**: Para construção de objetos complexos de forma fluida.
---

## 📂 Estrutura do Projeto

- **`src/main/java`**: Contém o código-fonte da aplicação.
- **`src/main/resources`**: Arquivos de configuração, como `application.properties`.
- **`test`**: Testes unitários e de integração (em desenvolvimento).

---

## 🚀 Como Usar

### Pré-requisitos

1. **PostgreSQL** instalado e configurado.
2. **Java 21+** instalado.
3. **Maven** configurado no ambiente.

## 🐳 Executando com Docker

### Pré-requisitos

1. **Docker** e **Docker Compose** instalados no ambiente.

### Passos para Execução

1. Clone o repositório:
    ```git
        git clone https://github.com/seu-usuario/seu-repositorio.git
        cd seu-repositorio
    ```
```bash
    docker-compose up --build
   ```
   Acesse a API em: http://localhost:8080.


   **Para parar os containers:**
```bash
    docker-compose down
```


2. Configure o banco de dados no arquivo `application.properties`:
```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
  ```

   3. Execute o projeto:
   ```bash
         mvn spring-boot:run
  ```

4. Acesse a API em: [http://localhost:8080](http://localhost:8080).

   ---

   ## 📦 Como Importar

   1. Abra sua IDE (IntelliJ IDEA ou outra de sua preferência).
   2. Selecione a opção **Import Project**.
   3. Escolha o arquivo `pom.xml` na raiz do projeto.
   4. Aguarde o Maven resolver as dependências.
   5. Execute a aplicação diretamente pela IDE.

   ---

   ## 📌 Próximos Passos

   - 🔒 **Melhorias no Sistema de Autenticação e Autorização**.
     - 🧪 **Implementação de Testes Automatizados**.
     - ⚡ **Otimizações de Desempenho e Escalabilidade**.
   ---

   ## 🛡️ Contribuição

   Desenvolvido com ❤️ por Henrique --> [Github](https://github.com/egotting).
```