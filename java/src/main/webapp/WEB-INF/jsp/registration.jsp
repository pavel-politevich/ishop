<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
    <h1>Registration Page</h1>
    <div class="container">
        <form action="Controller">
            <input type="hidden" name="command" value="registration" />

            <label for="login">Login</label>
            <input type="text" id="login" name="login" required>
            <br /><br />

            <label for="password">Password</label>
            <input type="password" id="psw" name="password" required>
            <br /><br />

            <label for="username">Name</label>
            <input type="text" id="username" name="username" required>
            <br /><br />

            <label for="surname">Surname</label>
            <input type="text" id="surname" name="surname" required>
            <br /><br />

            <label for="email">e-mail</label>
            <input type="email" id="email" name="email" required>
            <br /><br />

            <label for="phone">Phone</label>
            <input type="text" id="phone" name="phone" required>
            <br /><br />

            <label for="address">Address</label>
            <input type="text" id="address" name="address" required>
            <br /><br />

            <label for="dateOfBirth">Date of birth</label>
            <input type="date" id="dateOfBirth" name="dateOfBirth" required>
            <br /><br />


            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>