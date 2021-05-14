<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@page import = "com.User" %>
      
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Management</title>


<link rel = "stylesheet" href = "Views/bootstrap.min.css">
<script src = "Components/jquery-3.6.0.min.js"></script>
<script src = "Components/users.js"></script>



</head>
<body>

<div class = "container"> 
	<div class="row">
		<div class="col-lg-8 
           m-auto d-block">

		<h1>Create an Account</h1>
		
		<form id="formCustomer" name="formCustomer">  	
		Name:
		<input id="name" name=name type="text" class="form-control form-control-sm" placeholder = "First Name"><br>
		
		Type:
		<input id="type" name="type" type="text" class="form-control form-control-sm" placeholder = "Type"><br>
		 
		Email:
		<input id="email" name="email" type="email" class="form-control form-control-sm" placeholder = "example@gmail.com" id="emailvalid"><br>
		
		Username:
		<input id="username" name="username" type="text" class="form-control form-control-sm" placeholder = "username"><br>
		
		password:
		<input id="password" name="password" type="password" class="form-control form-control-sm" placeholder = "Password" id="passcheck"><br>
		
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidCustomerIDSave" name="hidCustomerIDSave" value="">
	</form>
	
    
    <div id="alertSuccess" class="alert alert-success"></div>
     <div id="alertError" class="alert alert-danger"></div>
    <br>
    <br>
    <div id="divItemsGrid">
	<%
	User userObj = new User();
	out.print(userObj.readUsers());
	%>
	</div>
	

<br>


</div>
</div>
</div>


</body>
</html>