
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBConnection;
import DTO.Category;

public class CategoryDAOImpl implements CategoryDAO {


    @Override
    public boolean addCategory(Category category) {

        String query = "INSERT INTO categories (category_id, category_name) VALUES (?, ?)";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, category.getCatogory_id());
            pstmt.setString(2, category.getCatogory_name());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<Category> getAllCategories() {

        List<Category> categories = new ArrayList<>();

        String query = "SELECT category_id, category_name FROM categories";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                Category category = new Category();

                category.setCatogory_id(rs.getInt("category_id"));
                category.setCatogory_name(rs.getString("category_name"));

                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }


    @Override
    public Category getCategoryById(int categoryId) {

        String query = "SELECT category_id, category_name FROM categories WHERE category_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, categoryId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    Category category = new Category();

                    category.setCatogory_id(rs.getInt("category_id"));
                    category.setCatogory_name(rs.getString("category_name"));

                    return category;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public boolean updateCategory(Category category) {

        String query = "UPDATE categories SET category_name = ? WHERE category_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, category.getCatogory_name());
            pstmt.setInt(2, category.getCatogory_id());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean deleteCategory(int categoryId) {

        String query = "DELETE FROM categories WHERE category_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, categoryId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean categoryExists(int categoryId) {

        String query = "SELECT 1 FROM categories WHERE category_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, categoryId);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}