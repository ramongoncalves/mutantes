# Projeto Mutantes

Projeto Exame ML.

Verificação de Cadeias de DNA, para encontrar genes MUTANTES.


## Execução

Fazer o clone ou download e instalar jre 1.8.

### Prerequisitos

Java 1.8
Maven 3.X

### Instalação

Dentro da pasta do projeto, executar a seguinte linha de comando:
maven clean install

Após executar o seguinte comando:
mvn spring-boot:run

## Dependências

spring-boot-starter-data-jpa
spring-boot-starter-web
spring-boot-starter-test
spring-boot-starter-devtools
H2 (BANCO EM MEMÓRIA)
lombok

## Endpoints

### Mutant POST
http://ec2-18-228-7-159.sa-east-1.compute.amazonaws.com:8080/mutant

### Stats GET
http://ec2-18-228-7-159.sa-east-1.compute.amazonaws.com:8080/stats

