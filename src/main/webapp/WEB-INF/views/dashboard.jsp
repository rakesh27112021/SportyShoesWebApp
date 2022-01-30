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

Product Lists
<br/><br/>

<a  href="addproduct"> Add new product </a><br/>
<c:set var="sl" value="1"/>
<table class="table table-striped table-hover">
	<thead>
			<tr class="bg-success">
				<th>SL No.</th>
		        <th>Product Id</th>
				<th>Name</th>
				<th>Brand</th>
				<th>Size</th>
				<th>Category</th>
				<th>Available Quantity</th>
				<th></th>
			</tr>
	</thead>
		<c:forEach items="${products}" var="product">  
		  <tr> 
		  	<td>${sl}</td> 
		    <td>${product.getProductid()}</td> 
		    <td>${product.getName()}</td>
		    <td>${product.getBrand()}</td>
		    <td>${product.getSize()}</td>
		    <td>${product.getCategory()}</td>
		    <td>${product.getAvailablequantity()}</td>
		    <td><a href="neworder?productid=${product.getProductid()}">Order</a> </td>
		    <c:set  var="sl" value="${sl+1}"/>
		  </tr> 
		</c:forEach> 

</table>


</body>
</html>