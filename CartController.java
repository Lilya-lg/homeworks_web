package lesson08.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

public class CartController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 HttpSession session = req.getSession();
	        	RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/cart.jsp");
	        	 Object productsCartMap = session.getAttribute("productsCartMap");
	            if(productsCartMap == null) {
	            	productsCartMap = new HashMap<ProductDto,Integer>();
	            }
	
	            req.setAttribute("productMap", productsCartMap);
	            rd.forward(req, resp);
	        }
	       
	

    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		 String id = request.getParameter("buy");
		 String idRemove = request.getParameter("remove"); 
		 String quantity = request.getParameter("quantity");
		 String qntRemoveStr = request.getParameter("qntRemove");
		int counter = 0;
		int total = 0;

		 HttpSession session = request.getSession();
	      //  RequestDispatcher rd;
	        ProductDbService pds = new ProductDbService(DbConnector.getInstance());
	        if (id != null && !id.isEmpty()) {
	         //   rd = req.getRequestDispatcher("WEB-INF/views/product.jsp");
	        	int quant = Integer.parseInt(quantity);
	            ProductDto product = pds.getProductById(id);
	            Object productsCartMap = session.getAttribute("productsCartMap");
	            if(productsCartMap == null) {
	            	 productsCartMap = new HashMap<ProductDto,Integer>();
	           
	            }
	            Integer count = ((HashMap<ProductDto,Integer>)productsCartMap).get(product);
	            if(count==null) {
	            ((HashMap<ProductDto,Integer>)productsCartMap).put(product,quant);
	            }
	            else {
	            	((HashMap<ProductDto,Integer>)productsCartMap).put(product,count+quant);	
	            }
	            for (Integer tot :((HashMap<ProductDto,Integer>) productsCartMap).values()) {
	                total += tot;
	            }
	            session.setAttribute("productsCartMapSize",total);
	            session.setAttribute("productsCartMap",productsCartMap);
	            session.setAttribute("productsCartListSize", ((HashMap<ProductDto,Integer>) productsCartMap).entrySet().size());
	        }
	        if (idRemove != null && !idRemove.isEmpty()) {
		         //   rd = req.getRequestDispatcher("WEB-INF/views/product.jsp");
	        		
        	   		 int qntRemove = Integer.parseInt(qntRemoveStr);
        	   		
		            ProductDto product = pds.getProductById(idRemove);
		            Object productsCartMap = session.getAttribute("productsCartMap");
		            if(productsCartMap == null) {
		            }
		            else {
		            Integer count = ((HashMap<ProductDto,Integer>)productsCartMap).get(product);
		            if(count==null) {
		            }
		            else {
		            	((HashMap<ProductDto,Integer>)productsCartMap).put(product,count-qntRemove);	
		            }
		            for (Integer tot :((HashMap<ProductDto,Integer>) productsCartMap).values()) {
		                total += tot;
		            }
		            session.setAttribute("productsCartMap",productsCartMap);
		            session.setAttribute("productsCartMapSize",total);
		            session.setAttribute("productsCartListSize", ((HashMap<ProductDto,Integer>) productsCartMap).entrySet().size());
		            }
		        }
	            resp.sendRedirect("products");
	
	}
}
