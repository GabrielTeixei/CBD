package com.myautocomplet.app;

import redis.clients.jedis.Jedis;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;   
import java.util.Set;
import java.util.TreeSet;

public class App {
    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        Scanner scanner = new Scanner(System.in);

        // Carregue os termos do arquivo "names.txt" para o Redis
        try {
            byte[] data = Files.readAllBytes(Paths.get("names.txt"));
            String[] terms = new String(data, "UTF-8").split("\n");
            for (String term : terms) {
                jedis.zadd("autocomplete", 0, term.toLowerCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
            jedis.close();
            scanner.close();
            return;
        }

        // Solicite a entrada de pesquisa do usuário
        System.out.print("Search for ('Enter' for quit): ");
        String query = scanner.nextLine().toLowerCase();

        while (!query.isEmpty()) {
            // Use a entrada do usuário para consultar termos que começam com a entrada
            Set<String> autocompleteResults = new TreeSet<>();

            // Consultar Redis para correspondências
            autocompleteResults = jedis.zrangeByLex("autocomplete", "[" + query, "[" + query + "xff");

            for (String result : autocompleteResults) {
                System.out.println(result);
            }

            // Solicite a próxima entrada de pesquisa do usuário
            System.out.print("Search for ('Enter' for quit): ");
            query = scanner.nextLine().toLowerCase();
        }

        jedis.close();
        scanner.close();
    }
}
