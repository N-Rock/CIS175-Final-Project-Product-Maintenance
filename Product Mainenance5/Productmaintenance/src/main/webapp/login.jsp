<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Maintenance</title>
    <style>
        .form-group {
            margin-bottom: 1em;
            display: flex;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 0.3em;
            width: 120px;
        }
        .form-group input {
             width: 300px;
            padding: 0.5em;
        }
        .error {
            color: red;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
    <h1>Login Form</h1>
    <p>Please enter a username  and password to continue.</p>
    <form action="ProductServlet" method="post">
        <input type="hidden" name="action" value="login">
        
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" name="username" id="username" value="">
        </div>
        
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" value="">
        </div>
        <div class="form-group">
            <label for="password"></label>
            <button type="submit">Login</button>
        </div>  
        
    </form>
</body>
</html>
