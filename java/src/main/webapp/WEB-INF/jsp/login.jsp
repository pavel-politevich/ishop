<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<h2>Login Page</h2>
<div class="container">
	  <form action="Controller">
	  	<input type="hidden" name="command" value="signIn" />
	    <label for="username">Username</label>
	    <input type="text" id="username" name="username" required>

	    <label for="password">Password</label>
	  <input type="password" id="password" name="password" required>

	    <input type="submit" value="Submit">
	  </form>
</div>
</body>
</html>