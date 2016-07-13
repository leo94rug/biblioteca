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

public class Login extends HttpServlet{
	public String email;
	public String password;
	public String tipo;
        
    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, Exception{
    	        Map<String,Object> data= new HashMap<String,Object>();

        this.email = request.getParameter("email");
    	this.password = request.getParameter("password");

    	if(!Gestione.session_check(request)){
            if(controllo_utente(request,response)){
                Gestione.attiva_sessione(request,tipo);
                FreeMarker.process("index_utente_ad.html", data, response, getServletContext());
            }
            else{
                             PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Sei già non loggatozz');");
            out.println("</script>");
            Gestione.invalida(request);
            }
        } 
        else {
             PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Sei già loggato');");
            out.println("</script>");
            FreeMarker.process("login_err.html", data, response, getServletContext());
        }
    }
        public String annida(String par1,String val1,String par2,String val2, HttpServletResponse response) throws IOException{
            String uguale="=";
            String trattino="'";
            String and="' AND '";
            String query1=par1.concat(uguale);
            String query2=trattino.concat(val1);   
            String query3=and.concat(par2);
            String query4=uguale.concat(trattino);
            String query5=val2.concat(trattino);
            String query6=query1.concat(query2);
            String query7=query3.concat(query4);
            String query8=query6.concat(query7);
            String query=query8.concat(query5);
            query = par1 + " " + " = " + " ' " + val1 + " '" +" " + " AND  "+ " " + par2 +" " + " = " + " ' " + val2 + " ' "; 
            query = "email='" + val1 + "' AND password='" + val2 + "'";
            /*query += val1;/*            
            query += "' AND '";            
            query += par2;
            query += "=";    
            query += "'";    
            query += val2;    
            query += "'";  */              
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Sei 122" + query +"');");
            out.println("</script>"); 
        return query;
    }
    public boolean controllo_utente(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, Exception{ 
    try {     
        Intermedio.connect();
                            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Sei 1" + this.email + "" + this.password +"');");
            out.println("</script>"); 
        String condizione = annida("email",email.toString(),"password",password,response);
                    out.println("<script type=\"text/javascript\">");
            out.println("alert('Sei 2"+ condizione +"');");
            out.println("</script>"); 
        ResultSet rs = Intermedio.selectRecord("utenti",condizione,response);
                    this.tipo = rs.getObject(4).toString();

            out.println("<script type=\"text/javascript\">");
            out.println("alert('Sei 3');");
            out.println("</script>"); 
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Sei " + rs.getString(3).toString() + email + "');");
            out.println("</script>");        
      //  ResultSet rs = stat.executeQuery(query);
        if(rs.getString(3).equals(email)){
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Sei " + rs.getString(1).toString() +"');");
            out.println("</script>");
            this.tipo = rs.getObject(4).toString();
            return true;
        }
        else{
            return true;
        }
    } 
    catch (SQLException ex) {
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('ERRORE database, cod:" + ex.getMessage() + "');");
        out.println("</script>");
    }    
    finally {
        out.close();
    }
    return false;
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