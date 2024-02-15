package com.example;
import org.bson.Document;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class App {

    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("mydb"); // Nome do seu banco de dados

            // Obtendo a coleção
            MongoCollection<Document> collection = database.getCollection("restaurantes");

            // Inserindo um novo documento
            Document newRestaurant = new Document("nome", "mar")
                    .append("cuisine", "Variada")
                    .append("address", new Document("street", "Costa nova").append("city", "Cidade torre a vista"));
            collection.insertOne(newRestaurant);
            System.out.println("Documento inserido com sucesso!");

            // Atualizando um documento
            collection.updateOne(Filters.eq("nome", "rua da barra"),
                    Updates.set("cuisine", "Peixe"));
            System.out.println("Documento atualizado com sucesso!");

            // Pesquisando documentos
            Document foundRestaurant = collection.find(Filters.eq("nome", "DOCNemo")).first();
            if (foundRestaurant != null) {
                System.out.println("Documento encontrado: " + foundRestaurant.toJson());
            } else {
                System.out.println("Nenhum restaurante encontrado com o nome 'DOCNemo'.");
            }
        }
    }
}
