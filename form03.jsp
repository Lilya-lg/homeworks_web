<%@ page import="ua.itea.DbExecutor"%>
<a href="withauthorizator.jsp">Authorization</a> | <a href="form03.jsp">Registration</a>
<center>
<table>
<form action="" method="post">
    <tr><td>Name:</td><td><input type="textarea" name="name" value="<%=(request.getParameter("name") != null)?request.getParameter("name"):""%>"/></td></tr>
    <tr><td>Email:</td><td><input type="email" name="login" value="<%=(request.getParameter("login") != null)?request.getParameter("login"):""%>"/></td></tr>
    <tr><td>Password:</td><td><input type="password" name="password" value="<%=(request.getParameter("password") != null)?request.getParameter("password"):""%>"/></td></tr>
    <tr><td>Repeat password:</td><td><input type="password" name="passwordRepeat" value="<%=(request.getParameter("passwordRepeat") != null)?request.getParameter("passwordRepeat"):""%>"/></td></tr>
    <tr><td>Gender:</td><td>M<input type="radio" name="gender" value="M" <%=("M".equals(request.getParameter("gender")))?" checked=\"true\"":""%>/>F<input type="radio" name="gender" value="F" <%=("F".equals(request.getParameter("gender")))?" checked=\"true\"":""%>/></td></tr>
    <tr><td>Region:</td><td><select name="region">
        <option value="DNR" <%=("DNR".equals(request.getParameter("region")))?" selected=\"true\"":""%>>ДНР</option>
        <option value="LNR" <%=("LNR".equals(request.getParameter("region")))?" selected=\"true\"":""%>>ЛНР</option>
        <option value="Crimea" <%=("Crimea".equals(request.getParameter("region")))?" selected=\"true\"":""%>>Крым</option>
    </select></td></tr>
    <tr><td>Comment:</td><td><textarea rows=10 cols=20 name="comment"><%=(request.getParameter("comment") != null)?request.getParameter("comment"):""%></textarea></td></tr>
    <tr><td>I agree to install an Amigo Browser:</td><td><input type="checkbox" name="browser" checked="true"/></td></tr>
    <tr><td> </td><td><input type="submit" value="SEND"/></td></tr>
</form>
</table>
</center>
<%
    boolean isError = false;
    String errorText = "<ul>";
	String name = request.getParameter("name");
    String login = request.getParameter("login");
    String password = request.getParameter("password");
    String passwordRepeat = request.getParameter("passwordRepeat");
    String gender = request.getParameter("gender");
    String region = request.getParameter("region");
    String comment = request.getParameter("comment");
    String browser = request.getParameter("browser");
  
	if(login!= null && !login.isEmpty()){
	if (!login.matches("^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}")){
		isError = true;
        errorText += "<li>Please check your email!</li>";
	}	
	}
	if(password != null && !password.isEmpty()){
	if (!password.equals(passwordRepeat)){
		 isError = true;
		 errorText += "<li>Repeat password correctly!</li>";
	}
	if (!password.matches("^(?=.*[0-9])(?=.*[A-Z]).{8,}$")){
		 isError = true;
		 errorText += "<li>Password must contain 8 digits, 1 number and 1 capital letter</li>";
	}
	}	
	if(login == null || login.isEmpty() || password == null || password.isEmpty()|| passwordRepeat == null || passwordRepeat.isEmpty()
		|| gender == null || gender.isEmpty() || comment == null || comment.isEmpty()|| name == null || name.isEmpty()) {
		  isError = true;
          errorText += "<li>All fields are mandatory</li>";
		}
	else if (browser == null ){
		  isError = true;
          errorText += "<li>Check Amigo</li>";
	}	  		  		
	errorText += "</ul>";
	
    if (!isError) {
        DbExecutor de = new DbExecutor();
        de.addUser(name, login, password,gender,region,comment);
        out.write("Registration succeeded.");
    } else {
        out.write(errorText);
    }
%>