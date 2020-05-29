Este repositório comporta o trabalho final de Francisco Israel dos Santos Cruz da disciplina de Desenvolvimento Web da Especialização em Engenharia de Software com Ênfase em DevOps (turma 2). 
A especificação do trabalho segue:

O trabalho 2 será representado pela implementação de uma aplicação que deverá atender aos seguintes pontos:

- Permitir o cadastro de filmes
- Permitir que pessoas avaliem os filmes
- Listar os filmes com a média de avaliações

A entidade filme deve ter um título, sinopse, ano de lançamento, nome do(s) produtor(es), nome do(s) protagonista(s).

Deverá ser implementado a avaliação de cada filme em uma entidade relacionada com a entidade filmes, onde esta entidade deverá conter o nome da pessoa que realizou a avaliação do filme, um comentário opcional e a nota de avaliação do filme, que pode variar de 0 até 5.

O valor médio das avaliações dos filmes deve ser um valor calculado, e este valor não poderá ser armazenado no banco de dados.

O projeto deverá ser implementado utilizando as seguintes tecnologias abordadas em aula:

- Spring Boot
- Spring Data JPA
- PostgreSQL

-----------------------------------------Componentes do trabalho--------------------------------------------------------------------

O diretório amazingmovies-api é onde se encontra o projeto back-end;

O direttório amazingmovies-ui é onde se encontra o projeto front-end;

-------------------------------Etapas para a execução do trabalho (com docker)------------------------------------------------------

-Entrar no diretório do trabalho;

-Instalar o maven (recomendo a versão 3.6.0) e o openjdk-8-jdk-headless

    -No Ubuntu:

        sudo apt install maven

        sudo apt install openjdk-8-jdk-headless

    -Lembrando que é necessário alterar o JAVA_HOME e o MAVEN_HOME ao PATH ou variáveis de ambiente em alguns sistemas (Windows, ...)

-Instalar o docker-compose e o docker

    -No Ubuntu:

        sudo apt install docker.io

        sudo apt install docker-compose

-Fazer o build do projeto back-end (amazingmovie-api) executando o seguinte comando:

    mvn -f amazingmovies-api/pom.xml clean package

-Executar o docker-compose

    sudo docker-compose up --build

    
Acessar http://172.17.0.1/ (em maquinas unix) ou http://localhost/ uma vez que a rede criada no docker-compose está no driver bridge

----ATENÇÃO----

Caso o sistema retorne na página inicial o aviso "Não foi possível ler a lista de filmes!", verifique qual o IP de sua interface docker0 e altere o arquivo amazingmovies-ui/util/api.js colocando o IP de sua interface docker na seguinte linha:


baseURL: "http://"IP-DA-SUA-INTERFACE-DOCKER":8081/v1.0/",


A seguir dê CTRL+C no terminal onde está executando o docker-compose e execute "sudo docker-compose up --build" novamente.

-----------------------------Dicas para a execução do trabalho (modo normal)-------------------------------------------------------

-O arquivo de configuração do banco no back-end está em "amazingmovies-api/src/main/resources/application.properties";


-A configuração do IP do back-end no front-end se encontra em "amazingmovies-ui/util/api.js".

------------------------------Considerações finais---------------------------------------------------------------------------------


No back-end eu fiz testes de integração e testes com os serviços visualizando as práticas do projeto da aula 8. No front eu procurei componentizar o máximo que pude dado o tempo que tive. Ambas as tecnologias se apresentaram bem produtivas. O Spring Boot automatiza muita coisa que costuma gerar dor de cabeça em outras tecnologias (paginação, rotinas, migrations, mock, ...) e isso ajudou bastante. 
Durante as aulas meus colegas reclamaram muito que para fazer pouco era necessário muito código no front-end React. Contudo, depois desse processo de desenvolvimento, notei que a componentização pode gerar um gargalo maior de código no início da montagem do projeto, mas, se cada componente for bem genérico, é possível economizar uma quantidade enorme de código lá na frente por meio da velha máxima do reúso.





