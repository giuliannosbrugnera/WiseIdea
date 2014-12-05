package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Lote;
import model.Produto;
import persistence.ClienteDAO;
import persistence.DAOException;
import persistence.LoteDAO;
import persistence.ProdutoDAO;

public class VendaProduto extends HttpServlet {

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        RequestDispatcher rd;
        String acao = request.getParameter("acao");

        switch ( acao ) {
            case "buscar": {
                String productID = request.getParameter("productID");
                // Busca o produto com id 'productID' para retornar todas as suas informações
                try {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    Produto produto = produtoDAO.buscarProdutoPorId(productID);

                    request.setAttribute("produto", produto);
                }
                catch ( DAOException e ) {
                    e.printStackTrace();
                    rd = request.getRequestDispatcher("/viewError.jsp");
                    rd.forward(request, response);
                }       // Busca todos os lotes que possuem esse produto, para caso o usuário
                // queira escolher os lotes manualmente.
                ArrayList<Lote> loteList;
                try {
                    LoteDAO loteDAO = new LoteDAO();
                    loteList = loteDAO.listarTodos(productID);

                    request.setAttribute("listLote", loteList);
                }
                catch ( DAOException e ) {
                    rd = request.getRequestDispatcher("/viewError.jsp");
                    e.printStackTrace();
                    rd.forward(request, response);
                }

                ArrayList<Cliente> clientList;
                try {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    clientList = clienteDAO.buscarTodos();

                    request.setAttribute("listCliente", clientList);
                }
                catch ( DAOException e ) {
                    rd = request.getRequestDispatcher("/viewError.jsp");
                    e.printStackTrace();
                    rd.forward(request, response);
                }

                String completeURL = request.getParameter("url");
                request.setAttribute("url", completeURL + "&acao=vender");

                rd = request.getRequestDispatcher("/viewProdutoVenda.jsp");
                rd.forward(request, response);
                break;
            }
            case "vender": {
                String productID = request.getParameter("productID");
                String qtdeVenda = request.getParameter("numero");
                String escolha = request.getParameter("escolha");
                String clientID = request.getParameter("clientID");
                String[] values = null;
                System.out.println("* * *\nVendaProduto > Dados");
                System.out.println("ID do produto: " + productID);
                System.out.println("ID do cliente: " + clientID);
                System.out.println("Quantidade: " + qtdeVenda);
                System.out.println("Escolha dos lotes: " + escolha);
                if ( escolha.equals("manual") ) {
                    values = request.getParameterValues("checkBoxGroup[]");
                    System.out.println("IDs dos lotes:");
                    for ( String s : values ) {
                        System.out.println(s);
                    }
                }
                try {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    if ( !produtoDAO.venderProduto(productID, qtdeVenda, escolha, clientID, values) ) {
                        rd = request.getRequestDispatcher("/viewProdutoVencido.jsp");
                        rd.forward(request, response);
                    }

                    String resultado = produtoDAO.getReport();
                    response.setCharacterEncoding("UTF-8");

                    PrintWriter writer = response.getWriter();
                    writer.print(resultado);
                    writer.close();
                }
                catch ( DAOException | ParseException e ) {
                    e.printStackTrace();
                    rd = request.getRequestDispatcher("/viewError.jsp");
                    rd.forward(request, response);
                }
                break;
            }
        }
    }

}
