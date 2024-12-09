<%@ page import="music.business.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mma" uri="/WEB-INF/custom-tags.tld" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Form</title>
    <style>
        .form-group {
            margin-bottom: 1em;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 0.3em;
        }
        .form-group input {
            width: 100%;
            padding: 0.5em;
        }
        .error {
            color: red;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
    <% 
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        Product product = (Product) request.getAttribute("product");

        boolean hasEmptyDescription = (description == null || description.trim().isEmpty());
        boolean hasEmptyPrice = (price == null || price.trim().isEmpty());
    %>

    <h1><% if ("add".equals(request.getParameter("action"))) { %>Add<% } else { %>Edit<% } %> Product</h1>

    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
        <p class="error"><%= error %></p>
    <% } %>

    <form action="ProductServlet" method="post">
        <input type="hidden" name="action" value="save">

        <input type="hidden" name="editFlag" id="editFlag" value="<%= product != null ? true : false %>">
        <input type="hidden" name="id" id="id" value="<%= product != null ? product.getId() : "" %>">

        <div class="form-group">
            <label for="code">Product Code:</label>
            <input type="text" name="code" id="code" value="<%= product != null ? product.getCode() : "" %>">
        </div>
        
        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" name="description" id="description" value="<%= product != null ? product.getDescription() : "" %>" required>
            <% if (hasEmptyDescription) { %>
                <span class="error">* Required</span>
            <% } %>
        </div>

        <div class="form-group">
            <label for="price">Price:</label>
            <input type="text" name="price" id="price" value="<%= product != null ? product.getPrice() : "" %>" required>
            <% if (hasEmptyPrice) { %>
                <span class="error">* Required</span>
            <% } %>
        </div>

        <input type="submit" value="Save Product">
    </form>
        
    <br>
    <a href="ProductServlet?action=list">Cancel</a>
</body>
</html>
