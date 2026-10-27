# Gerenciador de Contatos JDBC (Java + MySQL)

Aplicação desenvolvida em **Java** via terminal com persistência de dados em base de dados relacional **MySQL**, construída com foco em práticas de programação Back-end.

## 🚀 Tecnologias Utilizadas
* **Java** (JDK 25 / Hospedado no VS Code)
* **JDBC** (Java Database Connectivity) para integração com SGBD
* **MySQL** (WampServer)
* **MySQL Connector/J** (`.jar`)

## 📋 Funcionalidades
* **Cadastrar Contato:** Insere novos registos (Nome e Telefone) diretamente na base de dados relacional.
* **Listar Contatos:** Consulta e exibe no terminal todos os contactos guardados de forma formatada.
* **Menu Interativo:** Execução contínua via terminal (`while` e `Scanner`) com tratamento de exceções.

## 🗄️ Estrutura da Base de Dados
Para executar o projeto, crie a base de dados executando o seguinte script SQL:

```sql
CREATE DATABASE agenda;
USE agenda;

CREATE TABLE contatos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    telefone VARCHAR(20)
);