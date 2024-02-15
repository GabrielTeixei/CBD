package com.mycompany.app;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantService {
    private MongoCollection<Document> collection;

    public RestaurantService() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("cbd");
        collection = database.getCollection("restaurants");
    }

    public int countLocalidades() {
        // Count the number of distinct localidades
        long count = collection.distinct("localidade", String.class).into(new ArrayList<>()).size();
        return (int) count;
    }

    public Map<String, Integer> countRestByLocalidade() {
        // Count the number of restaurants in each locality and return the results as a map
        Map<String, Integer> restaurantCountByLocalidade = new HashMap<>();

        List<String> localidades = collection.distinct("localidade", String.class).into(new ArrayList<>());
        for (String localidade : localidades) {
            long count = collection.countDocuments(Filters.eq("localidade", localidade));
            restaurantCountByLocalidade.put(localidade, (int) count);
        }

        return restaurantCountByLocalidade;
    }

    public List<String> getRestWithNameCloserTo(String name) {
        // Retrieve restaurant names containing a specified substring in their name
        List<String> matchingNames = new ArrayList<>();
        
        // Create a filter to find restaurants with names containing the specified substring
        Document filter = new Document("nome", new Document("$regex", name));
        List<Document> restaurants = collection.find(filter).into(new ArrayList<>());
        for (Document restaurant : restaurants) {
            matchingNames.add(restaurant.getString("nome"));
        }

        return matchingNames;
    }

    public static void main(String[] args) {
        RestaurantService restaurantService = new RestaurantService();
        
        int localidadesCount = restaurantService.countLocalidades();
        System.out.println("Number of distinct localidades: " + localidadesCount);

           
        System.out.println();
        System.out.println("--------------------------------------------------");

        Map<String, Integer> restaurantCountByLocalidade = restaurantService.countRestByLocalidade();
        System.out.println("Number of restaurants by localidade:");
        for (Map.Entry<String, Integer> entry : restaurantCountByLocalidade.entrySet()) {
            System.out.println("-> " + entry.getKey() + " - " + entry.getValue());
        }


          
        System.out.println();
        System.out.println("--------------------------------------------------");

        List<String> matchingNames = restaurantService.getRestWithNameCloserTo("Park");
        System.out.println("Restaurant names containing 'Park' in the name:");
        for (String name : matchingNames) {
            System.out.println("-> " + name);
        }
    }
}
