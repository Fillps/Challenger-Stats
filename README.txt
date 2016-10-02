Challenger Stats

Utilizando a API da Riot (https://developer.riotgames.com/) criar uma aplicação que diponibiliza vários dados sobre os campeões de League of Legends, como itens, runas, talentos mais usados, builds com maior porcentagem de vitórias, e estatísticas gerais do campeão, com base em partidas de jogadores challenger das diversas regiões diponíveis. 

Bibliotecas utilizadas nesse projeto:

Challenger Stats usa a biblioteca Riot-api-java (https://github.com/rithms/riot-api-java).


Como executar o Challenger Stats

Na pasta "arquivos" extraia "dados.part01.rar".

Com o terminal aberto na pasta do projeto, execute os comandos:
 mkdir target
 javac -d target -cp "library/*;src/" src/TestesDeDesempenho.java
 java -cp "library/*;target/" TestesDeDesempenho