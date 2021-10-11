package lesson08;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

import lesson08.model.ProductDto;
public class ProductDbService{
	private DbConnector dbConnector;
	public static final Logger LOG = Logger.getLogger(ProductDbService.class.getName());
	 public static final String SELECT_PRODUCTS = "SELECT p.id, p.name, p.description, p.price, p2c.product_id, p2c.category_id, c.name " +
	            "FROM products p ";
	    public static final String SELECT_PRODUCTS_BY =
	            SELECT_PRODUCTS +
	                    "JOIN product2category p2c on p2c.product_id = p.id " +
	                    "JOIN categories c on c.id = p2c.category_id ";
	    public static final String SELECT_PRODUCTS_BY_ID =
	            SELECT_PRODUCTS_BY +
	                    "WHERE p.id = ?";
	    public static final String SELECT_PRODUCTS_BY_CATEGORY =
	            SELECT_PRODUCTS_BY +
	                    "WHERE c.name = ?";

	public ProductDbService(DbConnector dbConnector) {
		super();
		this.dbConnector = dbConnector;
	}
	
	public List<ProductDto> getProducts(){
		Connection conn = dbConnector.getConnection();
		List<ProductDto> result = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		LOG.info("Starting query");
		try {
			ps = conn.prepareStatement(SELECT_PRODUCTS);
			rs = ps.executeQuery();
			while (rs.next()){
				ProductDto pd = new ProductDto();
				pd.setId(rs.getInt(1));
				pd.setName(rs.getString(2));
				pd.setDescription(rs.getString(3));
				pd.setPrice(rs.getInt(4));
				result.add(pd);
			}
			LOG.info("Query success");	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.log(Level.SEVERE,"DB Error"+e.getMessage(),e);
			try {
				rs.close();
			}
			catch(SQLException e1) {					
			}
			try {
				ps.close();
			}
			catch(SQLException e2) {					
			}
			try {
				conn.close();
			}
			catch(SQLException e3) {					
			}
			
		}
	return result;
		
	}
	
	 public ProductDto getProductById(String id) {
	        Connection conn = dbConnector.getConnection();
	        ProductDto result = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        LOG.info("Starting query");
	        try {
	            if (id != null && !id.isEmpty()) {
	                ps = conn.prepareStatement(SELECT_PRODUCTS_BY_ID);
	                ps.setString(1, id);
	                rs = ps.executeQuery();
	            }
	            while (rs != null && rs.next()) {
	                ProductDto pd = new ProductDto();
	                pd.setId(rs.getInt(1));
	                pd.setName(rs.getString(2));
	                pd.setDescription(rs.getString(3));
	                pd.setPrice(rs.getInt(4));
	                result = pd;
	            }
	            LOG.info("Query success");
	        } catch (SQLException e) {
	            LOG.log(Level.SEVERE, "DB error: " + e.getMessage(), e);
	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	            } catch (SQLException e1) {}
	            try {
	                if (ps != null) {
	                    ps.close();
	                }
	            } catch (SQLException e1) {}
	            try {
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException e1) {}
	        }
	        return result;
	    }


	public List<ProductDto> getProductsByCategory(String category){
		  Connection conn = dbConnector.getConnection();
	        List<ProductDto> result = new ArrayList<>();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        LOG.info("Starting query");
	        try {
	            if (category != null && !category.isEmpty()) {
	                ps = conn.prepareStatement(SELECT_PRODUCTS_BY_CATEGORY);
	                ps.setString(1, category);
	            } else {
	                ps = conn.prepareStatement(SELECT_PRODUCTS_BY);
	            }
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                ProductDto pd = new ProductDto();
	                pd.setId(rs.getInt(1));
	                pd.setName(rs.getString(2));
	                pd.setDescription(rs.getString(3).substring(0, 30) + "...");
	                pd.setPrice(rs.getInt(4));
	                result.add(pd);
	            }
	            LOG.info("Query success");
	        } catch (SQLException e) {
	            LOG.log(Level.SEVERE, "DB error: " + e.getMessage(), e);
	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	            } catch (SQLException e1) {}
	            try {
	                if (ps != null) {
	                    ps.close();
	                }
	            } catch (SQLException e1) {}
	            try {
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException e1) {}
	        }
	        return result;
	    }

}