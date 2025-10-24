package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import util.ConnectionFactory;

public class ProdutoDAO {

    // =============================================
    // FIND(GET)
    // =============================================
    public List<Produto> buscarTodosProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection comm = ConnectionFactory.getConnection()) {
            // Preparando a consulta SQL
            PreparedStatement stmt = comm.prepareStatement(sql);
            // Envia a consulta SQL
            ResultSet rs = stmt.executeQuery();

            // Enquanto tiver um próximo produto ele irá iterar na lista
            while (rs.next()) {
                // Cria uma nova instância de produto
                Produto produto = new Produto(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("estoque"));

                // Adiciona na lista
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.out.printf("Erro ao buscar produtos: ", e.getMessage());
            // Passo a passo do erro
            e.printStackTrace();
        }
        return produtos;
    }

    // =============================================
    // FIND BY ID(GET)
    // =============================================

    public Produto buscarPorId(Long id) {
        // Carrega esse objeto com null caso não encontre nada na busca
        Produto produto = null;
        String sql = "SELECT id, nome, preco, estoque FROM produtos WHERE id= ?";

        try (Connection comm = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = comm.prepareStatement(sql);
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto(rs.getLong("id"),
                            rs.getString("nome"),
                            rs.getDouble("preco"),
                            rs.getInt("estoque"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o produto. ID: " + id);
            System.out.println(e.getMessage());
            // Passo a passo do erro
            e.printStackTrace();
        }

        return produto;
    }

    // =============================================
    // CREATE (POST)
    // =============================================
    public void inserirProduto(Produto produto) {
        String sql = "INSERT INTO produtos(nome, preco, estoque) VALUES(?, ?, ?)";

        try (Connection comm = ConnectionFactory.getConnection()) {
            // o Return_generated_keys retornará a chave gerada(Criará uma chave nova)
            PreparedStatement stmt = comm.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getEstoque());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    produto.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao criar o produto: " + produto.getNome());
            System.out.println(e.getMessage());
            // Passo a passo do erro
            e.printStackTrace();
        }
    }


    // =============================================
    // UPDATE (PUT)
    // =============================================

    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, estoque = ? WHERE id = ?";

        try (Connection comm = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = comm.prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getEstoque());
            stmt.setLong(4, produto.getId());

            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Produto ID: " + produto.getId() + " atualizado.");
            System.out.println("Linhas afetadas: " + linhasAfetadas);

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o produto: " + produto.getNome());
            System.out.println(e.getMessage());
            // Passo a passo do erro
            e.printStackTrace();
        }
    }


    // =============================================
    // DELETE (DELETE)
    // =============================================

    public void deletarProduto(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection comm = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = comm.prepareStatement(sql);

            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Produto Excluído");
            System.out.println("Linhas Afetadas: " + linhasAfetadas);
        } catch (Exception e) {
            System.out.println("Erro ao excluir produto com ID" + id);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
