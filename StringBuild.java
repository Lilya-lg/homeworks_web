package compression;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.Response;

//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;

public class StringBuild extends HttpServlet {
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/lesson06/form03.jsp");
//		dispatcher.forward(req, resp);
		//resp.sendRedirect("/lesson06/form03.jsp");
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");		
		int lengthOfText = 1000000 + (int)(Math.random()*1000000);
	    StringBuilder sb = new StringBuilder(lengthOfText);
	    for( int i = 0; i < lengthOfText; i++ ) 
	    sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		out.write(sb.toString());

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		

	}

}
