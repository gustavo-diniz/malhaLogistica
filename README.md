<b>Entregando mercadorias</b>

O Walmart esta desenvolvendo um novo sistema de logística e sua ajuda é muito importante neste momento. Sua tarefa será desenvolver o novo sistema de entregas visando sempre o menor custo. Para popular sua base de dados o sistema precisa expor um Webservices que aceite o formato de malha logística (exemplo abaixo), nesta mesma requisição o requisitante deverá informar um nome para este mapa. É importante que os mapas sejam persistidos para evitar que a cada novo deploy todas as informações desapareçam. O formato de malha logística é bastante simples, cada linha mostra uma rota: ponto de origem, ponto de destino e distância entre os pontos em quilômetros.

A B 10 
B D 15
A C 20
C D 30
B E 50
D E 30

Com os mapas carregados o requisitante irá procurar o menor valor de entrega e seu caminho, para isso ele passará o mapa, nome do ponto de origem, nome do ponto de destino, autonomia do caminhão (km/l) e o valor do litro do combustível, agora sua tarefa é criar este Webservices. Um exemplo de entrada seria mapa SP, origem A, destino D, autonomia 10, valor do litro 2,50; a resposta seria a rota A B D com custo de 6,25.

<b>Arquitetura utilizada:</b>

Java 8
SpringMVC Rest
Spring Jdbc Template
Spring tests
Wildfly
Gson
Junit
Algoritmo Djikstra
Mysql
Maven

Foi escolhida esta arquitetura com foco em simplicidade, pensando na utilização de recursos e frameworks mais básicos para resolver este problema, sempre poderão existir formas mais simples de se desenvolver, mas para efeitos deste teste e pelo tempo, estes foram os recursos e frameworks que me motivarão a escolher esta arquitetura.
