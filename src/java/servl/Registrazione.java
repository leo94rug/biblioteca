package servl;

import uti.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;

public class Registrazione extends HttpServlet {

    private String email;
    private String pwd;
    private int tipo;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String professione;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        this.email = request.getParameter("email");
        this.pwd = request.getParameter("pwd");
        this.tipo = 1;
        this.nome = request.getParameter("nome");
        this.cognome = request.getParameter("cognome");
        this.indirizzo = request.getParameter("indirizzo");
        this.professione = request.getParameter("professione");
        PrintWriter out = response.getWriter();
        if (!Gestione.session_check(request)) {
            if (Gestione.controllo_esistenza("utente", "email", email)) {
                Map<String, Object> utente = new HashMap<String, Object>();
                utente.put("email", this.email);
                utente.put("pwd", this.pwd);
                utente.put("tipo", this.tipo);
                utente.put("nome", this.nome);
                utente.put("cognome", this.cognome);
                utente.put("indirizzo", this.indirizzo);
                utente.put("professione", this.professione);
                Intermedio.insertRecord1("utente", utente);
                Gestione.attiva_sessione(request,1);
                data.put("sessione", true);
                FreeMarker.process("index.jsp", data, response, getServletContext());
            } else {
                PrintWriter q = response.getWriter();
                q.println("<script type=\"text/javascript\">");
                q.println("alert('utente gia esistente');");
                q.println("</script>");
                Gestione.invalida(request);
                data.put("sessione", false);
                FreeMarker.process("index.jsp", data, response, getServletContext());
            }
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Sei già loggato');");
            out.println("</script>");
            Gestione.invalida(request);
            data.put("sessione", false);
            FreeMarker.process("index.jsp", data, response, getServletContext());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> data = new HashMap<String, Object>();
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            Gestione.invalida(request);
            data.put("sessione", false);
            FreeMarker.process("index.jsp", data, response, getServletContext());
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            Gestione.invalida(request);
            data.put("sessione", false);
            FreeMarker.process("index.jsp", data, response, getServletContext());
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            Gestione.invalida(request);
            data.put("sessione", false);
            FreeMarker.process("index.jsp", data, response, getServletContext());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> data = new HashMap<String, Object>();
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            Gestione.invalida(request);
            data.put("sessione", false);
            FreeMarker.process("index.jsp", data, response, getServletContext());
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            Gestione.invalida(request);
            data.put("sessione", false);
            FreeMarker.process("index.jsp", data, response, getServletContext());
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            Gestione.invalida(request);
            data.put("sessione", false);
            FreeMarker.process("index.jsp", data, response, getServletContext());
        }
    }

}
