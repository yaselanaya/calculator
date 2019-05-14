## Calculadora de Frete Rest Api ##

** Proposta para uma solução: **

Existe uma entidade empresarial que tem duas empresas que estabelecem o negócio:

Origem da Empresa => Onde a mercadoria sai

Destino da empresa => Para onde a mercadoria está indo.

Além disso, a entidade comercial tem um destino que tem uma sobretaxa aplicada a cada barco
que transporta a mercadoria pelo peso da carga.

Ele também tem uma entidade ShipLoad que mantém a relação do navio e a carga em peso
que transporta o barco em um determinado negócio.

Uma empresa pode ter um ou mais ShipLoad, já que vários navios podem estar envolvidos na empresa.

O cálculo do frete não é mais do que a carga em peso que leva cada barco multiplicado pela sobretaxa do destino e
o somatório de cada frete dará o frete total que é obtido para cada negócio.


** Tecnologias utilizadas: **

Idioma => Java JDK 1.8

Framework => Spring Boot 2.1.4

Gerente de Dependência => Maven

Controle de versão => Git

Documentação da api => Swagger 2.9.2

Banco de dados => PostgresSql 9.4

** Arquitetura: **

- A arquitetura foi baseada nos princípios definidos pelo Domain Driven Design (DDD)

** Etapas e requisitos para executar a API REST: **

1- Instalar o PostgresSql

2- Criar banco de dados de calculadora

3- Configurar conexão ao banco de dados no arquivo application.properties

3- Importar projeto como projeto maven em um IDE

4- Execute o projeto como um aplicativo de inicialização de mola

5- Para empacotar o projeto, instale o maven e execute o comando: mvn package

6- Execute o jar executando o comando: java -jar calculator-0.0.1-SNAPSHOT.jar

7- Para ver os pontos de extremidade, abra o navegador e copie o url: 

http://localhost:7000/api/v1.0/swagger-ui.html, http://localhost:7000/api/v1.0/v2/api-docs

- com a primeira opção, você pode executar os endpoints para ver suas respostas.

Para os terminais das listas, uma solução de filtro foi implementada, consistindo em um objeto de formato:

eg: data={"filters":[{"field":"name","value":"One","operator":"CONTAINS"}],"pageable":{"page":1,"size":10,"orders":[{"direction":"ASC","property":"id"}]}}

Onde tem a chave filtra que é uma matriz de objetos que são compostos de um campo chave que é o nome do campo a ser filtrado,
valor que é o valor para filtrar e o operador, então você irá comparar operadores, um enum que possui os seguintes valores:

EQUALITY,NEGATION,GREATER_THAN,LESS_THAN,LIKE,STARTS_WITH,ENDS_WITH,CONTAINS,NUMBER_LESS_EQUAL_THAN,NUMBER_GREATER_EQUAL_THAN,COMPARABLE_LESS_EQUAL_THAN,
COMPARABLE_GREATER_EQUAL_THAN,DATE_GREATER_EQUAL_THAN,DATE_LESS_EQUAL_THAN,IN,NOT_IN,IS_NULL,NOT_NULL,IS_EMPTY,IS_NULL_OR_EMPTY,IS_NOT_EMPTY

Ele também tem uma página de chave que tem uma página chave que não é mais do que o número da página a ser exibida,
tamanho da chave que representa o número de elementos por página e uma última chave que possui uma matriz de objetos com a chave
direção que leva os valores ASC ou DESC e a propriedade de chave que é a propriedade para a qual a lista será classificada.



## Rest Api Calculador de flete ##

**Propuesta de solución:**

Tenemos una entidad negocio la cual cuenta con dos compañias que son la que establecen el negocio:

Compañia Origen => Desde donde parte la mercancía 

Compañia Destino => Hacia donde va dirigida la mercancía.

Ademas la entidad negocio cuenta con un destino el cual tiene un recargo que se le aplica a cada barco
que transporta la mercancía por el peso de la carga.

También cuenta con una entidad ShipLoad la cual guarda la relacción del barco y la carga en peso
que transporta el barco en un negocio determinado.

Un negocio puede tener uno o mas ShipLoad ya que puede que estén involucrado varios barcos en el negocio.

El cálculo del flete no es mas que la carga en peso que lleva cada barco multiplicada por el recargo del destino y
la sumatoría de cada flete dará el total del flete que se obtiene para cada negocio.


**Tecnologías utilizadas:**

Lenguaje => Java JDK 1.8

Framework => Spring Boot 2.1.4

Gestor de dependencias => Maven

Control de versiones => Git

Documentación de la api => Swagger 2.9.2

Base de Datos => PostgresSql 9.4

**Arquitectura:**

- La arquitectura fue basada en los principios que define Domain Driven Design(DDD)

**Pasos y requisitos para correr el API REST:**

1- Instalar PostgresSql

2- Crear base de datos calculator

3- Configurar conexion a base de datos en el archivo application.properties

3- Importar projecto como projecto de maven en un IDE

4- Correr proyecto como aplicacción de spring boot

5- Para empaquetar el proyecto instalar maven y ejecutar comando: mvn package en la consola

6- Correr jar ejecutando el comando: java -jar calculator-0.0.1-SNAPSHOT.jar

7- Para ver los endpoints abrir el navegador y copiar url: 
http://localhost:7000/api/v1.0/swagger-ui.html ó http://localhost:7000/api/v1.0/v2/api-docs

- con la primera opción puede ejecutar los endpoints para ver sus respuestas.

 
Para los endpoints de los filtros se implemento una solución que consiste en un objeto del formato:

eg: data={"filters":[{"field":"name","value":"One","operator":"CONTAINS"}],"pageable":{"page":1,"size":10,"orders":[{"direction":"ASC","property":"id"}]}}

Donde cuenta con el key filters el cual es un arreglo de objetos que están compuestos por un key field el cual es el nombre del campo a filtar, 
value el cual es el valor para filtrar y el key operator por lo que vas a comparar, operator es un enum que cuenta con los valores siguientes:

EQUALITY,NEGATION,GREATER_THAN,LESS_THAN,LIKE,STARTS_WITH,ENDS_WITH,CONTAINS,NUMBER_LESS_EQUAL_THAN,NUMBER_GREATER_EQUAL_THAN,COMPARABLE_LESS_EQUAL_THAN,
COMPARABLE_GREATER_EQUAL_THAN,DATE_GREATER_EQUAL_THAN,DATE_LESS_EQUAL_THAN,IN,NOT_IN,IS_NULL,NOT_NULL,IS_EMPTY,IS_NULL_OR_EMPTY,IS_NOT_EMPTY

Ademas cuenta con un key pageable el cual cuenta con el key page que no es más que el número de la página a mostrar, 
key size que representa la cantidad de elemntos por páginas y un ultimo key que cuenta con un arreglo de objetos con las key 
direction que toma los valores ASC o DESC y el key property que es la propiedad por la que se va a ordenar la lista.

