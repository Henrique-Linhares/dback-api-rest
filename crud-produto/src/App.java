import java.util.Scanner;

import dao.ProdutoDAO;
import model.Produto;
import util.ConnectionFactory;

public class App {
    public static void main(String[] args) {
        // // Exercício 2
        // Produto produto = new Produto("Notebook", 3575.73, 30);
        // System.out.println(produto.toString());

        // // Exercício 3
        // try {
        //     ConnectionFactory.getConnection();
        //     System.out.println("Conexão bem sucedida!");
        // } catch (Exception e) {
        //     System.out.println(e.getMessage());
        // }

        // // Exercicio 04
        // ProdutoDAO produtoDAO = new ProdutoDAO();
        // for(Produto p: produtoDAO.buscarTodosProdutos()) {
        //     System.out.println(p.toString());
        // }

        //Exercicio 05
        // ProdutoDAO produtoDAO = new ProdutoDAO();
        // Scanner scan = new Scanner(System.in);
        // System.out.println("Digite o ID do produto: ");
        // Long id = scan.nextLong();

        // Produto produto = produtoDAO.buscarPorId(id);

        // if(produto != null) {
        //     System.out.println(produtoDAO.buscarPorId(id));
        // } else {
        //     System.out.println("Produto não encontrado!");
        // }

        // ProdutoDAO produtoDAO = new ProdutoDAO();
        // O 1L significa que você quer passar o 1 como "Long", ou seja, o L significa Long
        // Produto produto = produtoDAO.buscarPorId(1L);
        // if(produto != null) {
        //     produto.toString();
        // } else {
        //     System.out.println("Produto não encontrado!");
        // }



        // Exercicio 06
        // ProdutoDAO produtoDAO = new ProdutoDAO();
        // Scanner scan = new Scanner(System.in);

        // System.out.println("Digite o nome do produto: ");
        // String nome = scan.nextLine();

        // System.out.println("Digite o preço do produto: ");
        // Double preco = scan.nextDouble();

        // System.out.println("Digite o estoque do produto: ");
        // int estoque = scan.nextInt();

        // Produto produto = new Produto(nome, preco, estoque);

        // if(produto != null) {
        //     produtoDAO.inserirProduto(produto);
        //     System.out.println(produtoDAO.buscarPorId(produto.getId()));
        // } else {
        //     System.out.println("Erro ao criar o produto!");
        // }


        // Exercicio 07 - Atualizar
        // ProdutoDAO produtoDAO = new ProdutoDAO();
        // Scanner scan = new Scanner(System.in);
        // Produto produto = new Produto(3L, "Iphone X", 10000, 10);
        // produtoDAO.atualizarProduto(produto);


        //Exercicio 08 - Deletar
        // ProdutoDAO produtoDAO = new ProdutoDAO();
        // produtoDAO.deletarProduto(4);

    }
}
