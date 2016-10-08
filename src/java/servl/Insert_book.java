/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Book;
import uti.*;


/**
 *
 * @author leo
 */
public class Insert_book extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    private String titolo;
    private String autore;
    private String descrizione;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        Random random = new Random();
        response.setContentType("text/html;charset=UTF-8");
        int k = 0;
        if (Gestione.session_check(request)) {
            data.put("sessione", true);
            titolo = request.getParameter("titolo");
            autore = request.getParameter("autore");
            descrizione = request.getParameter("descrizione");

                try {
                    Intermedio.connect();
                    Map<String, Object> data2 = new HashMap<String, Object>();

                    data2.put("titolo", this.titolo);
                    data2.put("autore", this.autore);
                    data2.put("descrizione", this.descrizione);

                    k = Intermedio.insertRecord1("libro", data2);
                    if (k > 0) {
                        PrintWriter out = response.getWriter();
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Libro inserito!');");
                        out.println("</script>");
                        FreeMarker.process("index.jsp", data, response, getServletContext());
                    } else {
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Errore database :(');");
                        out.println("</script>");
                        FreeMarker.process("inserimento_libro.jsp", data, response, getServletContext());
                    }
                } catch (SQLException ex) {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('ERRORE database (ins), cod:" + ex.getMessage() + "');");
                    out.println("</script>");
                }

        } else {
            data.put("sessione", false);
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Entra con il tuo account per continuare');");
            out.println("</script>");
            FreeMarker.process("index.jsp", data, response, getServletContext());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Insert_book.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Insert_book.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
