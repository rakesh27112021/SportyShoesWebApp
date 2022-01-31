<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.dto.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%User user=(User)request.getSession().getAttribute("user");%>
Welcome,${user.getUsername()}<br/><br/><br/>

Order details
<br/><br/>

<form action="saveorder" method="post" >
	Product id: <input type="text" name="productid" value="${order.product.getProductid()}" disabled="disabled"/><br/>
	Product Name: <input type="text" value="${order.product.getName()}" name="name"/><br/>
	Brand: <input type="text" name="brand" value="${order.product.getBrand()}"/><br/>
	Size: <input type="text" name="size" value="${order.product.getSize()}"/><br/>
	Category: <input type="text" name="category" value="${order.product.getCategory()}"/><br/>
	Quantity: <input type="number" name="quantity" value=0 required/> <br/>
	<input type="submit" value="Save"/><br/>
</form>
<br/>
<br/>

</body>
</html>