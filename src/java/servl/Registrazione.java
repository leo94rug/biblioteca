package servl;


import uti.*;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;

public class Registrazione extends HttpServlet{
	public String email;
	public String password;
	public String tipo;
        
    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, Exception{
    	Map<String,Object> data= new HashMap<String,Object>();
        this.email = request.getParameter("email");
    	this.password = request.getParameter("password");
        PrintWriter out = response.getWriter();
    	if(!Gestione.session_check(request)){
            if(registra_utente(request,response)){
                Gestione.attiva_sessione(request,tipo);
                FreeMarker.process("index_registrazione.html", data, response, getServletContext());
            }
            else{   
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Errore nella registrazione');");
            out.println("</script>");
            Gestione.invalida(request);
            }
        } 
        else {
      //       PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Sei già loggato');");
            out.println("</script>");
            FreeMarker.process("login_err.html", data, response, getServletContext());
        }
    }

    public boolean registra_utente(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, Exception{ 
    try {     
        Intermedio.connect();
        if(controllo_utente_esiste(request,response)){
            Map<String,Object> data= new HashMap<String,Object>();
            data.put("email",this.email);
            data.put("password",this.password);
            data.put("id_gruppo","1");           
           int k=Intermedio.insertRecord1("utenti",data,response);
            if(k>0){
                return true;
            }
        }
        else{
            PrintWriter q = response.getWriter();
            q.println("<script type=\"text/javascript\">");
            q.println("alert('utente gia esistente');");
            q.println("</script>");
        }
    } 
    catch (SQLException ex) {
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('ERRORE database (reg_ut), cod:" + ex.getMessage() + "');");
        out.println("</script>");
    }    
    finally {
        out.close();
    }
    return false;
    }
    protected boolean controllo_utente_esiste(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, Exception{ 
    try {     
        Intermedio.connect();
        ResultSet rs=Intermedio.selectRecord("utenti","email='" + this.email + "'");
        if(rs.next()){           
            if(rs.getString(3).equals(this.email)){
                return false;
            }
        }
    } 
    catch (SQLException ex) {
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('ERRORE database(contr_ut), cod:" + ex.getMessage() + "');");
        out.println("</script>");
    }    
    finally {
        out.close();
    }
    return true;
    }

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}