# CashCardController

O `CashCardController` é uma classe Java que faz parte de um projeto de aprendizado utilizando o framework Spring. Esta classe é responsável por gerenciar operações relacionadas a cartões de dinheiro (CashCards) em uma aplicação web. 

## Funcionalidades

- **Buscar Cartão por ID**: Permite que um usuário recupere um cartão de dinheiro específico através de seu ID.
- **Criar Cartão**: Permite que um usuário crie um novo cartão de dinheiro, associando-o ao seu nome de usuário.
- **Listar Todos os Cartões**: Retorna uma lista paginada de todos os cartões de dinheiro pertencentes ao usuário autenticado.
- **Atualizar Cartão**: Permite que um usuário atualize as informações de um cartão de dinheiro existente.
- **Deletar Cartão**: Permite que um usuário exclua um cartão de dinheiro, desde que ele seja o proprietário.

## Estrutura

A classe utiliza anotações do Spring para definir endpoints RESTful e gerenciar as requisições HTTP. As operações são realizadas em um repositório de cartões de dinheiro (`CashCardRepository`), que interage com a base de dados.

Este projeto é uma excelente oportunidade para aprender sobre desenvolvimento de APIs RESTful, gerenciamento de dados e autenticação de usuários em aplicações Java com Spring e faz parte do SPRING ACADEMY.
