package com.seuexemplo;
import org.bson.Document;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.Indexes;

public class MongoDBExample {

    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("mydb");

            // Obtendo a coleção
            MongoCollection<Document> collection = database.getCollection("restaurantes");

            // Criando índices
            collection.createIndex(Indexes.ascending("address.city")); // Índice para localidade
            collection.createIndex(Indexes.ascending("cuisine")); // Índice para gastronomia
            collection.createIndex(Indexes.text("nome")); // Índice de texto para o nome

            // Inserindo um novo documento
            Document newRestaurant = new Document("nome", "mar")
                    .append("cuisine", "Variada")
                    .append("address", new Document("street", "UA").append("city", "passaro aluz"));
            collection.insertOne(newRestaurant);
            System.out.println("Documento inserido com sucesso!");

            // Atualizando um documento
            collection.updateOne(Filters.eq("nome", "deti"),
                    Updates.set("cuisine", "Peixe"));
            System.out.println("Documento atualizado com sucesso!");

            System.out.println();
            // Pesquisando documentos por localidade
            Document foundRestaurantByCity = collection.find(Filters.eq("address.city", "Ctesssa")).first();
            if (foundRestaurantByCity != null) {
                System.out.println("Restaurante encontrado por localidade: " + foundRestaurantByCity.toJson());
            } else {
                System.out.println("Nenhum restaurante encontrado nesta localidade.");
            }
                System.out.println();
            // Pesquisando documentos por gastronomia
            Document foundRestaurantByCuisine = collection.find(Filters.eq("cuisine", "C")).first();
            if (foundRestaurantByCuisine != null) {
                System.out.println("Restaurante encontrado por gastronomia: " + foundRestaurantByCuisine.toJson());
            } else {
                System.out.println("Nenhum restaurante encontrado com esta gastronomia.");
            }
            System.out.println();
            // Pesquisando documentos por nome usando índice de texto
            Document foundRestaurantByName = collection.find(Filters.text("mar")).first();
            if (foundRestaurantByName != null) {
                System.out.println("Restaurante encontrado por nome: " + foundRestaurantByName.toJson());
            } else {
                System.out.println("Nenhum restaurante encontrado com este nome.");
            }
        }
    }
}
