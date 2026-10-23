
package DAO;

import java.util.List;
import DTO.Product;

public interface ProductDAO {

    boolean addProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(int productId);

    boolean updateProduct(Product product);

    boolean deleteProduct(int productId);

    List<Product> getProductsWithCategory();

    List<Product> getProductsByCategory(int categoryId);

    List<Product> searchProductsByName(String name);

    List<Product> searchProductsByPrice(double minPrice, double maxPrice);

    boolean updateStock(int productId, int newQuantity);
}