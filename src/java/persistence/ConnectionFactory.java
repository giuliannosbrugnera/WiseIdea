package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

    public static void closeConnection( Connection conn, PreparedStatement ps, ResultSet rs )
            throws DAOException {
        close(conn, ps, rs);
    }

    public static void closeConnection( Connection conn, PreparedStatement ps )
            throws DAOException {
        close(conn, ps, null);
    }

    public static void closeConnection( Connection conn )
            throws DAOException {
        close(conn, null, null);
    }

    private static void close( Connection conn, PreparedStatement ps, ResultSet rs )
            throws DAOException {
        try {
            if ( rs != null ) {
                rs.close();
            }
            if ( ps != null ) {
                ps.close();
            }
            if ( conn != null ) {
                conn.close();
            }
        }
        catch ( SQLException exception ) {
            throw new DAOException(exception.getMessage(), exception.fillInStackTrace());
        }
    }

    public static Connection getConnection() throws DAOException {
        try {
            Class.forName("org.postgresql.Driver");
            String conexao = "jdbc:postgresql://localhost/WiseIdea";//AQUI depois do localhost, nome do banco
            String usuario = "postgres", senha = "1234";//user e pass do seu pgadmin

            Connection conn = DriverManager.getConnection(conexao, usuario, senha);
            return conn;
        }
        catch ( SQLException | ClassNotFoundException exception ) {
            throw new DAOException(exception.getMessage(), exception.fillInStackTrace());
        }
    }
}
