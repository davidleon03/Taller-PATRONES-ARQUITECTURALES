# Taller Patrones Arquitecturales | David Leon
## Objetivos
- Crear una instancia de MongoDB en una máquina virtual de EC2.
- Crear un servicio REST llamado LogService que reciba una cadena, la almacene en la base de datos y responda en un objeto JSON con las 10 últimas cadenas almacenadas en la base de datos y la fecha en que fueron almacenadas.
- Crear una aplicación web llamada APP-LB-RoundRobin que esté compuesta por un cliente web y al menos un servicio REST. El cliente web debe tener un campo y un botón que permita al usuario enviar un mensaje. Cada vez que el usuario envía un mensaje, este se lo envía al servicio REST y actualiza la pantalla con la información que este le regresa en formato JSON.
- Crear un servicio REST que reciba la cadena, implemente un algoritmo de balanceo de cargas de Round Robin y delegue el procesamiento del mensaje y el retorno de la respuesta a cada una de las tres instancias del servicio LogService.
## Herramientas utilizadas
 - Maven
 - Java 
 - Git 
 - Spark
 - Mongo
 - AWS
 
## Instancias
- Procedemos a crear la siguiente arquitectura con instancias EC2 en aws
![image](https://user-images.githubusercontent.com/98216838/225403398-5aad9dbd-a6b9-448b-80fd-6591b6a01a7a.png)
- Las creamos y las dejamos en ejecucion para proceder a configurarlas mas adelante
![image](https://user-images.githubusercontent.com/98216838/225126701-fe1c33ff-41d5-4617-bfed-7a03ec06f1ae.png)

## MONGO
- Se crea la variable .repo de configuracion de mongo
![image](https://user-images.githubusercontent.com/98216838/224846231-23efd151-1bc7-48ec-9939-ad7d0dcceb11.png)
- Ser procede a instalar mongo
![image](https://user-images.githubusercontent.com/98216838/224846297-e5234caf-3456-4655-bb63-fd121d55ba62.png)
![image](https://user-images.githubusercontent.com/98216838/224846378-936f81e0-ee7a-4720-86a9-7450efa295c4.png)
- Verificamos su instalacion exitosa y la version
![image](https://user-images.githubusercontent.com/98216838/224846428-ae43e6b6-0128-40eb-87c0-842af8b359ed.png)
- Configuramos para que el servicio siempre se este ejecutanto y verificamos su estado
![image](https://user-images.githubusercontent.com/98216838/224846761-66dfe1bd-db28-401d-98fd-a5153ce01b22.png)
- Configuramos el archivo mongo.conf, debemos colocar en acceso 0.0.0.0 para que cualquier persona pueda acceder
![image](https://user-images.githubusercontent.com/98216838/224846951-4c9c996e-2d47-4b5d-a28e-e829bb29c503.png)
- Creamos la base de datos


![image](https://user-images.githubusercontent.com/98216838/224847133-9dad8973-c483-4601-acfc-41c28dd63bbe.png)
![image](https://user-images.githubusercontent.com/98216838/224848308-c95fc145-fc42-4f8a-9281-c66708952cd1.png)

## JAVA
- En cada una de las instancias de LogService y RoundRobin debemos tener instalado JAVA para la ejecucion del programa
![image](https://user-images.githubusercontent.com/98216838/225128369-bb63a893-eea6-4e03-b5e8-2d43f60ca956.png)
![image](https://user-images.githubusercontent.com/98216838/225128540-b4204421-f10c-4994-ab57-8bb68664658f.png)
## GIT
- Igualmente debemos tener GIT en todas las instancias para poder clonar el repositorio y ejecutarlo
![image](https://user-images.githubusercontent.com/98216838/225128738-9053801f-896b-4984-948c-5e2456bb8967.png)
## Configuracion para la ejecucion
- Procedemos a configurar los grupos de seguridad de las instancias de LogService
![image](https://user-images.githubusercontent.com/98216838/225138408-b11342e4-cce3-42e1-b0d6-c9fbef06d3f2.png)
- Lo ejecutamos
![image](https://user-images.githubusercontent.com/98216838/225137651-af29ed72-893f-48db-b72a-8eea7a68056c.png)
## Pruebas de LogService
- Realizamos pruebas de manera local de cada una de las instancias de LogService y verificamos el funcionamiento y conexion a la base de datos creada en EC2

![image](https://user-images.githubusercontent.com/98216838/225138463-d9b7a91c-4f3b-4b25-9b5c-9272729890df.png)
## Prueba RoundRobin local con LogService en EC2
- Creamos el balanceador de cargas entre las 3 instacias creadas
![image](https://user-images.githubusercontent.com/98216838/225186904-4b6884e6-0b77-4632-82f9-67c18f1fce58.png)
- Verificamos su funcionamineto local
![image](https://user-images.githubusercontent.com/98216838/225186858-12d5f759-4199-4327-8920-10747edfdbfe.png)
![image](https://user-images.githubusercontent.com/98216838/225194581-17574ae3-a37a-4875-9f7f-c0705dc9d685.png)
![image](https://user-images.githubusercontent.com/98216838/225194834-a57a1bf1-6d50-4ab5-a5bb-77a906e5fcda.png)
## RoundRobin en AWS
- Montamos RoundRobin en AWS y se verifica su funcionamiento
![image](https://user-images.githubusercontent.com/98216838/225195391-e75cfde6-0f8f-42e3-8217-cbe2b9229ea8.png)
![image](https://user-images.githubusercontent.com/98216838/225195488-e2498f80-df89-4a76-9709-923ffc80d159.png)
![image](https://user-images.githubusercontent.com/98216838/225195775-9aea58d2-cf42-4cc0-9a54-9ad808c15394.png)
## Documentacion

Para crear la documentación del proyecto se hace con el siguiente comando:
    
    mvn javadoc:javadoc
    

## Autor

* **David Leon**
