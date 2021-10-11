<%@ page pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<c:forEach var="product" items="${productMap}">
<table>
<tr><td>${product.key.name}</td><td></td></tr>
<tr><td><a href = "products?id = ${product.key.id}"><img src = "static/images/${product.key.id}.jpg" width = "250" height = "100%"/></a></td><td>${product.key.description}</td></tr>
<tr><td>${product.key.price}</td><td><form action="cart" method="post">
    <input type="text" size="2" name="qntRemove" value="${product.value}"/>
    <input type="hidden" name="remove" value="${product.key.id}"/>
    <input type="submit" value="Remove"/></form></td></tr>
</table><br/><br/>	
</c:forEach>
<table>
<tr>
<td>Итого в корзине:</td>
<td>${productsCartMapSize}</td>
</tr>
</table>
<%@include file="footer.jsp" %>