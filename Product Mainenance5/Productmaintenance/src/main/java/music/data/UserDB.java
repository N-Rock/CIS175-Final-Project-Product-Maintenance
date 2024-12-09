package music.data;
import jakarta.persistence.*;

public class UserDB {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");

    //LOGIN
    public static boolean checkUserPassword(String username,String password) {
        EntityManager em = emf.createEntityManager();
        try {
            long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username and u.password = :password and u.userRole in ('programmer', 'customerservice')", Long.class)
                           .setParameter("username", username)
                           .setParameter("password", password)
                           .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }  
       
    }
    
}
