package servl;

import uti.*;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import model.Book;

public class Login extends HttpServlet {

    public String email;
    public String password;
    public int tipo;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, Exception {
        Map< String, Object> data = new HashMap< String, Object>();
        ResultSet rs;
        PrintWriter out = response.getWriter();
        email = request.getParameter("email");
        password = request.getParameter("password");

        if (!Gestione.session_check(request)) {
            rs = Intermedio.selectRecord("utente", "email='" + email + "'");
            if (rs.next()) {
                tipo = rs.getInt("tipo");
                Gestione.attiva_sessione(request, tipo);
                data.put("sessione", true);
                FreeMarker.process("index.jsp", data, response, getServletContext());
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Email o password errati');");
                out.println("</script>");
                Gestione.invalida(request);
                FreeMarker.process("index.jsp", data, response, getServletContext());
            }
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Sei già loggato');");
            out.println("</script>");
            Gestione.invalida(request);
            FreeMarker.process("index.jsp", data, response, getServletContext());
        }
    }

    @
            Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map< String, Object> data = new HashMap< String, Object>();

        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            data.put("sessione", false);
            Gestione.invalida(request);
            FreeMarker.process("index.jsp", data, response, getServletContext());
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            data.put("sessione", false);
            Gestione.invalida(request);
            FreeMarker.process("index.jsp", data, response, getServletContext());
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            data.put("sessione", false);
            Gestione.invalida(request);
            FreeMarker.process("index.jsp", data, response, getServletContext());
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @
            Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map< String, Object> data = new HashMap< String, Object>();
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            data.put("sessione", false);
            Gestione.invalida(request);
            FreeMarker.process("index.jsp", data, response, getServletContext());
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            data.put("sessione", false);
            Gestione.invalida(request);
            FreeMarker.process("index.jsp", data, response, getServletContext());
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            data.put("sessione", false);
            Gestione.invalida(request);
            FreeMarker.process("index.jsp", data, response, getServletContext());
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
