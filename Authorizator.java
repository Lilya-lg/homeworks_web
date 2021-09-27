package ua.itea;

public class Authorizator {
    public String isAuthorized(String login, String password) {
        DbExecutor de = new DbExecutor();
        return de.checkLogin(login, password);
    }
    
    public static void main(String []args) {
    	DbExecutor de = new DbExecutor();
    	System.out.println(de.checkLogin("luser","123"));
    }
    
}
