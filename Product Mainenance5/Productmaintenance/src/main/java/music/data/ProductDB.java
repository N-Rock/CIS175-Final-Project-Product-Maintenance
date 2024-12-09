package music.data;

//import java.sql.*;
import java.util.List;
import java.math.BigDecimal;
import music.business.Product;
import jakarta.persistence.*;

public class ProductDB {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");

    
    //LIST 
    public static List<Product> produceProdList() {
    EntityManager em = emf.createEntityManager();
    try {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    } finally {
        em.close();
    }
}

    //        ConnectionPool cPool = ConnectionPool.getInstance();
//        Connection connection = cPool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        products = new ArrayList<>();
//        String query = "SELECT * FROM product";
//        
//        try {
//            ps = connection.prepareStatement(query);
//            rs = ps.executeQuery();
//            
//            while (rs.next()) {
//                Product tmpProduct = new Product();
//                tmpProduct.setCode(rs.getString("ProductCode"));
//                tmpProduct.setDescription(rs.getString("ProductDescription"));
//                tmpProduct.setPrice(rs.getDouble("ProductPrice"));
//                products.add(tmpProduct);
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        } finally {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            cPool.freeConnection(connection);
//        }
//        return products;


    //ADD
   public static int addProduct(Product product) {
       
       
       EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(product); // Persist the product object
            transaction.commit();
            return 1; // Success
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 0; // Failure
        } finally {
            em.close();
        }
    //ConnectionPool cPool = ConnectionPool.getInstance();
    //Connection connection = cPool.getConnection();
    //PreparedStatement ps = null;
    

//    String query = "INSERT INTO Product (ProductCode, ProductDescription, ProductPrice) VALUES (?, ?, ?)";
//    try {
//        ps = connection.prepareStatement(query);
//        ps.setString(1, product.getCode());
//        ps.setString(2, product.getDescription());
//        if(ProductDB.productIdExists(product)){
//            throw new SQLException("Product ID must be unique");
//        }else{
//
//        // Validate and parse ProductPrice
//        String priceInput = product.getPriceFormatted();
//        if (isValidDecimal(priceInput)) {
//            BigDecimal priceValue = parsePrice(priceInput);
//            ps.setBigDecimal(3, priceValue);
//        } else {
//            throw new SQLException("Invalid price format for ProductPrice.");
//        }
//
//            return ps.executeUpdate();
//        }
//    } catch (SQLException e) { //exception handling
//        System.out.println(e);
//        return 0;
//    } finally {
//        DBUtil.closePreparedStatement(ps);
//        cPool.freeConnection(connection);
//    }
    
    
}


protected static BigDecimal parsePrice(String priceInput) throws NumberFormatException {
    // Remove all non-numeric characters except the decimal point
    String sanitizedPrice = priceInput.replaceAll("[^\\d.]", "").trim();
    
    // Ensure there's only one decimal point
    if (sanitizedPrice.indexOf('.') != sanitizedPrice.lastIndexOf('.')) {
        throw new NumberFormatException("Invalid price format: multiple decimal points.");
    }
    
    return new BigDecimal(sanitizedPrice);
}

protected static boolean isValidDecimal(String input) {
    try {
        // Remove any unwanted characters
        new BigDecimal(input.replaceAll("[^\\d.]", "").trim());
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}


    // EDIT
    public static int editProduct(Product product) {


        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(product); // Merge updates to the product
            transaction.commit();
            return 1; // Success
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 0; // Failure
        } finally {
            em.close();
        }


//        ConnectionPool cPool = ConnectionPool.getInstance();
//        Connection connection = cPool.getConnection();
//        PreparedStatement ps = null;
//
//        String query = "UPDATE product SET ProductCode = ?, ProductDescription = ?, ProductPrice = ? WHERE ProductCode = ?";
//        try {
//          
//            ps = connection.prepareStatement(query);
//            ps.setString(1, product.getCode());
//            ps.setString(2, product.getDescription());
//            
//            if (isValidDecimal(product.getPriceFormatted())) {
//                BigDecimal priceValue = parsePrice(product.getPriceFormatted());
//                ps.setBigDecimal(3, priceValue);
//            } else {
//                throw new SQLException("Invalid price format for ProductPrice.");
//            }
//            ps.setString(4, product.getCode());
//
//            return ps.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e);
//            return 0;
//        } finally {
//            DBUtil.closePreparedStatement(ps);
//            cPool.freeConnection(connection);
//        }
    }

    
    //DELETE
    public static int deleteProduct(String code) {
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Product product = em.createQuery("SELECT p FROM Product p WHERE p.code = :code", Product.class)
                                .setParameter("code", code)
                                .getSingleResult();
            em.remove(product); // Remove the product
            transaction.commit();
            return 1; // Success
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 0; // Failure
        } finally {
            em.close();
        }
        
        
//        ConnectionPool cPool = ConnectionPool.getInstance();
//        Connection connection = cPool.getConnection();
//        PreparedStatement ps = null;
//
//        String query = "DELETE FROM product WHERE ProductCode = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, code);
//            return ps.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e);
//            return 0;
//        } finally {
//            DBUtil.closePreparedStatement(ps);
//            cPool.freeConnection(connection);
//        }
    }
    //PRODUCTID VALID CHECK
    public static boolean productIdExists(Product product) {
        
          EntityManager em = emf.createEntityManager();

        try {
            long count = em.createQuery("SELECT COUNT(p) FROM Product p WHERE p.code = :code", Long.class)
                           .setParameter("code", product.getCode())
                           .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }  
        
//        ConnectionPool cPool = ConnectionPool.getInstance();
//        Connection connection = cPool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query = "SELECT ProductCode FROM product WHERE ProductCode = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, product.getCode());
//            rs = ps.executeQuery();
//            return rs.next();
//        } catch (SQLException e) {
//            System.out.println(e);
//            return false;
//        } finally {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            cPool.freeConnection(connection);
//        }
    }
    
    //SELECT PRODUCT
    public static Product selectProduct(String code){
         EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("SELECT p FROM Product p WHERE p.code = :code", Product.class)
                     .setParameter("code", code)
                     .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No product found with code: " + code);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
//        ConnectionPool cPool = ConnectionPool.getInstance();
//        Connection connection = cPool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        
//        Product tmpProduct = new Product();
//
//        
//        String query = "SELECT * FROM products " + 
//                " WHERE ProductCode = ?";
//        
//        try{
//            ps = connection.prepareStatement(query);
//            ps.setString(1, code);
//            rs = ps.executeQuery();
//            
//            tmpProduct.setCode(rs.getString("ProductCode"));
//            tmpProduct.setDescription(rs.getString("ProductDescription"));
//            tmpProduct.setPrice(rs.getDouble("ProductPrice"));
//        }catch(SQLException e){
//            System.out.println(e);
//        }finally{
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            cPool.freeConnection(connection);
//        }
//        return tmpProduct;
//                
        
         
    }
    
}
