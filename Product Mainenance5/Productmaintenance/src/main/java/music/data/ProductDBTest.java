package music.data;
import java.math.BigDecimal;
import java.util.List;
import music.business.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ProductDBTest {

    private Product sampleProduct;

    @BeforeEach
    public void setUp() {
        sampleProduct = new Product();
        sampleProduct.setCode("P001");
        sampleProduct.setDescription("Sample Product");
        sampleProduct.setPrice(10.00);  // Assuming setPrice accepts double
    }

    @Test
    public void testAddProduct() {
        int result = ProductDB.addProduct(sampleProduct);
        assertEquals(1, result, "Product should be added successfully.");
    }

    @Test
    public void testEditProduct() {
        sampleProduct.setDescription("Updated Description");
        int result = ProductDB.editProduct(sampleProduct);
        assertEquals(1, result, "Product description should be updated successfully.");
    }

    @Test
    public void testDeleteProduct() {
        ProductDB.addProduct(sampleProduct);  // Ensure the product exists before deleting
        int result = ProductDB.deleteProduct("P001");
        assertEquals(1, result, "Product should be deleted successfully.");
    }

    @Test
    public void testProductIdExists() {
        ProductDB.addProduct(sampleProduct);  // Ensure the product exists for this test
        assertTrue(ProductDB.productIdExists(sampleProduct), "Product ID should exist in the database.");
    }

    @Test
    public void testProduceProdList() {
        ProductDB.addProduct(sampleProduct);  // Ensure there is at least one product
        List<Product> products = ProductDB.produceProdList();
        assertFalse(products.isEmpty(), "Product list should not be empty after adding a product.");
    }

    @Test
    public void testParsePrice_ValidFormat() {
        BigDecimal result = ProductDB.parsePrice("10.00");
        assertEquals(new BigDecimal("10.00"), result, "Price should be parsed correctly.");
    }

    @Test
    public void testParsePrice_InvalidFormat() {
        assertThrows(NumberFormatException.class, () -> ProductDB.parsePrice("10.00.00"),
                "Invalid format should throw NumberFormatException.");
    }

    @Test
    public void testIsValidDecimal_Valid() {
        assertTrue(ProductDB.isValidDecimal("15.75"), "Valid decimal format should return true.");
    }

    @Test
    public void testIsValidDecimal_Invalid() {
        assertFalse(ProductDB.isValidDecimal("abc"), "Invalid decimal format should return false.");
    }

    @AfterEach
    public void tearDown() {
        ProductDB.deleteProduct("P001");  // Clean up after each test
    }
}
