package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;
import persistence.DAOException;
import persistence.ProdutoDAO;

public class BuscaProduto extends HttpServlet {

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        RequestDispatcher rd;
        String acao = request.getParameter("acao");

        // Buscar um determinado produto com base no formulário preenchido.
        switch ( acao ) {
            case "buscar":
                String name = request.getParameter("nome");
                String loteNumber = request.getParameter("numero");
                String category = request.getParameter("categoria");

                if ( name == null ) {
                    name = "";
                }

                if ( category == null ) {
                    category = "";
                }

                if ( loteNumber == null ) {
                    loteNumber = "";
                }

                try {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    ArrayList<Produto> produtoList = produtoDAO.buscarProduto(name, loteNumber, category);

                    request.setAttribute("listProduto", produtoList);
                }
                catch ( DAOException e ) {
                    e.printStackTrace();
                    rd = request.getRequestDispatcher("/viewError.jsp");
                    rd.forward(request, response);
                }

                rd = request.getRequestDispatcher("/viewProduto.jsp");
                rd.forward(request, response);
                break;
            case "vender":
                name = request.getParameter("nome");

                try {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    ArrayList<Produto> produtoList = produtoDAO.buscarProduto(name, "", "");

                    request.setAttribute("listProduto", produtoList);
                }
                catch ( DAOException e ) {
                    e.printStackTrace();
                    rd = request.getRequestDispatcher("/viewError.jsp");
                    rd.forward(request, response);
                }

                StringBuffer requestURL = request.getRequestURL();
                if ( request.getQueryString() != null ) {
                    requestURL.append("?").append(request.getQueryString());
                }
                String completeURL = requestURL.toString();
                request.setAttribute("url", completeURL);

                rd = request.getRequestDispatcher("/viewVenda.jsp");
                rd.forward(request, response);
                break;
            case "buscarlimite":
                try {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    String resultado = produtoDAO.buscarLimite();
                    response.setCharacterEncoding("UTF-8");

                    PrintWriter writer = response.getWriter();
                    writer.print(resultado);
                    writer.close();
                }
                catch ( DAOException e ) {
                    e.printStackTrace();
                    rd = request.getRequestDispatcher("/viewError.jsp");
                    rd.forward(request, response);
                }
                break;
            case "atualizarlimite":
                String novolimite = request.getParameter("novolimite");

                try {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    String resultado = produtoDAO.atualizarLimite(novolimite);
                    response.setCharacterEncoding("UTF-8");

                    PrintWriter writer = response.getWriter();
                    writer.print(resultado);
                    writer.close();
                }
                catch ( DAOException e ) {
                    e.printStackTrace();
                    rd = request.getRequestDispatcher("/viewError.jsp");
                    rd.forward(request, response);
                }
                break;
        }
    }

}
