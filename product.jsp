<%@ page pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<table>
<tr><td>${product.name}</td><td></td></tr>
<tr><td><a href="products?id=${product.id}"><img src="static/images/${product.id}.jpg" width="250" height="100%"/></a></td><td>${product.description}</td></tr>
<tr><td>${product.price}</td><td>Buy</td></tr>
</table><br/><br/>



<%@include file="footer.jsp" %>