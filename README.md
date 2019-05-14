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

con la primera opción puede ejecutar los endpoints para ver sus respuestas.

 
     

