<%@ page pageEncoding="UTF-8"%>
		  </div>
            
				<div id="sidebar">
					<table border=1>
                    <tr>
                    <td width="252" align="left">
                    <font color=white>
                    <c:choose>
					<c:when test = "${sessionScope.authorized!=null}">
                    Вы авторизировались как ${sessionScope.authorized}</c:when>
                    <c:otherwise>
					Вы не авторизованы
					</c:otherwise>
					</c:choose>
					<br />
                    В вашей корзине ${(sessionScope.productsCartListSize==null?0:sessionScope.productsCartListSize)} товаров.
                    </font>
                    </td>
                    </tr>
                    </table>
                    <h2>Боковое меню</h2>
					<ul>
						<li><a href="products?category=socks">Socks</a></li>
						<li><a href="products?category=Category2">Category2</a></li>
						<li><a href="products?category=Smth else">Smth else</a></li>
						<li><a href="registration">Регистрация</a></li>
						<li><a href="login">Вход</a></li>
						<li><a href="cart">Корзина</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="footer">
	<p>Copyright (c) by Бондаренко Антон</p>
</div>
<!-- end #footer -->
</body>
</html>