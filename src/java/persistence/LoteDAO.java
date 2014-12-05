package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;
import model.Lote;
import model.Produto;

public class LoteDAO {

    private Connection conn;

    public LoteDAO() throws DAOException {
        this.conn = ConnectionFactory.getConnection();
    }

    public void closeConnection() throws DAOException {
        ConnectionFactory.closeConnection(conn);
    }

    // Retorna uma lista de todos os lotes armazenados no BD ou todos os lotes de um determinado produto.
    public ArrayList<Lote> listarTodos( String idProduto ) throws DAOException {
        ArrayList<Lote> loteList = new ArrayList<>();

        String SQL;
        SQL = "SELECT *\n"
                + "FROM lote\n";
        if ( idProduto == null ) {
            SQL += "JOIN produto\n"
                    + "ON lote.id_produto = produto.id_produto";
        }
        else {
            SQL += "WHERE qtd_prod_lote > 0 AND lote.id_produto = " + idProduto;
        }

        System.out.println("----\nVENDA");
        System.out.println("SQL: " + SQL);

        try {
            Statement statement;
            ResultSet set;

            statement = conn.createStatement();
            set = statement.executeQuery(SQL);

            while ( set.next() ) {
                Lote lote = new Lote();
                Produto produto = new Produto();

                if ( idProduto == null ) {
                    produto.setId((Integer) set.getObject("id_produto"));
                    produto.setValor((float) set.getObject("valor_produto"));
                    produto.setNome(set.getObject("nome_produto").toString());
                    produto.setCategoria(set.getObject("categoria").toString());
                    produto.setLimiteValidade((Integer) set.getObject("limite_validade"));
                }

                lote.setId((Integer) set.getObject("id_lote"));
                lote.setId_produto((Integer) set.getObject("id_produto"));
                lote.setQtdeProd((Integer) set.getObject("qtd_prod_lote"));
                lote.setValidade(tokenizer(set.getObject("validade_lote").toString()));
                lote.setDataEntrada(tokenizer(set.getObject("data_entrada_lote").toString()));
                lote.setDataFabricacao(tokenizer(set.getObject("data_fabric_lote").toString()));
                lote.setProduto(produto);

                loteList.add(lote);
            }

        }
        catch ( SQLException sqle ) {
            throw new DAOException("Erro ao buscar dados " + sqle);
        }

        return loteList;
    }

    // No BD as datas são guardadas como yyyy-mm-dd.
    // Essa função recebe a data como parâmetro e retorna uma string no formato dd/mm/yyyy
    public String tokenizer( String dateToParse ) {

        StringTokenizer st = new StringTokenizer(dateToParse, "-");
        String year = st.nextToken();
        String month = st.nextToken();
        String day = st.nextToken();

        return day + "/" + month + "/" + year;
    }
}
