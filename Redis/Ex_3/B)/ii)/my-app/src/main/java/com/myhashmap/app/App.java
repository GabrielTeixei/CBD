package com.myhashmap.app;

import redis.clients.jedis.Jedis;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Jedis jedis = new Jedis();
        Scanner scanner = new Scanner(System.in);
        String hashKey = "nomesHash";

        System.out.println("Lista de nomes");
        while (true) {
            System.out.println("Digite um nome (ou 'sair' para encerrar): ");
            String nome = scanner.nextLine();

            if (nome.equalsIgnoreCase("sair")) {
                break;
            }
            
            // Adicionar o nome ao Redis Hash
            jedis.hset(hashKey, "nome" + jedis.hlen(hashKey), nome);
        }

        // Obter todos os nomes do Redis Hash
        
        System.out.println("Nomes na lista:");
        jedis.hgetAll(hashKey).forEach((k,v)->{
            System.out.printf("%s -> %s\n", k, v);
        });
        

        jedis.close();
        scanner.close();
    }
}
