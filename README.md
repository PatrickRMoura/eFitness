# e-Fitness

E-fitness trata-se de um sistema capaz de gerenciar academias, possuindo módulos de controle de matrículas
de alunos e registro de evolução dos alunos.

## Tecnologias Utilizadas:

Linguagem: JAVA

Arquitetura: MVC(Model View Controller)

Banco de dados: [PostgreSQL](https://www.postgresql.org/download/)

Controle de versionamento/plataforma de hospedagem: Git e GitHub


## Clonando o projeto
`git clone https://github.com/PatrickRMoura/eFitness.git`

## HTTPS
https://github.com/PatrickRMoura/eFitness.git

## SSH
git@github.com:PatrickRMoura/eFitness.git

## Preparação do ambiente e do projeto

1. Instalar [PostgreSQL](https://www.postgresql.org/download/) e fazer download do driver [PostgresJDBC (https://jdbc.postgresql.org/)
2. Nas Bibliotecas do projeto, adicionar o arquivo .jar baixado
3. Construir o projeto e executar o script de geração do BD

## Breve descrição das funções do sistema
 
* Registrar Usuário
  * Será possível o cadastro de um usuário para possibilitar o acesso ao sistema
 
* Realizar Login
  * O usuário informa usuário e senha e realiza login para entrar no sistema
 
* Registrar Exercício
  * Deve ser possível cadastrar um exercício que posteriormente será vinculado a treinos.
 
* Registrar Aluno
  * Deve ser possível cadastrar um aluno.
 
* Registrar Avaliação do Aluno
  * Ao entrar no cadastro do aluno, haverá um botão "Avaliações", onde poderá ser cadastrada uma avaliação para o aluno.
 
* Registrar Matrícula do Aluno
  * Ao entrar no cadastro de aluno, haverá um botão chamado "Matrícula", onde será possível cadastrar a matrícula do aluno.
 
* Registrar Restrição do Aluno
  * Ao entrar no cadastro de aluno, haverá um botão chamado "Restrições", onde será possível cadastrar os cuidados especiais do aluno.

* Registrar Treino do Aluno
  * Ao entrar no cadastro de aluno, haverá um botão "Treinos", onde será possível cadastrar um treino para o aluno.
