<%@ page import="Demo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
  <title>Signup</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<!--  <script>
    function login(){
      <%
        response.sendRedirect("login.jsp"); 
      %>
    }
  </script>   --> 
</head

<body>
	<div class="container">
		<form action="signup" method="get">
  			<div class="form-group">
    		<label for="exampleInputEmail1">Username</label>
    		<input type="text" class="form-control" id="uname" aria-describedby="userHelp" placeholder="Enter your username" name="uname">
    		<small id="userHelp" class="form-text text-muted" >Account wont be created if you enter an existing username.</small>
  			</div>
  			<div class="form-group">
    		<label for="password">Password</label>
    		<input type="password" class="form-control" id="password" name="password" placeholder="Password">
  			</div>
  			<button type="submit" class="btn btn-primary">Submit</button>

		</form>
 <!--   <div>
        <form action = "login.jsp" method="get"> 
        <h3>Already have an account</h3>
        <button action="submit">login</button>
      </form>
    </div>   -->
	</div>
</body>
</html>