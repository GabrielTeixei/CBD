package com.atend_B;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class App {
    private static final int MAX_TOTAL_QUANTITY = 30; // Limite máximo de unidades de produtos por usuário por janela temporal
    private static final int TIMESLOT = 60 * 60 * 1000; // Janela temporal em milissegundos (60 minutos)

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> pedidosCollection;

    public App() {
        this.mongoClient = MongoClients.create("mongodb://localhost:27017");
        this.database = mongoClient.getDatabase("atendimento");
        this.pedidosCollection = database.getCollection("pedidos");
    }

    public void fazerPedido(String username, String produto, int quantidade) {
        // Verifica o total de unidades de produto solicitadas pelo usuário dentro da janela temporal
        long totalQuantity = pedidosCollection.countDocuments(
            Filters.and(                
                Filters.eq("username", username),
                Filters.gte("timestamp", System.currentTimeMillis() - TIMESLOT)
            )
        );
        
        // Se o usuário já atingiu o limite de quantidade, produz uma mensagem de erro
        if (totalQuantity + quantidade > MAX_TOTAL_QUANTITY) {
            System.out.println("");
            System.out.println("Limite de quantidade excedido para o usuário " + username);
            System.out.println("");
            return;
        }

        // Registra o pedido na coleção
        Document pedido = new Document("username", username)
            .append("product", produto)
            .append("quantity", quantidade)
            .append("timestamp", System.currentTimeMillis());
        pedidosCollection.insertOne(pedido);
        System.out.println("");
        System.out.println("Pedido registrado para o usuário " + username + ": " + quantidade + " unidades de " + produto);
        System.out.println("");
    }

    public static void main(String[] args) {
        App sistema = new App();

        // Exemplos de pedidos
        sistema.fazerPedido("Teste", "sopa", 40);
        sistema.fazerPedido("Alex", "H20", 15);
        sistema.fazerPedido("Tomas", "TOmato", 8);
        sistema.fazerPedido("Eva", "gomas", 20); // Deve exceder o limite

        // Fechar a conexão com o MongoDB ao finalizar
        sistema.mongoClient.close();
    }
}
