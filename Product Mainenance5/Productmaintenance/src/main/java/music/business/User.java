package music.business;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name ="User")//dbname
public class User implements Serializable {
    
    //Jpa annotations
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment prime key
    @Column(name = "UserId")
    private Long userId;

    @Column(name = "Username", nullable = false, unique = true)
    private String username;

    @Column(name = "Password", nullable = false)
    private String password;
    
    @Column(name = "UserRole", nullable = false)
    private String userRole;
        
    public User() {
        this.username = "";
        this.password = "";
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    public String getUserRole() {
        return userRole;
    }
}
