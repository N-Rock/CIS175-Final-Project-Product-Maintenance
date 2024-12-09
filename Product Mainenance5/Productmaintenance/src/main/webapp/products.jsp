<%@ page import="music.business.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Products</title>
</head>
<body>
    <h1>Product List</h1>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>Code</th>
            <th>Description</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        <%
           List<Product> products = (List<Product>) request.getAttribute("products");
if (products != null) {
    for (Product product : products) {
        %>
        <tr>
            <td><%= product.getCode() %></td>
            <td><%= product.getDescription() %></td>
            <td><%= product.getPriceCurrencyFormat() %></td>
            <td>
                <a href="ProductServlet?action=edit&code=<%= product.getCode() %>">Edit</a>
                |
                <a href="ProductServlet?action=delete&code=<%= product.getCode() %>">Delete</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <br>
    <a href="ProductServlet?action=add">Add New Product</a>
</body>
</html>
