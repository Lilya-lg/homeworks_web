<table border = 1>
<%
	int raws = Integer.parseInt(request.getParameter("raws"));
	int columns = Integer.parseInt(request.getParameter("columns"));
	int[][] array = new int[raws][columns];
	int counter = 0;
	for (int i = 0; i < array.length; i++) {
		%>
			<tr>
			<%
			for (int j = 0; j < array[i].length; j++) {				
				counter++;
				if(counter%2 == 0){
				%>				
				<td bgcolor="#FFFF00"> <%=counter%> </td>
				<%
				}
				else {
					%>
					<td> <%=counter%> </td>
					<%
				}
			}	
			%>
			</tr>
			<%
		}
%>
</table>