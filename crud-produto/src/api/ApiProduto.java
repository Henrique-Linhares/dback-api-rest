package api;

import static spark.Spark.*;

import com.google.gson.Gson;

import dao.ProdutoDAO;
import model.Produto;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Filter;

public class ApiProduto {
    private static final ProdutoDAO dao = new ProdutoDAO();
    private static final Gson gson = new Gson();
    private static final String APPLICATION_JSON = "application/json";

    public static void main(String[] args) {
        // Configura a porta da aplicação
        port(4567);

        // Cria um novo filtro
        after(new Filter() {
            @Override
            public void handle(Request request, Response response) {
                response.type(APPLICATION_JSON);
            }
        });

        // Método GET para buscar todos os produtos
        get("/produtos", new Route() {
            //Sobrescreve o método handle
            @Override
            public Object handle(Request request, Response response) {
                return gson.toJson(dao.buscarTodosProdutos());
            }
        });

        // Método GET para buscar por id
        get("/produtos/:id", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                try {
                    // Pega um pedaço da url (id) e passa para Long
                    Long id = Long.parseLong(request.params(":id"));
                    Produto produto = dao.buscarPorId(id);

                    if(produto != null) {
                        return gson.toJson(produto);
                    } else {
                        // Retorna o status de 404
                        response.status(404);
                        return "{\"Mensagem\": \"Produto com ID: " + id + " Não Encontrado!\"}";
                    }

                } catch (NumberFormatException e) {
                    response.status(400);
                    return "{\"Mensagem\": \"Formato do ID inválido:\"}";
                }
            }
        });


        // Método Post para criar novos produtos
        post("/produtos", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                try {
                    Produto novoProduto = gson.fromJson(request.body(), Produto.class);
                    dao.inserirProduto(novoProduto);
                    response.status(201);
                    return gson.toJson(novoProduto);
                } catch (Exception e) {
                    response.status(500);
                    System.out.println("Erro ao processar Requsição POST");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    return "{\"Mensagem\": \"Erro ao criar Produto\"}";
                }
            }
        });
    }

}
