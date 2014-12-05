package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.StringTokenizer;
import model.Cliente;
import model.Lote;
import model.Produto;

public class ProdutoDAO {

    private final Connection conn;
    public String report;

    public ProdutoDAO() throws DAOException {
        this.conn = ConnectionFactory.getConnection();
    }

    public void closeConnection() throws DAOException {
        ConnectionFactory.closeConnection(conn);
    }

    // Retorna uma lista de todos os lotes armazenados no BD.
    public ArrayList<Produto> buscarProduto( String name, String loteNumber, String category ) throws DAOException {

        ArrayList<Produto> produtoList = new ArrayList<>();
        String SQL = "SELECT *\n" + "FROM produto\n WHERE ";
        SQL += "produto.nome_produto LIKE '%" + name + "%' ";

        if ( !loteNumber.isEmpty() ) {
            SQL += "AND produto.id_produto = (\n"
                    + "SELECT lote.id_produto\n"
                    + "FROM lote\n"
                    + "WHERE lote.id_lote = " + loteNumber + ") ";
        }

        if ( !category.isEmpty() ) {
            SQL += "AND produto.categoria LIKE '%" + category + "%'";
        }

        System.out.println("SQL: " + SQL);

        try {
            Statement statement;
            ResultSet set;

            statement = conn.createStatement();
            set = statement.executeQuery(SQL);

            while ( set.next() ) {
                Produto produto = new Produto();

                produto.setId((Integer) set.getObject("id_produto"));
                produto.setValor((float) set.getObject("valor_produto"));
                produto.setNome(set.getObject("nome_produto").toString());
                produto.setCategoria(set.getObject("categoria").toString());
                produto.setLimiteValidade((Integer) set.getObject("limite_validade"));

                produtoList.add(produto);
            }

        }
        catch ( SQLException sqle ) {
            throw new DAOException("Erro ao buscar dados " + sqle);
        }

        return produtoList;

    }

    public Produto buscarProdutoPorId( String productID ) throws DAOException {

        Produto produto = new Produto();
        String SQL = "SELECT *\n" + "FROM produto\n" + "WHERE id_produto = " + productID;

        System.out.println("SQL: " + SQL);

        try {
            Statement statement;
            ResultSet set;

            statement = conn.createStatement();
            set = statement.executeQuery(SQL);

            if ( set.next() ) {
                produto.setId((Integer) set.getObject("id_produto"));
                produto.setValor((float) set.getObject("valor_produto"));
                produto.setNome(set.getObject("nome_produto").toString());
                produto.setCategoria(set.getObject("categoria").toString());
                produto.setLimiteValidade((Integer) set.getObject("limite_validade"));
            }

            SQL = "SELECT qtde\n" + "FROM\n" + "(SELECT SUM(qtd_prod_lote) AS qtde, id_produto\n"
                    + "FROM LOTE\n"
                    + "GROUP BY id_produto\n"
                    + ") AS tmp\n"
                    + "WHERE tmp.id_produto = " + productID;

            set = statement.executeQuery(SQL);

            if ( set.next() ) {
                produto.setQuantidadeEmEstoque(((Long) set.getObject("qtde")).intValue());
            }

        }
        catch ( SQLException sqle ) {
            throw new DAOException("Erro ao buscar dados " + sqle);
        }

        return produto;

    }

