package com.mycompany.app;

import redis.clients.jedis.Jedis;
import java.util.Scanner;
import java.util.Set;

public class AtendimentoSistema {
    private static final String PREFIXO_CHAVE = "PEDIDOS:";
    private static final String MENSAGEM_ERRO = "Limite máximo de pedidos excedido para este período";
    private Jedis jedis = new Jedis();
    public int LIMITE_TOTAL;
    public int JANELA_DE_TEMPO;

    public AtendimentoSistema(int LIMITE_TOTAL, int JANELA_DE_TEMPO) {
        this.LIMITE_TOTAL = LIMITE_TOTAL;
        this.JANELA_DE_TEMPO = JANELA_DE_TEMPO;
    }

    public void listarPedidosUsuario(String username) {
        String chave = PREFIXO_CHAVE + username;
        Set<String> produtos = jedis.zrange(chave, 0, -1);

        System.out.println("Lista de pedidos para o usuário " + username + ":");
        for (String item : produtos) {
            System.out.println(item);
        }
    }

    public void fazerPedido(String username, String produto, int quantidade) {
        long currentTime = System.currentTimeMillis() / 1000;
        String chave = PREFIXO_CHAVE + username;

        long quantidadePedidos = jedis.zcount(chave, currentTime - JANELA_DE_TEMPO, currentTime);

        Set<String> listaProdutos = jedis.zrange(chave, 0, -1);
        int quantidadeTotal = 0;
        for (String item : listaProdutos) {
            String[] partes = item.split(":");
            if (partes.length == 2) {
                int quantidadeItem = Integer.parseInt(partes[1]);
                quantidadeTotal += quantidadeItem;
            }
        }

        if (quantidadePedidos >= LIMITE_TOTAL || quantidadeTotal + quantidade > LIMITE_TOTAL) {
            System.out.println(MENSAGEM_ERRO);
            return;
        }

        jedis.zadd(chave, currentTime, produto + ":" + quantidade);
        System.out.println(String.format("Pedido realizado com sucesso! > %s -> %s x %d", username, produto, quantidade));
    }

    public static void main(String[] args) {
        int LIMITE_TOTAL = 4;
        int JANELA_DE_TEMPO = 10;

        AtendimentoSistema sistemaAtendimento = new AtendimentoSistema(LIMITE_TOTAL, JANELA_DE_TEMPO);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu nome de usuário: ");
        String username = scanner.nextLine();

        while (true) {
            System.out.print("Digite o nome do produto (ou 'sair' para encerrar): ");
            String produto = scanner.nextLine();

            if (produto.equalsIgnoreCase("sair")) {
                break;
            }

            System.out.print("Digite a quantidade desejada: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();

            sistemaAtendimento.fazerPedido(username, produto, quantidade);

            System.out.println("Lista de produtos:");
            sistemaAtendimento.listarPedidosUsuario(username);
        }
    }
}
