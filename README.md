#Challenger Stats

###O que é o Challenger Stats?

Nosso trabalho opera com dados do jogo [League of Legends](http://br.leagueoflegends.com/), no qual as partidas são jogadas com dois times de cinco jogadores, e cada jogador escolhe um campeão/personagem disponível. Dependendo da partida, o jogador pode usar itens, runas, talentos e habilidades diferentes para ajudá-lo. O que o nosso programa se propõe a fazer é criar um banco de dados dos conjuntos de itens, runas, talentos e habilidades para cada campeão disponível no jogo, com base na porcentagem de vitórias e frequência de uso entre os melhores jogadores.

###Pré-requesitos:

Challenger Stats precisa de Java 8 e as seguintes bibliotecas:

  - [Riot-api-java](https://github.com/rithms/riot-api-java)
  - [Google Gson](https://github.com/google/gson) - Apenas se desejar obter os dados apartir do Riot API

###Como conseguimos os dados?

Para obter os dados das partidas usamos a API dos próprios desenvolvedores, a [Riot API](veloper.riotgames.com). E para conseguir administrar a API facilmente, resolvemos utilizar uma biblioteca disponível no GitHub, a [Riot-api-java](https://github.com/rithms/riot-api-java). Como desejamos obter os dados dos melhores jogadores de cada região (nível Challenger) utilizamos o método já existente na API, onde retorna a ID dos duzentos jogadores challenger de uma certa região. Após conseguir o ID, buscamos por partidas em um certo intervalo de tempo (patch), pois o jogo está em constante atualização. Outros dados que obtemos foram informações estáticas do jogo, dados de uso geral como:

  -	Campeões
  -	Itens
  -	Talentos
  -	Runas
  - Habilidades de Invocador

###Etapa 2:
Após conseguir os dados das partidas, armazenamos em um array e o serializamos, salvando em disco. Como parte do trabalho proposto, desenvolvemos e testamos o desempenhos dos seguintes algoritmos de ordenação:

-	Insetion Sort com Busca Linear
-	Insertion Sort com Busca Binária
-	Shell Sort
-	Bubble Sort
-	Quick Sort Randomizado
-	Merge Sort
-	Heap Sort
-	Radix Sort

Concluindo que para a nossa aplicação o algoritmo com melhor tempo foi o Quick Sort Randomizado, porém como ele não é estável, optamos pelo Merge Sort que possuí um desempenho satisfatório e é estável. 

##Diagrama ER:

Para criar o nosso banco de dados, criamos o seguinte diagrama ER:

![alt tag](https://raw.githubusercontent.com/Fillps/Challenger-Stats/master/arquivos/Challenger-Stats-ER.PNG)

Como a nossa aplicação se propõe a oferecer estatísticas gerais de campeões, a estrutura de dados se centraliza em torno da entidade campeão, com ele possuindo chaves estrangeiras das outras entidades. Durante a partida, cada campeão pode escolher usar um conjunto de itens, runas, talentos e habilidades, sendo assim cada um desses é uma entidade no diagrama.

###Entidades:

-	**Champion (Campeão):**

Na entidade campeão, teremos como atributos porcentagem de vitórias, média de uso, quantidade de jogos analisados, dano causado, entre diversas outras estatísticas que obtemos com as partidas analisadas. Além disso, terá chaves estrangeiras das entidades Build, Rune Page, Mastery Page e Summoner Spells. Cada campeão terá duas chaves de cada uma das entidades citadas, uma com maior frequência de uso, e outra com maior porcentagem de vitórias.

-	**Rune Page (Página de Runas), Mastery Page (Página de Talentos), Build (Conjunto de Itens):**

Cada uma dessas entidades possui um comportamento muito similar. Cada uma delas é um conjunto de Runas, Talentos ou Itens, nos quais podem ser repetidos. Elas também podem serem usadas por diversos campeões.

-	**Summoner Spells (Habilidades de Invocador):**

Os Summoner Spells contêm apenas duas habilidades de invocador, sendo assim guardando duas chaves estrangeiras das habilidades. 

-	**Rune (Runa), Mastery (Talentos), Item, Summoner Spells (Habilidades de Invocador):**

Essas entidades irão guardar as informações de cada um desses elementos, por exemplo, bônus fornecido, tamanho do bônus fornecido, preço, entre outros. Essas são as informações estáticas já mencionadas anteriormente.


###Relações:

-	**RunePage_Rune, Build_Item, MasteryPage_Mastery:** (n-m)

-	**Demais:** (1-n)


##Como executar o Challenger Stats

###Executar testes de desempenho:

Na pasta "arquivos" extraia *dados.part01.rar*.

Com o terminal aberto na pasta do projeto, execute os comandos:
```
mkdir target
javac -d target -cp "library/*;src/" src/TestesDeDesempenho.java
java -cp "library/*;target/" TestesDeDesempenho
```
