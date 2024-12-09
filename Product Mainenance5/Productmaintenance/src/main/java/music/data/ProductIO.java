package music.data;

import java.io.*;
import java.util.*;
import music.business.Product;

public class ProductIO {

    private static List<Product> products = null;
    private static String filePath = null;

    // Called once from the servlet to set the file path
    public static void init(String filePath) {
        ProductIO.filePath = filePath;
    }

    public static List<Product> selectProducts() {
        products = new ArrayList<>();
        if (filePath == null) {
            throw new IllegalStateException("File path has not been initialized.");
        }

        File file = new File(filePath);
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line = in.readLine();
            while (line != null) {
                StringTokenizer t = new StringTokenizer(line, "|");
                if (t.countTokens() >= 3) {
                    String code = t.nextToken();
                    String description = t.nextToken();
                    String priceAsString = t.nextToken();
                    double price = Double.parseDouble(priceAsString);

                    Product p = new Product(code, description, price);
                    products.add(p);
                }
                line = in.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return products;
    }

    public static Product selectProduct(String productCode) {
        products = selectProducts();
        for (Product p : products) {
            if (productCode != null && productCode.equalsIgnoreCase(p.getCode())) {
                return p;
            }
        }
        return null;
    }

    public static void saveProducts(List<Product> products) {
        if (filePath == null) {
            throw new IllegalStateException("File path has not been initialized.");
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(new File(filePath)))) {
            for (Product p : products) {
                out.println(p.getCode() + "|" + p.getDescription() + "|" + p.getPrice());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
