package lesson08.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lesson08.DbConnector;
import lesson08.ProductDbService;
import lesson08.model.ProductDto;


//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;

public class ProductController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cat = req.getParameter("category");
		 String id = req.getParameter("id");
	        RequestDispatcher rd;
	        ProductDbService pds = new ProductDbService(DbConnector.getInstance());
	        if (id != null && !id.isEmpty()) {
	            rd = req.getRequestDispatcher("WEB-INF/views/product.jsp");
	            ProductDto product = pds.getProductById(id);
	            req.setAttribute("product", product);
	        } else {
	            rd = req.getRequestDispatcher("WEB-INF/views/products.jsp");
	            List<ProductDto> products = pds.getProductsByCategory(cat);
	            req.setAttribute("productList", products);
	        }
	        rd.forward(req, resp);
	
	}
    
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		if(Objects.equals("admin", login) && Objects.equals("123", password)) {
			HttpSession session = request.getSession();
			session.setAttribute("authorized", login);
		}
		doGet(request,resp);
	}
	

}
