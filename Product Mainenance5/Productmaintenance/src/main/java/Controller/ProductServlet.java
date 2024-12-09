package controller;

import music.business.Product;
import music.data.ProductDB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import music.data.UserDB;

public class ProductServlet extends HttpServlet {             
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if (session.getAttribute("isLogin") == null) {
            session.setAttribute("isLogin", false);
        }
        
        Boolean isLogin = (Boolean) session.getAttribute("isLogin");
        String url = "/login.jsp";
        if(isLogin == true){
            
       


        
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";  // Default action
        }

 

        switch (action) {
            case "login":
                url = "/login.jsp";
                break;
            case "list":
                List<Product> products = ProductDB.produceProdList();
                request.setAttribute("products", products);
                url = "/products.jsp";
                break;
            case "add":
                url = "/product.jsp";  // Empty form for adding a product
                break;
            case "edit":
                {
                    ServletContext sc = request.getServletContext(); // getting servlet context to check a flag for editing
                    String code = request.getParameter("code");
                    Product product = ProductDB.selectProduct(code); //ProductIO.selectProduct(code);
                    request.setAttribute("product", product);
                    sc.setAttribute("editFlag", true); // created an attribute to check whether to add or edit an item
                    url = "/product.jsp";
                    break;
                }
            case "delete":
                String deleteCode = request.getParameter("code");
                request.setAttribute("code", deleteCode);
                url = "/confirmDelete.jsp";
                break;
            default:
                break;
        }
                }
      

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String code = request.getParameter("code");

        switch (action) {
            case "save":
                String description = request.getParameter("description");
                String priceString = request.getParameter("price");
                String idString = request.getParameter("id");

                // Server-side validation for empty fields
                if (description == null || description.trim().isEmpty() || priceString == null || priceString.trim().isEmpty()) {
                    request.setAttribute("error", "Description and Price are required fields.");
                    request.getRequestDispatcher("/product.jsp").forward(request, response);
                    return;
                }

                try {
                double price = Double.parseDouble(priceString);
                Product product = new Product(code, description, price);
                //new servlet context object to retrieve the attribute
               // ServletContext sc = request.getServletContext();
                String editFlag = request.getParameter("editFlag");
                // Check for null & true on "editFlag" and provide a default
    //            Boolean editFlag = (Boolean) sc.getAttribute("editFlag");
                if (editFlag != null && !editFlag.isEmpty()) {
                    Long productId = Long.valueOf(idString); 
                    product.setId(productId);
                    ProductDB.editProduct(product); //calling to edit the database
                   // sc.removeAttribute("editFlag");
                } else {
                    ProductDB.addProduct(product); //calling to insert an item into the database
                }
                    response.sendRedirect("ProductServlet?action=list");
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Price must be a valid number.");
                    request.getRequestDispatcher("/product.jsp").forward(request, response);
                }  
                
                break;
            case "login":
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                Boolean isCheck = UserDB.checkUserPassword(username, password);
                HttpSession session = request.getSession(true);
                
                if(isCheck){
                    session.setAttribute("isLogin", true);
                    
                    response.sendRedirect("ProductServlet?action=list");
                } else {
                    request.setAttribute("error", "Username and password error.");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } 
                break;
            case  "deleteConfirmed":
                ProductDB.deleteProduct(code); // Delete the product from the database
                response.sendRedirect("ProductServlet?action=list");
                break;     
        }
        
//
//        if ("save".equals(action)) {
//            String code = request.getParameter("code");
//            String description = request.getParameter("description");
//            String priceString = request.getParameter("price");
//            String idString = request.getParameter("id");
//
//            // Server-side validation for empty fields
//            if (description == null || description.trim().isEmpty() || priceString == null || priceString.trim().isEmpty()) {
//                request.setAttribute("error", "Description and Price are required fields.");
//                request.getRequestDispatcher("/product.jsp").forward(request, response);
//                return;
//            }
//
//            try {
//            double price = Double.parseDouble(priceString);
//            Product product = new Product(code, description, price);
//            //new servlet context object to retrieve the attribute
////            ServletContext sc = request.getServletContext();
//            String editFlag = request.getParameter("editFlag");
//            // Check for null & true on "editFlag" and provide a default
////            Boolean editFlag = (Boolean) sc.getAttribute("editFlag");
//            if (editFlag != null && !editFlag.isEmpty()) {
//                Long productId = Long.parseLong(idString); 
//                product.setId(productId);
//                ProductDB.editProduct(product); //calling to edit the database
////                sc.removeAttribute("editFlag");
//            } else {
//                ProductDB.addProduct(product); //calling to insert an item into the database
//            }
//                response.sendRedirect("ProductServlet?action=list");
//            } catch (NumberFormatException e) {
//                request.setAttribute("error", "Price must be a valid number.");
//                request.getRequestDispatcher("/product.jsp").forward(request, response);
//            }
//        }else if ("deleteConfirmed".equals(action)) {
//            String code = request.getParameter("code");
//            ProductDB.deleteProduct(code); // Delete the product from the database
//            response.sendRedirect("ProductServlet?action=list");
//        }
    }
}
