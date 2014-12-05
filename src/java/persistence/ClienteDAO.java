package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cliente;

public class ClienteDAO {

    private final Connection conn;

    public ClienteDAO() throws DAOException {
        this.conn = ConnectionFactory.getConnection();
    }

    public void closeConnection() throws DAOException {
        ConnectionFactory.closeConnection(conn);
    }

    public ArrayList<Cliente> buscarCliente( String nome ) throws DAOException {

        ArrayList<Cliente> clienteList = new ArrayList<>();

        String SQL;
        SQL = "SELECT *\n"
                + "FROM cliente\n"
                + "WHERE cliente.nome_cliente LIKE '%" + nome + "%'";

        try {
            Statement statement;
            ResultSet set;

            statement = conn.createStatement();
            set = statement.executeQuery(SQL);

            while ( set.next() ) {
                Cliente cliente = new Cliente();

                cliente.setId(set.getObject("id_cliente").toString());
                cliente.setEndereco(set.getObject("endereco").toString());
                cliente.setNome(set.getObject("nome_cliente").toString());
                cliente.setTelefone(set.getObject("telefone_cliente").toString());

                clienteList.add(cliente);
            }

        }
        catch ( SQLException sqle ) {
            throw new DAOException("Erro ao buscar dados " + sqle);
        }

        return clienteList;

    }

    public Cliente buscarClientePorId( String clienteID ) throws DAOException {

        Cliente cliente = null;
        String SQL = "SELECT *\n"
                + "FROM cliente\n"
                + "WHERE cliente.id_cliente = '" + clienteID + "'";

        System.out.println("Cliente > buscarUnico");
        System.out.println("SQL: " + SQL);

        try {
            Statement statement;
            ResultSet set;

            statement = conn.createStatement();
            set = statement.executeQuery(SQL);

            if ( set.next() ) {
                cliente = new Cliente();

                cliente.setId(set.getObject("id_cliente").toString());
                cliente.setEndereco(set.getObject("endereco").toString());
                cliente.setNome(set.getObject("nome_cliente").toString());
                cliente.setTelefone(set.getObject("telefone_cliente").toString());
            }
            else {
                return null;
            }

        }
        catch ( SQLException sqle ) {
            throw new DAOException("Erro ao buscar dados " + sqle);
        }

        return cliente;

    }

    public ArrayList<Cliente> buscarTodos() throws DAOException {

        ArrayList<Cliente> clientList = new ArrayList<>();
        String SQL = "SELECT *\n" + "FROM cliente\n";

        System.out.println("Cliente > buscarTodos");
        System.out.println("SQL: " + SQL);

        try {
            Statement statement;
            ResultSet set;

            statement = conn.createStatement();
            set = statement.executeQuery(SQL);

            while ( set.next() ) {
                Cliente cliente = new Cliente();

                cliente.setId(set.getObject("id_cliente").toString());
                cliente.setEndereco(set.getObject("endereco").toString());
                cliente.setNome(set.getObject("nome_cliente").toString());
                cliente.setTelefone(set.getObject("telefone_cliente").toString());

                clientList.add(cliente);
            }

        }
        catch ( SQLException sqle ) {
            throw new DAOException("Erro ao buscar dados " + sqle);
        }

        return clientList;

    }

}
