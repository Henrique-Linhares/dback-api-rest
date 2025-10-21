package dao;

import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class ProdutoDAO {
    

    // Método de Leitura
    // READ
    public List<Produto> buscarTodos() {
        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produtos";

        try () {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    
}
