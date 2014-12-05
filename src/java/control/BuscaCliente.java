package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import persistence.ClienteDAO;
import persistence.DAOException;

public class BuscaCliente extends HttpServlet {

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        RequestDispatcher rd;
        String acao = request.getParameter("acao");

        // Listar todos os clientes cadastrados no sistema
        switch ( acao ) {
            case "buscar":
                String name = request.getParameter("nome");
                try {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    ArrayList<Cliente> clienteList = clienteDAO.buscarCliente(name);

                    request.setAttribute("listCliente", clienteList);
                }
                catch ( DAOException e ) {
                    rd = request.getRequestDispatcher("/viewError.jsp");
                    e.printStackTrace();
                    rd.forward(request, response);
                }
                rd = request.getRequestDispatcher("/viewCliente.jsp");
                rd.forward(request, response);
                break;
            case "buscarunico":
                String clientID = request.getParameter("clientID");
                Cliente cliente = null;
                try {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    cliente = clienteDAO.buscarClientePorId(clientID);
                }
                catch ( DAOException e ) {
                    rd = request.getRequestDispatcher("/viewError.jsp");
                    e.printStackTrace();
                    rd.forward(request, response);
                }
                if ( cliente == null ) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("Cliente com CPF/CNPJ " + clientID + " não existe");
                    response.flushBuffer();
                }
                else {
                    String jsonStr = "{\"nome\": \"" + cliente.getNome() + "\", "
                            + "\"endereco\": \"" + cliente.getEndereco() + "\", "
                            + "\"identificador\": \"" + cliente.getId() + "\", "
                            + "\"telefone\": \"" + cliente.getTelefone() + "\"}";
                    System.out.println("BuscaCliente > json");
                    System.out.println("jsonStr: " + jsonStr);

                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    // Get the printwriter object from response to write the required json object to the output stream
                    PrintWriter out = response.getWriter();
                    out.print(jsonStr);
                    out.close();
                }
                break;
            case "buscartodos":
                try {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    ArrayList<Cliente> clientList = clienteDAO.buscarTodos();

                    request.setAttribute("listCliente", clientList);
                }
                catch ( DAOException e ) {
                    rd = request.getRequestDispatcher("/viewError.jsp");
                    e.printStackTrace();
                    rd.forward(request, response);
                }
                break;
        }

    }

}
