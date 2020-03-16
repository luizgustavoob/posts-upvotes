# Posts compartilhados

Este repositório contém os projetos utilizados na criação de uma aplicação que permite a postagem de textos livres, sendo que estes textos podem receber *upvotes*.

## Tecnologias
* [Spring Boot](https://spring.io/projects/spring-boot)
* [String Data JPA](https://spring.io/projects/spring-data-jpa)
* [Maven](http://maven.apache.org/)
* [H2 Database Engine](http://www.h2database.com/html/main.html)
* [Lombok](https://projectlombok.org/)
* [JUnit](https://junit.org)
* [Angular 9](https://angular.io/)
* [Ng Bootstrap](https://ng-bootstrap.github.io/#/home)
* [Bootstrap](https://getbootstrap.com/)
* [Angular Font Awesome](https://fontawesome.com/how-to-use/on-the-web/using-with/angular)

## Execução

Primeiramente, deve-se baixar o repositório em algum diretório físico do computador.

### Pré-requisitos

* [Node](https://nodejs.org/en/)
* [Angular CLI](https://cli.angular.io/)
* [Maven](https://maven.apache.org/)

### Execução - API

Com o diretório disponível no computador, deve-se acessar a pasta *posts-upvotes* pelo terminal e digitar o comando
```
  mvn clean package
```
para que o arquivo **.jar** da API seja gerado. Na sequência, digitar o comando
```
  java -jar target\posts-upvotes-0.0.1-SNAPSHOT.jar
```
e a aplicação back-end será iniciada.

### Execução - Cliente

Através de outro terminal, deve-se acessar a pasta *post-upvotes-front* e inicialmente digitar o comando
```
  npm install
```
para que as dependências necessárias ao projeto sejam baixadas. Após isso, digitar o comando 
```
  ng serve --open
```
e a aplicação cliente será aberta no navegador.
