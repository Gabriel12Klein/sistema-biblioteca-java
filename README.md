# sistema-biblioteca-java
# Sistema de Gerenciamento de Biblioteca Acadêmica (Java)

Projeto desenvolvido para a disciplina de **Programação Orientada a Objetos (2025/2)**, com foco em modelagem de classes, uso de ArrayList e aplicação dos princípios de orientação a objetos.

## 📚 Cenário

Sistema para informatizar o processo de empréstimo de livros, cadastro de usuários e controle de acervo de uma biblioteca acadêmica.

O sistema permite registrar livros, alunos, empréstimos e devoluções, simulando o funcionamento de uma biblioteca real através de um menu interativo no console.

---

## 🚀 Funcionalidades

- Cadastro de usuários (alunos)
- Listagem de usuários
- Cadastro de livros
- Listagem de livros
- Realização de empréstimos
- Registro de devolução
- Consulta de histórico de empréstimos
- Consulta de livros disponíveis

---

## 🏗️ Estrutura do Sistema

### 📌 Classes Implementadas

- **Usuario (Aluno)**
  - Atributos: nome, matrícula, curso
  - Consulta de livros emprestados

- **Livro**
  - Atributos: título, autor, ano, código, status
  - Alteração de status (disponível/emprestado)

- **Acervo**
  - Armazena os livros utilizando `ArrayList<Livro>`
  - Permite adicionar, remover e buscar livros

- **Emprestimo**
  - Relação entre usuário e livro
  - Controle de data de empréstimo e devolução
  - Verificação de atraso

- **SistemaBiblioteca (classe principal)**
  - Menu interativo no console
  - Controle geral do sistema

---

## 🛠️ Tecnologias Utilizadas

- Java
- Programação Orientada a Objetos (POO)
- ArrayList
- Menu interativo via console

---

## ▶️ Como Executar

1. Clone ou baixe o repositório
2. Abra na sua IDE (NetBeans, IntelliJ, etc.)
3. Execute a classe principal `SistemaBiblioteca`
4. Utilize o menu no console

---

## 🎯 Objetivo Acadêmico

Aplicar conceitos de:
- Modelagem de classes
- Encapsulamento
- Associação entre objetos
- Estrutura de dados (ArrayList)
- Organização de código orientado a objetos

