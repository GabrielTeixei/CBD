package com.myLista.app;

import redis.clients.jedis.Jedis;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        
        Jedis jedis = new Jedis();
        Scanner scanner = new Scanner(System.in);
        String listaChave = "nomesLista";

        System.out.println("Lista de nomes");
        while (true) {
            System.out.println("Digite um nome (ou 'sair' para encerrar): ");
            String nome = scanner.nextLine();

            if (nome.equalsIgnoreCase("sair")) {
                break;
            }
            // Adicionar o nome à lista no Redis
            jedis.rpush(listaChave, nome);
        }

        // Obter todos os nomes da lista no Redis
        long tamanhoLista = jedis.llen(listaChave);
        System.out.println("Nomes na lista:");

        for (long i = 0; i < tamanhoLista; i++) {
            String nome = jedis.lindex(listaChave, i);
            System.out.println((i + 1) + ". " + nome);
        }

        jedis.close();
        scanner.close();
    }
}
