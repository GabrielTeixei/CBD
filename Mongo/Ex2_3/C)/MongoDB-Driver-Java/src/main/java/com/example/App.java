package com.example;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

public class App {

    public static void main(String[] args) {
        // Obtém a coleção do MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("cbd"); // Nome do seu banco de dados
        MongoCollection<Document> collection = database.getCollection("restaurants");

        // 1. Listar todos os documentos da coleção
        System.out.println();
        System.out.println("Todos os documentos da coleção:");  
        listarDocumentos(collection);
        System.out.println();

        // 2. Apresentar restaurant_id, nome, localidade e gastronomia para todos os documentos
        System.out.println("----------------------------------------");
        System.out.println("restaurant_id, nome, localidade e gastronomia para todos os documentos:");
        listarCampos(collection, "restaurant_id", "nome", "localidade", "gastronomia");
        
        // 3. Apresentar restaurant_id, nome, localidade e código postal, excluindo o campo _id
        System.out.println(); 
        System.out.println("----------------------------------------");

        System.out.println("restaurant_id, nome, localidade e código postal, excluindo o campo _id:");
        listarCamposExcluindoID(collection, "restaurant_id", "nome", "localidade", "codigo_postal");

        System.out.println();
        System.out.println("----------------------------------------");
        // 4. Indicar o total de restaurantes localizados no Bronx

        long totalNoBronx = contarRestaurantesNoBronx(collection);
        System.out.println("Total de restaurantes no Bronx: " + totalNoBronx);

        System.out.println();
        System.out.println("----------------------------------------");
        listarRestaurantesNoBronxOrdenados(collection, 15);



        // 7. Encontre os restaurantes que obtiveram uma ou mais pontuações (score) entre
        // [80 e 100]
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("Restaurantes que obtiveram uma ou mais pontuações (score) entre [80 e 100]:");
        MongoCursor<Document> cursor = collection.find(Filters.and(Filters.gte("score", 80), Filters.lte("score", 100))).iterator();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                System.out.println(document.toJson());
            }
        } finally {
            cursor.close();
        }

        
    }

    //1
    private static void listarDocumentos(MongoCollection<Document> collection) {
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                System.out.println(document.toJson());
            }
        } finally {
            cursor.close();
        }
    }
    //2
    private static void listarCampos(MongoCollection<Document> collection, String... campos) {
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                StringBuilder output = new StringBuilder();
                for (String campo : campos) {
                    output.append(campo).append(": ").append(document.getString(campo)).append(", ");
                }
                System.out.println(output.toString());
            }
        } finally {
            cursor.close();
        }
    }
    //3
    private static void listarCamposExcluindoID(MongoCollection<Document> collection, String... campos) {
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                document.remove("_id");
                StringBuilder output = new StringBuilder();
                for (String campo : campos) {
                    output.append(campo).append(": ").append(document.getString(campo)).append(", ");
                }
                System.out.println(output.toString());
            }
        } finally {
            cursor.close();
        }
    }
    //4
    private static long contarRestaurantesNoBronx(MongoCollection<Document> collection) {
        return collection.countDocuments(Filters.eq("localidade", "Bronx"));
    }

    //7. Encontre os restaurantes que obtiveram uma ou mais pontuações (score) entre[80 e 100]
    private static void listarRestaurantesNoBronxOrdenados(MongoCollection<Document> collection, int limit) {
        MongoCursor<Document> cursor = collection.find(
                Filters.and(
                        Filters.eq("localidade", "Bronx"),
                        Filters.elemMatch("grades", Filters.and(
                                Filters.gt("grades.score", 80),
                                Filters.lte("grades.score", 100)
                        ))
                ))
                .sort(Sorts.ascending("nome"))
                .limit(limit)
                .iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                System.out.println(document.toJson());
            }
        } finally {
            cursor.close();
        }
    }
}
