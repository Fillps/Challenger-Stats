Challenger Stats

Utilizando a API da Riot (https://developer.riotgames.com/) criar uma aplicação que diponibiliza vários dados sobre os campeões de League of Legends, como itens, runas, talentos mais usados, builds com maior porcentagem de vitórias, e estatísticas gerais do campeão, com base em partidas de jogadores challenger das diversas regiões diponíveis. 

Bibliotecas utilizadas nesse projeto:

Challenger Stats usa a biblioteca Riot-api-java (https://github.com/rithms/riot-api-java).


Como compilar o Challenger Stats

Na pasta do projeto abra "arquivos" extraia "dados.part01.rar" para a pasta dados na pata do projeto (se ela não existir, crie uma).

Com o terminal aberto na pasta do projeto, execute o comando:

 javac -d target -cp "library/*;src/" src/TestesDeDesempenho.java
 java -cp "library/*;target/" TestesDeDesempenho