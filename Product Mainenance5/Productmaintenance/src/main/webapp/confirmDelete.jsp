<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Confirm Delete</title>
</head>
<body>
    <h1>Confirm Deletion</h1>
    <p>Are you sure you want to delete this product?</p>
    
    <form action="ProductServlet" method="post">
        <input type="hidden" name="action" value="deleteConfirmed">
        <input type="hidden" name="code" value="<%= request.getParameter("code") %>">
        <input type="submit" value="Yes, Delete Product">
    </form>

    <br>
    <a href="ProductServlet?action=list">No, Cancel</a>
</body>
</html>
