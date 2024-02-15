package com.atendiment;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;




public class SistemaDeAtendimento {
    private static final int LIMIT = 30; // Limite máximo de produtos por usuário
    private static final int TIMESLOT = 60; // Janela temporal em minutos

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> pedidosCollection;
 
    public SistemaDeAtendimento() {
        this.mongoClient = MongoClients.create("mongodb://localhost:27017");
        this.database = mongoClient.getDatabase("atendimento");
        this.pedidosCollection = database.getCollection("pedidos");
    }

    public void fazerPedido(String username, String produto) {
        // Verifica se o usuário já fez pedidos dentro da janela temporal
        long count = pedidosCollection.countDocuments(
            Filters.and(                
                Filters.eq("username", username),
                Filters.gte("timestamp", System.currentTimeMillis() - (TIMESLOT * 1 * 1000))
            )
        );

        // Se o usuário já atingiu o limite, produz uma mensagem de erro
        if (count >= LIMIT) {
            System.out.println("Limite de pedidos excedido para o usuário " + username);
            return;
        }

        // Registra o pedido na coleção
        Document pedido = new Document("username", username)
            .append("product", produto)
            .append("timestamp", System.currentTimeMillis());
        pedidosCollection.insertOne(pedido);

        System.out.println("Pedido registrado para o usuário " + username + ": " + produto);
    }

    public static void main(String[] args) {
        SistemaDeAtendimento sistema = new SistemaDeAtendimento();
        // Exemplos de pedidos
        sistema.fazerPedido("teste_1", "pera");
        sistema.fazerPedido("Predo", "melao");
        sistema.fazerPedido("ROma", "arrol");
        sistema.fazerPedido("ze", "tijolo"); // Deve exceder o limite


        sistema.close();
    }
}