    public boolean venderProduto( String productID, String number, String escolha, String clientID, String[] idLotes ) throws DAOException, ParseException {

        LinkedList<Lote> loteList = new LinkedList<>();
        Integer qtdeVenda, qtdeVendaInicial;
        qtdeVenda = qtdeVendaInicial = Integer.parseInt(number);
        String SQL = "SELECT *\n" + "FROM lote\n"
                + "WHERE qtd_prod_lote > 0 AND id_produto = " + productID;

        if ( escolha.equals("manual") ) {
            SQL += " AND (";
            for ( String id : idLotes ) {
                SQL += "id_lote = " + id;

                if ( !id.equals(idLotes[idLotes.length - 1]) ) {
                    SQL += " OR ";
                }
            }
            SQL += ")";
        }

        SQL += "\nORDER BY data_entrada_lote, validade_lote";
        Integer qtdeEmEstoque;
        String dataValidade;

        System.out.println("* * *\nProdutoDAO > venderProduto\nSQL: " + SQL);

        ClienteDAO clienteDAO = new ClienteDAO();

        // Necessários para a geração do relatório.
        Produto produto = buscarProdutoPorId(productID);
        Cliente cliente = clienteDAO.buscarClientePorId(clientID);

        try {
            Statement statement;
            ResultSet set;

            statement = conn.createStatement();
            set = statement.executeQuery(SQL);

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            //get current date time with Date()
            Date todayDate = new Date();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date newerDate;

            String newerDateForDB = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

            // Guarda todos os lotes que possuem tal produto.
            // Só não guarda os lotes vencidos.
            while ( set.next() ) {
                Lote lote = new Lote();
                dataValidade = tokenizer(set.getObject("validade_lote").toString());

                // Verifica se o lote está vencido
                newerDate = formatter.parse(dataValidade);
                int tempoRestante = (int) ((newerDate.getTime() - todayDate.getTime()) / (1000 * 60 * 60 * 24));

                // Somente adiciona ao array de lotes os lotes que:
                // - não estão vencidos;
                // - o tempo restanta é maior ou igual ao limite de dias para venda.
                if ( tempoRestante >= 0 && tempoRestante >= produto.getLimiteValidade() ) {
                    // Montando o array de lotes.
                    lote.setId((Integer) set.getObject("id_lote"));
                    lote.setId_produto((Integer) set.getObject("id_produto"));
                    lote.setQtdeProd((Integer) set.getObject("qtd_prod_lote"));
                    lote.setValidade(tokenizer(set.getObject("validade_lote").toString()));
                    lote.setDataEntrada(tokenizer(set.getObject("data_entrada_lote").toString()));
                    lote.setDataFabricacao(tokenizer(set.getObject("data_fabric_lote").toString()));
                    loteList.add(lote);
                }
            }

            // Se não adicionou nenhum lote ao array de lotes, então todos estão vencidos.
            if ( loteList.isEmpty() ) {
                return false;
            }

            // Array de lotes possui lotes. Necessário verificar a escolha do usuário.
            report = "<h2>Relatório da venda</h2><p>O ";
            report += escolha.equals("automatico") ? "sistema" : "usuário";
            report += " escolheu os seguintes lotes para a retirada dos produtos</p><br>";

            Lote updateLote;
            while ( (updateLote = loteList.pollFirst()) != null ) {
                // report += updateLote.reportData();
                qtdeEmEstoque = updateLote.getQtdeProd();
                // Verifica se o lote possui produtos suficientes.
                if ( qtdeEmEstoque >= qtdeVenda ) {
                    String novaQtdeEstoque = Integer.toString(qtdeEmEstoque - qtdeVenda);
                    SQL = "UPDATE lote\n" + "SET qtd_prod_lote = " + novaQtdeEstoque + "\n"
                            + "WHERE id_lote = " + updateLote.getId();
                    report += "<p>Lote com código <code>" + updateLote.getId() + "</code></p>";
                    report += "<p>Quantidade atualizada: ";
                    if ( Integer.parseInt(novaQtdeEstoque) > 0 ) {
                        report += "<code>" + novaQtdeEstoque + "</code> produtos.</p>" + "<hr>";
                    }
                    else {
                        report += "o lote está <strong>vazio</strong>.</p>" + "<hr>";
                    }
                    System.out.println("Lote possui qtde suficiente!!!\nSQL:\n" + SQL);
                    // Atualiza a quantidade de produtos do lote.
                    statement.executeUpdate(SQL);
                    break;
                }
                else {
                    SQL = "UPDATE lote\n" + "SET qtd_prod_lote = 0\n" + "WHERE id_lote = " + updateLote.getId();
                    report += "<p>Lote com código <code>" + updateLote.getId() + "</code></p>";
                    report += "<p>Quantidade atualizada: o lote está <strong>vazio</strong>.</p>" + "<hr>";
                    System.out.println("Lote NÃO possui qtde suficiente :(\nSQL:\n" + SQL);
                    statement.executeUpdate(SQL);
                    qtdeVenda = qtdeVenda - qtdeEmEstoque;
                }
            }

            SQL = "INSERT INTO venda (id_cliente, data_venda, valor_venda, qtd_prod_venda) ";
            SQL += "VALUES ('" + cliente.getId() + "', '" + newerDateForDB + "', " + produto.getValor() * qtdeVendaInicial;
            SQL += ", " + qtdeVendaInicial + ");";

            report += "<p>Cliente: <strong>" + cliente.getNome() + "</strong>";
            report += "<p>Data da venda: <strong>" + new SimpleDateFormat("EEEEEEEEEEEEE, dd MMMMMMMMMMM yyyy, HH:mm:ss").format(new Date()) + "</strong>";
            report += "<p>Valor da venda: <strong>R$ " + produto.getValor() * qtdeVendaInicial + "0</strong>";
            report += "<p>Quantidade de produtos: <strong>" + qtdeVendaInicial + "</strong>";

            System.out.println("Inserindo na venda:\n" + SQL);
            // Atualiza a tabela venda.
            statement.executeUpdate(SQL);
        }
        catch ( SQLException sqle ) {
            throw new DAOException("Erro ao buscar dados " + sqle);
        }

        return true;

    }

    public String buscarLimite() throws DAOException {

        String SQL = "SELECT limite_validade\nFROM produto\nLIMIT 1";
        String resultado = null;

        try {
            Statement statement;
            ResultSet set;

            statement = conn.createStatement();
            set = statement.executeQuery(SQL);

            if ( set.next() ) {
                resultado = set.getObject("limite_validade").toString();
            }
        }
        catch ( SQLException sqle ) {
            throw new DAOException("Erro ao buscar dados " + sqle);
        }

        return resultado;

    }

    public String atualizarLimite( String novoLimite ) throws DAOException {

        String SQL = "UPDATE produto\nSET limite_validade = " + novoLimite;

        try {
            Statement statement;
            ResultSet set;

            statement = conn.createStatement();
            statement.executeUpdate(SQL);
        }
        catch ( SQLException sqle ) {
            throw new DAOException("Erro ao buscar dados " + sqle);
        }

        return "<p>Novo limite de dias para vender um produto: <code>" + novoLimite + "</code></p>";

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

    public String getReport() {
        return this.report;
    }

}
