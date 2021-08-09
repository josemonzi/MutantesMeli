# MutantesMeli
Microservicio Rest para buscar mutantes mediante el código del ADN

###REQUERIMIENTOS
 
 - AWS (si lo quiere instalar en la nube)
 - Java 8
 - Maven
 - Mysql

# Instalación máquina Local

1- Clonar el proyecto del repositorio https://github.com/josemonzi/MutantesMeli.git, desde una consola cmd ejecutando el siguiente 
 comando: git clone https://github.com/josemonzi/MutantesMeli.git

2- Ejecutar el archivo dbMutante.sql para realizar la instalación de la base de datos, el script se encuentra en la ruta ./Mutantes/src/main/resources/instalacion/bd/, se recomienda realizar la instalación en un motor de base de datos MYSQL.

3- para configurar la conexión a la base de datos modificar el archivo application.properties ubicado en la ruta ./Mutantes/src/main/resources/application.properties donde habilita la configuración local 
y modifica los parámetros spring.datasource.url, spring.datasource.username, spring.datasource.password, como el siguiente ejemplo:

spring.datasource.url=jdbc:mysql://localhost:3306/DBMutante?zeroDateTimeBehavior=convertToNull&serverTimezone=America/Bogota&useSSL=false
spring.datasource.username=usuarioConsulta
spring.datasource.password=usuarioConsulta123*

4- desde cmd y ubicado en la carpeta donde clonó el repositorio, se crea el artefacto del proyecto. 
Nota: validar que su maquina reconozca los comandos maven, ejecutando el comando mvn -v desde la consola.

Para la generación del artefacto con los comandos de maven lanzamos el comando: mvn install

5- Se verifica que se cree el artefacto en la ruta ./target/Mutantes-1.jar 

6- Una vez comprobado que esta el artefacto, lo ejecutamos desde la carpeta target con el comando de Java: java -jar Mutantes-1.jar

7- verificando la consola podemos observar el puerto y si inicio el microservicio.

# Instalación en la Nube AWS

realizar los puntos 1, 4, 5 de la instalación máquina local; tomar el artefacto generado y desplegarlo en el servicio de AWS de su preferencia, Además se debe crear la base de datos (se recomienda con el servicio RDS) y configurar las variables de entorno anteriormente nombradas.
la primera configurar en la maquina las variables de entorno:

spring.datasource.url= Url de la base de datos.
spring.datasource.username = usuario para conectarse a la base de datos.
spring.datasource.password = contraseña para conectarse a la base de datos.

### Diagrama de Clases

En la solución se utilizó el patrón MVC para el intercambio de información entre capas y el patrón Strategy para el recorrido de la matriz al encontrar si es un mutante.

![Screenshot](https://github.com/josemonzi/MutantesMeli/blob/dev/Mutantes/src/main/resources/imagenes/Diagrama_Clases.gif?raw=true)

El software se desarrolla con el framework Spring Boot versión 2.5.3, Java 8, y diversas librerías para el manejo del test (Junit versión 4.13.1, Mockito 3.6.28, jacoco 0.8.0).


## Información Paquetes

A continuación, se define los paquetes implementados en el aplicativo. Los cuales almacenan las clases e interfaces.

*com.meli.co.mutantes.configuracion: Clases bean para inyección de dependencias.

*com.meli.co.mutantes.controller: Clases controladores donde llega el llamado del servicio por http Método Post y Get.

*com.meli.co.mutantes.dao: Interfaz de las consultas a base de datos.

*com.meli.co.mutantes.dto: Clases que tiene la estructura de datos tanto de entrada como de salida de la aplicación.

*com.meli.co.mutantes.entities: Clases entidades creadas a partir del modelo de base de datos.

*com.meli.co.mutantes.exception: Clasese para el manejo de excepciones de la aplicación.

*com.meli.co.mutantes.mapper: Interfaces de las clases encargadas del mapeo de datos de la capa negocio a la capa presentación.
 
*com.meli.co.mutantes.mapper.impl: Clases encargadas del mapeo de datos de la capa negocio a la capa presentación.

*com.meli.co.mutantes.service: Interfaces de las clases de la capa de servicio del proyecto.

*com.meli.co.mutantes.service.impl: Clases encargadas de la capa de negocio.

*com.meli.co.mutantes.util: clases auxiliares que tiene apoya la lógica del proyecto.

### Test

Puede verificar el test realizado a las diferentes clases entrando en la maquina local a la ruta ./target/site/jacoco/index.html, antes de abrir el .html
debemos ejecutar el siguiente comando maven en la consola: mvn clean compile verify 


# ENLACES

Swagger: http://appfuegoquasarapp-env.eba-ans4pfnh.us-east-2.elasticbeanstalk.com/v01/fuegoQuasar/swagger-ui.html

Metodo Post /topsecret: http://appfuegoquasarapp-env.eba-ans4pfnh.us-east-2.elasticbeanstalk.com/v01/fuegoQuasar/topsecret

Metodo Post /topsecret_split/{satellite_name}: http://appfuegoquasarapp-env.eba-ans4pfnh.us-east-2.elasticbeanstalk.com/v01/fuegoQuasar/topsecret_split/{satellite_name}

Metodo Get /topsecret_split: http://appfuegoquasarapp-env.eba-ans4pfnh.us-east-2.elasticbeanstalk.com/v01/fuegoQuasar/topsecret_split/

Base de datos: jdbc:mysql://database-quasar.ca7azgha2tnz.us-east-2.rds.amazonaws.com:3306/FuegoQuasar?zeroDateTimeBehavior=convertToNull&serverTimezone=America/Bogota&useSSL=false





