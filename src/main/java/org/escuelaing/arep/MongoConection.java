package org.escuelaing.arep;

import com.mongodb.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Sorts.*;

public class MongoConection {
    public static MongoClient mongoClient;

    /**
     Connect to MongoDB database using the provided connection string.

     This method uses the default server API version V1.

     @throws MongoClientException if connection fails.
     */
    public static void connect() {
        // Conexión a base de datos mongodb, Generado automaticamente por mongo

        ConnectionString connectionString = new ConnectionString("mongodb://myServerAdmin:password@34.200.220.118:27017/arep");
        mongoClient = MongoClients.create(connectionString);
        System.out.println("SE CONECTO -------------------------------------------------------------");

    }
    /**
     * Funcion encargada de ingresar el string a la base de datos
     * retorna un JSON con los ultimos 10 valores que se encuentren en la base de datos
     * @param valueChain the value to store in the "VALOR" field of the new document
     * @return a JSON string representation of the 10 most recently created documents
     */
    public static String insertIntoDB(String valueChain) {
        MongoDatabase database = mongoClient.getDatabase("arep");
        MongoCollection<Document> chains = database.getCollection("cadenas");
        Document chain = new Document("_id", new ObjectId());
        chain.append("VALOR", valueChain);
        chain.append("CREADO", LocalDateTime.now());
        chains.insertOne(chain);
        System.out.println("LO QUE SE AÑADE A MONGO");
        System.out.println(chains.find());
        Bson orderBySort = orderBy(descending("CREADO"));
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(printChains(chains, orderBySort));
    }

    /**
     * Funcion generada para imprimir los ultimos 10 registros
     * @param chains the MongoDB collection to query
     * @param ordeBySort the sort key to use for sorting documents
     * @return an ArrayList of the 10 sorted documents as JSON objects
     */
    public static ArrayList<Document> printChains(MongoCollection<Document> chains,Bson ordeBySort){
        ArrayList<Document> jsonObjects = new ArrayList<>();
        FindIterable<Document> iterable = chains.find().sort(ordeBySort).limit(10);
        for (Document d : iterable) {
            System.out.println(d);
            jsonObjects.add(d);
        }
        return jsonObjects;
    }
}
