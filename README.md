# 📞 Lista Telefônica

Este projeto consiste em uma API REST para armazenar contatos de uma lista telefônica.

## 🚀 Tecnologias Utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- Banco de Dados H2 (para testes)
- Maven

## 📌 Funcionalidades
- Criar um novo contato
- Buscar contatos
- Atualizar informações de um contato
- Deletar um contato

## 📄 Estrutura da Entidade Contato
A entidade **Contato** possui os seguintes atributos:

- `id` (Long) - Identificador único do contato
- `nome` (String) - Nome do contato
- `telefone` (String) - Número de telefone
- `email` (String) - Endereço de e-mail

## 🔧 Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/paulo-fabiano/java-lista-telefonica.git
   ```
2. Acesse o diretório do projeto:
   ```bash
   cd java-lista-telefonica
   ```
3. Compile e execute o projeto usando Maven:
   ```bash
   mvn spring-boot:run
   ```

## 🔍 Testando a API
A API pode ser testada via **Postman**, **Insomnia** ou utilizando `curl` no terminal.

Exemplo de requisição para adicionar um contato:
```bash
curl -X POST "http://localhost:8080/contatos" \
     -H "Content-Type: application/json" \
     -d '{"nome": "Paulo Fabiano", "telefone": "(11) 99999-8888", "email": "paulo@email.com"}'
```

Exemplo de requisição para listar os contatos:

```bash
curl "http://localhost:8080/contato/listar

```
---
Desenvolvido por **Paulo Fabiano De Oliveira Filho** 🚀