<%
    boolean isError = false;	
	boolean showForm = false;
    String errorText = "<ul>";
    String login = request.getParameter("login");
	
    String password = request.getParameter("password");
    String passwordRepeat = request.getParameter("passwordRepeat");
    String gender = request.getParameter("gender");
    String region = request.getParameter("region");
    String comment = request.getParameter("comment");
    String browser = request.getParameter("browser");
	
	if(login!= null){
	if (login.matches("^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}")==false){
		isError = true;
        errorText += "<li>Please check your email!</li>";
	}	
	}
	if(password != null){
	if (password.equals(passwordRepeat)== false){
		 isError = true;
		 errorText += "<li>Repeat password correctly!</li>";
	}
	if (password.matches("^(?=.*[0-9])(?=.*[A-Z]).{8,}$")== false){
		 isError = true;
		 errorText += "<li>Password must contain 8 digits, 1 number and 1 capital letter</li>";
	}
	}
	
	if(login == null || login.isEmpty() || password == null || password.isEmpty()|| passwordRepeat == null || passwordRepeat.isEmpty()
		|| gender == null || gender.isEmpty() || comment == null || comment.isEmpty()) {
		  isError = true;
          errorText += "<li>All fields are mandatory</li>";
		}
	else if (browser == null ){
		  isError = true;
          errorText += "<li>Check Amigo</li>";
	}	  
		  
		
	errorText += "</ul>";
    if (!isError) {
	    out.write("Registration succeeded.");
		showForm = false;
       		
    } else {
        showForm = true;
		out.write(errorText);
    }
	if (showForm) {
%>
<center>
<table>
<form action="index.jsp" method="post">
    <tr><td>Email:</td><td><input type="email" name="login"/></td></tr>
    <tr><td>Password:</td><td><input type="password" name="password"/></td></tr>
    <tr><td>Repeat password:</td><td><input type="password" name="passwordRepeat"/></td></tr>
    <tr><td>Gender:</td><td>M<input type="radio" name="gender" value="M"/>F<input type="radio" name="gender" value="F"/></td></tr>
    <tr><td>Region:</td><td><select name="region">
        <option value="DNR">ДНР</option>
        <option value="LNR">ЛНР</option>
        <option value="Crimea">Крым</option>
    </select></td></tr>
    <tr><td>Comment:</td><td><textarea rows=10 cols=20 name="comment"/></textarea></td></tr>
    <tr><td>I agree to install an Amigo Browser:</td><td><input type="checkbox" name="browser" checked="true"/></td></tr>
    <tr><td> </td><td><input type="submit" value="SEND"/></td></tr>
</form>
</table>
</center>

<%
	}
%>