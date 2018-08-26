# Projeto Mutantes

Projeto Exame ML.

Verificação de Cadeias de DNA, para encontrar genes MUTANTES.


## Execução

Fazer o clone ou download e instalar jre 1.8.

### Prerequisitos

Java 1.8<br/>
Maven 3.X

### Instalação

Dentro da pasta do projeto, executar a seguinte linha de comando:<br/>
maven clean install

Executar o seguinte comando:<br/>
mvn spring-boot:run

## Dependências

spring-boot-starter-data-jpa<br/>
spring-boot-starter-web<br/>
spring-boot-starter-test<br/>
spring-boot-starter-devtools<br/>
H2 (BANCO EM MEMÓRIA)<br/>
lombok

## Endpoints

### Mutant POST
http://mutants2-env.pcbeqv22ew.sa-east-1.elasticbeanstalk.com:8080/mutant

### Stats GET
http://mutants2-env.pcbeqv22ew.sa-east-1.elasticbeanstalk.com:8080/stats

