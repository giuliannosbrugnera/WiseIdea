package control;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Lote;
import persistence.DAOException;
import persistence.LoteDAO;

public class BuscaLote extends HttpServlet {

    @Override
    @SuppressWarnings("null")
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        RequestDispatcher rd;
        String acao = request.getParameter("acao");

        // Listar todos os lotes cadastrados no sistema
        if ( acao.equals("listar") ) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            //get current date time with Date()
            Date todayDate = new Date();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date newerDate = null;
            try {
                newerDate = formatter.parse("01/12/2014");
            }
            catch ( ParseException ex ) {
                Logger.getLogger(BuscaLote.class.getName()).log(Level.SEVERE, null, ex);
            }

            int diffInDays = (int) ((newerDate.getTime() - todayDate.getTime()) / (1000 * 60 * 60 * 24));

            try {
                LoteDAO loteDAO = new LoteDAO();
                ArrayList<Lote> loteList = loteDAO.listarTodos(null);

                request.setAttribute("listLote", loteList);
            }
            catch ( DAOException e ) {
                rd = request.getRequestDispatcher("/viewError.jsp");
                e.printStackTrace();
                rd.forward(request, response);
            }

            rd = request.getRequestDispatcher("/viewLote.jsp");
            rd.forward(request, response);
        }
    }

}
