
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBConnection;
import DTO.Product;

public class ProductDAOImpl implements ProductDAO {


    @Override
    public boolean addProduct(Product product) {

        CategoryDAO categoryDAO = new CategoryDAOImpl();


        if (!categoryDAO.categoryExists(product.getCategory_id())) {
            System.out.println("Invalid category ID!");
            return false;
        }


        String query = "INSERT INTO products "
                + "(product_id, product_name, price, stock_quantity, category_id) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, product.getProduct_id());
            pstmt.setString(2, product.getProduct_name());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStock_quantity());
            pstmt.setInt(5, product.getCategory_id());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        String query = "SELECT product_id, product_name, price, "
                + "stock_quantity, category_id FROM products";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                Product product = new Product();

                product.setProduct_id(rs.getInt("product_id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setPrice(rs.getDouble("price"));
                product.setStock_quantity(rs.getInt("stock_quantity"));
                product.setCategory_id(rs.getInt("category_id"));

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    @Override
    public Product getProductById(int productId) {

        String query = "SELECT product_id, product_name, price, "
                + "stock_quantity, category_id "
                + "FROM products WHERE product_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, productId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    Product product = new Product();

                    product.setProduct_id(rs.getInt("product_id"));
                    product.setProduct_name(rs.getString("product_name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStock_quantity(rs.getInt("stock_quantity"));
                    product.setCategory_id(rs.getInt("category_id"));

                    return product;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public boolean updateProduct(Product product) {

        String query = "UPDATE products SET product_name = ?, "
                + "price = ?, stock_quantity = ?, category_id = ? "
                + "WHERE product_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, product.getProduct_name());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getStock_quantity());
            pstmt.setInt(4, product.getCategory_id());
            pstmt.setInt(5, product.getProduct_id());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean deleteProduct(int productId) {

        String query = "DELETE FROM products WHERE product_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, productId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<Product> getProductsWithCategory() {

        List<Product> products = new ArrayList<>();

        String query = "SELECT p.product_id, p.product_name, "
                + "p.price, p.stock_quantity, "
                + "c.category_name "
                + "FROM products p "
                + "INNER JOIN categories c "
                + "ON p.category_id = c.category_id";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                Product product = new Product();

                product.setProduct_id(rs.getInt("product_id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setPrice(rs.getDouble("price"));
                product.setStock_quantity(rs.getInt("stock_quantity"));


                product.setCategory_name(rs.getString("category_name"));

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    @Override
    public List<Product> getProductsByCategory(int categoryId) {

        List<Product> products = new ArrayList<>();

        String query = "SELECT p.product_id, p.product_name, "
                + "p.price, p.stock_quantity, "
                + "p.category_id, c.category_name "
                + "FROM products p "
                + "INNER JOIN categories c "
                + "ON p.category_id = c.category_id "
                + "WHERE p.category_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, categoryId);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    Product product = new Product();

                    product.setProduct_id(rs.getInt("product_id"));
                    product.setProduct_name(rs.getString("product_name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStock_quantity(rs.getInt("stock_quantity"));
                    product.setCategory_id(rs.getInt("category_id"));
                    product.setCategory_name(rs.getString("category_name"));

                    products.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    @Override
    public List<Product> searchProductsByName(String name) {

        List<Product> products = new ArrayList<>();

        String query = "SELECT p.product_id, p.product_name, "
                + "p.price, p.stock_quantity, p.category_id, "
                + "c.category_name "
                + "FROM products p "
                + "INNER JOIN categories c "
                + "ON p.category_id = c.category_id "
                + "WHERE p.product_name LIKE ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, "%" + name + "%");

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    Product product = new Product();

                    product.setProduct_id(rs.getInt("product_id"));
                    product.setProduct_name(rs.getString("product_name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStock_quantity(rs.getInt("stock_quantity"));
                    product.setCategory_id(rs.getInt("category_id"));
                    product.setCategory_name(rs.getString("category_name"));

                    products.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    @Override
    public List<Product> searchProductsByPrice(double minPrice, double maxPrice) {

        List<Product> products = new ArrayList<>();

        String query = "SELECT p.product_id, p.product_name, "
                + "p.price, p.stock_quantity, p.category_id, "
                + "c.category_name "
                + "FROM products p "
                + "INNER JOIN categories c "
                + "ON p.category_id = c.category_id "
                + "WHERE p.price BETWEEN ? AND ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setDouble(1, minPrice);
            pstmt.setDouble(2, maxPrice);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    Product product = new Product();

                    product.setProduct_id(rs.getInt("product_id"));
                    product.setProduct_name(rs.getString("product_name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStock_quantity(rs.getInt("stock_quantity"));
                    product.setCategory_id(rs.getInt("category_id"));
                    product.setCategory_name(rs.getString("category_name"));

                    products.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    @Override
    public boolean updateStock(int productId, int newQuantity) {

        if (newQuantity < 0) {
            System.out.println("Stock quantity cannot be negative.");
            return false;
        }

        String query ="UPDATE products SET stock_quantity = ? WHERE product_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, productId);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Stock updated successfully.");
                return true;
            } else {
                System.out.println("Product not found.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}