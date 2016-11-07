#Challenger Stats

###O que é o Challenger Stats?

Nosso trabalho opera com dados do jogo [League of Legends](http://br.leagueoflegends.com/), no qual as partidas são jogadas com dois times de cinco jogadores, e cada jogador escolhe um campeão/personagem disponível. Dependendo da partida, o jogador pode usar itens, runas, talentos e habilidades diferentes para ajudá-lo. O que o nosso programa se propõe a fazer é criar um banco de dados dos conjuntos de itens, runas, talentos e habilidades para cada campeão disponível no jogo, com base na porcentagem de vitórias e frequência de uso entre os melhores jogadores.


###Como conseguimos os dados?

Para obter os dados das partidas usamos a API dos próprios desenvolvedores, a [Riot API](veloper.riotgames.com). E para conseguir administrar a API facilmente, resolvemos utilizar uma biblioteca disponível no GitHub, a [Riot api java](https://github.com/rithms/riot-api-java). Como desejamos obter os dados dos melhores jogadores de cada região (nível Challenger) utilizamos o método já existente na API, onde retorna a ID dos duzentos jogadores challenger de uma certa região. Após conseguir o ID, buscamos por partidas em um certo intervalo de tempo (patch), pois o jogo está em constante atualização. Outros dados que obtemos foram informações estáticas do jogo, dados de uso geral como:

  -	Campeões
  -	Itens
  -	Talentos
  -	Runas
  - Habilidades de Invocador


###Funcionalidades Previstas

O programa terá as seguintes funcionalidades:

  - Obter partidas a partir da Riot API.
  - Inserir partidas, obtendo os dados, como porcentual de vitorias, frequência de uso entre outros.
  - Listar todos campeões por ordem alfabética, frequência de uso e porcentual de vitorias.
  - Permitir a busca por nome de campeão, com auto completar.
  - Permitir a busca por item, runa, talento e habilidades de invocador.
  - Recomendar itens, runas, habilidades de invocador, páginas de talentos e ordem de habilidades a serem utilizados em um certo campeão.


###Ferramentas

  - **Java:** Java é uma linguagem de programação interpretada orientada a objetos que é compilada e executado por uma máquina virtual. Uma das características e a portabilidade e independência de plataforma - "escreva uma vez, execute em qualquer lugar" ("write once, run anywhere"). Devido a suas similaridades com C/C++ e ao desejo de aprender uma nova linguagem de programação, escolhemos Java para esse projeto.
  - **Biblioteca [Riot api java](https://github.com/rithms/riot-api-java):** Um método simples de usa a Riot API para Java. Esta biblioteca faz com que seja fácil de reunir e usar dados de League of Legends em seus aplicativos. 
  - **[Eclipse](https://eclipse.org/):** Eclipse é um IDE para desenvolvimento Java, porém suporta várias outras linguagens a partir de plugins como C/C++,PHP,ColdFusion, Python, Scala e plataforma Android. Foi escolhido como IDE do projeto para a facilidade de uso e organização.


###Atividades

  - **Aprendizagem a linguagem de programação:** consiste em aprender a linguagem Java, que foi a linguagem escolhida para a implementação do trabalho. O tempo médio dessa atividade é duas semanas. 
  - **Aprendizagem da Riot api java:** consiste em aprender a biblioteca que foi escolhida para manusear a Riot API e como obter os dados necessários dela. O tempo médio dessa atividade é uma semana.
  - **Análise e escolha dos algoritmos:** consiste em analisar o desempenho dos algoritmos de classificação e pesquisa vistos em aula e escolher quais são os melhores para resolver o problema proposto nesse trabalho. O tempo médio dessa atividade é algumas horas.
  - **Implementação do trabalho:** consiste em implementar o algoritmo para realizar a proposta do trabalho de armazenar e manipular dados de campeões. O tempo médio dessa atividade é um mês.
  - **Testes e Melhorias:** consiste em testar do programa implementado a fim de encontrar erros, e então aplicar melhorias ao programa, resolvendo falhas e aprimorando o desempenho. O tempo médio dessa atividade é uma semana.

###Análise e escolha dos algoritmos:

Como parte do trabalho proposto, desenvolvemos e testamos os desempenhos dos seguintes algoritmos de ordenação:

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

  -	**Champion (Campeão):** Na entidade campeão, teremos como atributos porcentagem de vitórias, média de uso, quantidade de jogos analisados, dano causado, entre diversas outras estatísticas que obtemos com as partidas analisadas. Além disso, terá chaves estrangeiras das entidades Build, Rune Page, Mastery Page e Summoner Spells. Cada campeão terá duas chaves de cada uma das entidades citadas, uma com maior frequência de uso,  e outra com maior porcentagem de vitórias.

  -	**Rune Page (Página de Runas), Mastery Page (Página de Talentos) e Build (Conjunto de Itens):** Cada uma dessas entidades possui um comportamento muito similar. Cada uma delas é um conjunto de Runas, Talentos ou Itens, nos quais podem ser repetidos. Elas também podem serem usadas por diversos campeões.

  -	**Rune (Runa), Mastery (Talentos), Item e Summoner Spells (Habilidades de Invocador):** Essas entidades irão guardar as informações de cada um desses elementos, por exemplo, bônus fornecido, tamanho do bônus fornecido, preço, entre outros. Essas são as informações estáticas já mencionadas anteriormente.


###Relações:

  -	**RunePage_Rune, Build_Item e MasteryPage_Mastery (n-m):** Uma relação onde guarda um atributo de quantas vezes uma entidade se relaciona a outra entidade.

  -	**Champion_RunePage, Champion_Build, Champion_MasteryPage e Champion_SummonerSpell (n-m):** Uma relação onde tem como chave primária os IDs das entidades e como atributo, os dados da relação entre essas entidades.

##Como executar o Challenger Stats

###Pré-requesitos:

Challenger Stats precisa de Java 8 e as seguintes bibliotecas:

  - [Riot api java](https://github.com/rithms/riot-api-java)
  - [Google Gson](https://github.com/google/gson) - Parte do requisito para usar a biblioteca Riot api java.

###Executar testes de desempenho:

Na pasta ***arquivos*** extraia ***dados.part01.rar***.

Com o terminal aberto na pasta do projeto, execute os comandos:
```
mkdir target
javac -d target -cp "library/*;src/" src/tests.TestesDeDesempenho.java
java -cp "library/*;target/" tests.TestesDeDesempenho
```
