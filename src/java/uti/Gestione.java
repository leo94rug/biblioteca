package uti;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;

public class Gestione extends HttpServlet{
    static HttpSession session;
    public static boolean session_check (HttpServletRequest request){
    	session = request.getSession(true);
    	if(session.isNew()){
    	    invalida(request);
    		return false;
    	}
    	else{
    		return true;
    	}
    }
    public static void attiva_sessione(HttpServletRequest request,String tipo){
        session = request.getSession(true);
    	if(session.isNew()){
            String email=request.getParameter("email");
            session.setAttribute("email", email);
            session.setAttribute("tipo", tipo);    	
        }
    }
    public static void invalida(HttpServletRequest request){
        session.invalidate();
    }
}