package com.mycompany.app;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisDataException;
import java.util.*;

public class AtendimentoSistema {
    private final Jedis jedis;

    public AtendimentoSistema() {
        this.jedis = new Jedis(); // Configurar a conexão com o servidor Redis
    }

    public boolean atenderPedido(String username, String product, int limit, int timeslot) {
        String key = getKey(username, timeslot);
        String currentTime = String.valueOf(System.currentTimeMillis());
    
        // Verificar se o número de produtos excede o limite fora da transação
        Set<String> products = jedis.zrange(key, 0, -1);
        
        if (products.size() >= limit) {
            return false; // Mensagem de erro - limite excedido
        }
    
        try (Transaction transaction = jedis.multi()) {
            // Adicionar o pedido à lista associada ao utilizadore
            transaction.zadd(key, Double.parseDouble(currentTime), product);
    
            // Remover pedidos antigos que excedam o limite
            long cutoffTime = System.currentTimeMillis() - (timeslot * 60 * 1000);
            transaction.zremrangeByScore(key, "-inf", String.valueOf(cutoffTime - 1));
    
            // Adicionar o produto à lista de produtos do utilizadore
            String userProductsKey = "user_products:" + username;
            transaction.sadd(userProductsKey, product);
    
            // Executar a transação
            List<Object> result = transaction.exec();
    
            return result != null;
        } catch (JedisDataException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    private String getKey(String username, int timeslot) {
        long currentTimeSlot = System.currentTimeMillis() / (timeslot * 60 * 1000);
        return username + ":" + timeslot + ":" + currentTimeSlot;
    }

    public Set<String> getUtilizadoreProdutos(String username) {
        String userProductsKey = "user_products:" + username;
        return jedis.smembers(userProductsKey);
    }

    public Set<String> getUtilizadores() {
        Set<String> usernames = new HashSet<>();
        Set<String> keys = jedis.keys("*"); // Recupera todas as chaves do Redis
        for (String key : keys) {
            String[] parts = key.split(":");
            if (parts.length >= 2) {
                usernames.add(parts[1]);
            }
        }
        return usernames;
    }

    public static void main(String[] args) {
        AtendimentoSistema sistema = new AtendimentoSistema();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----------------------------------");
            System.out.println("Escolha uma opção:");
            System.out.println("(1) Ver nomes de utilizadore");
            System.out.println("(2) Ver produtos selecionados");
            System.out.println("(3) Atender pedido de Atendimento");
            System.out.println("(4) Sair");
            System.out.println("----------------------------------");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha após a leitura do número

            switch (escolha) {
                //foi feito para auxiliar na visualização dos dados
                case 1:
                    // Opção para ver nomes de utilizadore
                    Set<String> usernames = sistema.getUtilizadores();
                    System.out.println("Nomes de utilizadore: " + usernames);
                    break;
                case 2:
                    // Opção para ver produtos selecionados
                    System.out.print("Digite o nome do utilizadore: ");
                    String username = scanner.nextLine();
                    Set<String> userProducts = sistema.getUtilizadoreProdutos(username);
                    System.out.println("Produtos do utilizadore: " + userProducts);
                    break;
                case 3:
                // Opção para atend4er um pedido de atendimento
                    System.out.print("Digite o nome do utilizadore: ");
                    String usernamePedido = scanner.nextLine();

                    System.out.print("Digite o nome do produto: ");
                    String productPedido = scanner.nextLine();
                    int limitPedido = 2; // Defina o limite wdesejado para este pedido
                    int timeslotPedido = 15; // Defina o timeslot desejado para este pedido
 
                    boolean atendido = sistema.atenderPedido(usernamePedido, productPedido, limitPedido, timeslotPedido);

                    if (atendido) {
                        System.out.println("Pedido atendido com sucesso!");
                    } else {
                        System.out.println("Limite de pedidos excedido para o utilizadore ou ocorreu um erro.");
                    }
                    break;
                    
                case 4: 
                 // Opção para sair
                    System.out.println("Sair.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
