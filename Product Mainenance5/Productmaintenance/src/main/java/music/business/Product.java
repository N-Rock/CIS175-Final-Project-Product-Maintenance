package music.business;

import java.io.Serializable;
import java.text.NumberFormat;
import jakarta.persistence.*;

@Entity
@Table(name ="Product")//dbname
public class Product implements Serializable {
    
    //Jpa annotations
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment prime key
    @Column(name = "ProductId")
    private Long productId;

    @Column(name = "Code", nullable = false, unique = true)
    private String code;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "Price", nullable = false)
    private double price;
    
    
    
    public Product() {
        this.code = "";
        this.description = "";
        this.price = 00;
    
    }

    public Product(String code, String description, double price) {
        this.code = code;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return productId;
    }

    public void setId(Long productId) {
        this.productId = productId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    // New method to format price as currency
    public String getPriceCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }

    public String getPriceFormatted() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }
}
