<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Orders Lists
<br/><br/>

<c:set var="sl" value="1"/>
<table class="table table-striped table-hover">
	<thead>
			<tr class="bg-success">
				<th>SL No.</th>
		        <th>Order Id</th>
				<th>Product Name</th>
				<th>Brand</th>
				<th>User Name</th>
				<th>Order Date</th>
				<th>Ordered Quantity</th>
				<th></th>
			</tr>
	</thead>
		<c:forEach items="${orders}" var="Order">  
		  <tr> 
		  	<td>${sl}</td> 
		    <td>${Order.getOrderid()}</td> 
		    <td>${Order.getProduct().getName()}</td>
		    <td>${Order.getProduct().getBrand()}</td>
		    <td>${Order.getUser().getUserfirstname()}</td>
		    <td>${Order.getOrderDate()}</td>
		    <td>${Order.getQuantity()}</td>
		    <td> </td>
		    <c:set  var="sl" value="${sl+1}"/>
		  </tr> 
		</c:forEach> 

</table>

</body>
</html>