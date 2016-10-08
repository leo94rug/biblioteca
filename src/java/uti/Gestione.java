package uti;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import model.*;


/**
 *
 * @author leo
 */
public class Gestione extends HttpServlet {

    static HttpSession session;
    static ResultSet rs;

    /**
     *
     * @param request
     * @return
     */
    public static boolean session_check(HttpServletRequest request) {
        session = request.getSession(true);
        if (session.isNew()) {
            invalida(request);
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param request
     * @param tipo
     */
    public static void attiva_sessione(HttpServletRequest request,int type) {
        session = request.getSession(true);
        if (session.isNew()) {
            session.setAttribute("email", request.getParameter("email"));
            session.setAttribute("tipo", String.valueOf(type));

        }
    }

    /**
     *
     * @param request
     * @return
     */
    public static String getEmail(HttpServletRequest request) {
        return session.getAttribute("email").toString();
    }

    /**
     *
     * @param request
     * @return
     */
public static  int getType(HttpServletRequest request) {
        return (int) session.getAttribute("tipo");
    }
    public static void setType(int tipo) {
        String type = String.valueOf(tipo);
        session.setAttribute("tipo", type);
    }
    /**
     *
     * @param request
     */
    public static void invalida(HttpServletRequest request) {
        session.invalidate();
    }

    public static List< Book> libri_data_pub() throws Exception {
        List< Book> book = new ArrayList();
        Intermedio.connect();
        ResultSet rs = Intermedio.selectRecord("libro", "", "data_ins");
        while (rs.next()) {
            // Read values using column name
            Book libro = new Book(rs);
            book.add(libro);
        }
        return book;
    }
    public static List< Book> libri_data_pub(int page) throws Exception {
        List< Book> book = new ArrayList();
        Intermedio.connect();
        ResultSet rs = Intermedio.selectRecordp("libro", "","data_ins", page);
        while (rs.next()) {
            // Read values using column name
            Book libro = new Book(rs);
            book.add(libro);
        }
        return book;
    }
    public static Book detail_book(String isbn) throws Exception {
        Book book = null;

        Intermedio.connect();
        ResultSet rs = Intermedio.selectRecord("libro", "isbn='" + isbn + "'");
        if (rs.next()) {
            book = new Book(rs);
        }
        return book;
    }


    public static Utente utente(String email) throws Exception {
        Utente utente = null;

        Intermedio.connect();
        ResultSet rs = Intermedio.selectRecord("utente", "email='" + email + "'");
        if (rs.next()) {
            utente = new Utente(rs);
        }
        return utente;
    }
    public static boolean controllo_esistenza_login(String table, String campo, String value) throws ClassNotFoundException, SQLException, IOException, Exception {
            rs = Intermedio.selectRecord(table, campo + "='" + value + "'");
            if (rs.next()) {
                int tipo=rs.getInt("tipo");
                session.setAttribute("tipo", tipo);
                
                    return false;
            }

        return true;
    }
    /**
     *
     * @param table
     * @param campo
     * @param value
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws Exception
     */
    public static boolean controllo_esistenza(String table, String campo, String value) throws ClassNotFoundException, SQLException, IOException, Exception {
        try {
            rs = Intermedio.selectRecord(table, campo + "='" + value + "'");
            if (rs.next()) {
                    return false;
                
            }
        } catch (SQLException ex) {

        }
        return true;
    }

    /**
     *
     * @param table1
     * @param table2
     * @param joincond
     * @param campo
     * @param value
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws Exception
     */
    public static boolean controllo_esistenza(String table1, String table2, String joincond, String campo, String value) throws ClassNotFoundException, SQLException, IOException, Exception {
        try {
            rs = Intermedio.selectJoin(table1, table2, joincond, campo + "='" + value + "'");
            if (rs.next()) {
                return false;
            }
        } catch (SQLException ex) {
            SQLException eccezione = ex;
        }
        return true;
    }

    /**
     *
     * @param table1
     * @param table2
     * @param joincond
     * @param campo1
     * @param value1
     * @param campo2
     * @param value2
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws Exception
     */
    public static boolean controllo_esistenza(String table1, String table2, String joincond, String campo1, String value1, String campo2, String value2) throws ClassNotFoundException, SQLException, IOException, Exception {
            rs = Intermedio.selectJoin(table1, table2, joincond, campo1 + "='" + value1 + "' AND " + campo2 + "='" + value2 + "'");
            if (rs.next()) {
                return false;
            }

        return true;
    }

    public static int checkNumeric(String s) throws NumberFormatException {
        //convertiamo la stringa in numero, ma assicuriamoci prima che sia valida
        //convert the string to a number, ensuring its validity
        if (s != null) {
            //se la conversione fallisce, viene generata un'eccezione
            //if the conversion fails, an exception is raised
            return Integer.parseInt(s);
        } else {
            throw new NumberFormatException("String argument is null");
        }
    }
}
