package org.escuelaing.arep;

import static spark.Spark.*;
import java.io.IOException;


public class LogService {
    /**
     Método principal encargado de iniciar el servidor web Spark y definir las rutas
     y acciones que deben ejecutarse en respuesta a las solicitudes del cliente.
     @param args los argumentos de línea de comando, no se utilizan
     @throws IOException si hay un error al leer el puerto de un archivo de configuración
     */
    public static void main(String[] args) throws IOException {
        port(getPort());
        //Path encargado de retornar las 10 ultimas cadenas insertadas en la coleccion
        get("/insert/:cadenaValue", (req, res) -> {
            res.status(200);
            res.header("Access-Control-Allow-Origin", "*");
            MongoConection.connect();
            return MongoConection.insertIntoDB(req.params(":cadenaValue"));
        });
    }

    /**
     Obtiene el número de puerto en el que se debe ejecutar el servidor web Spark.
     El número de puerto se puede especificar a través de una variable de entorno "PORT".
     Si la variable de entorno no está presente, se utiliza el valor predeterminado 4567.
     @return el número de puerto en el que se debe ejecutar el servidor web
     */
    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}