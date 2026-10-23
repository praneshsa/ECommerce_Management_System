package main;

import java.util.List;
import java.util.Scanner;

import DAO.CartDAOImpl;
import DAO.CartItemDAOImpl;
import DAO.CategoryDAOImpl;
import DAO.OrderDAOImpl;
import DAO.OrderItemDAOImpl;
import DAO.ProductDAOImpl;
import DAO.UserDAOImpl;

import DTO.Cart;
import DTO.CartItem;
import DTO.Category;
import DTO.Order;
import DTO.OrderItem;
import DTO.Product;
import DTO.User;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserDAOImpl userDAO = new UserDAOImpl();
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        ProductDAOImpl productDAO = new ProductDAOImpl();
        CartDAOImpl cartDAO = new CartDAOImpl();
        CartItemDAOImpl cartItemDAO = new CartItemDAOImpl();
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        OrderItemDAOImpl orderItemDAO = new OrderItemDAOImpl();

        boolean running = true;

        while (running) {

            System.out.println("\n================================");
            System.out.println("   E-COMMERCE MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. User Management");
            System.out.println("2. Category Management");
            System.out.println("3. Product Management");
            System.out.println("4. Cart Management");
            System.out.println("5. Cart Item Management");
            System.out.println("6. Order Management");
            System.out.println("7. Order Item Management");
            System.out.println("8. Exit");
            System.out.println("================================");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {


                case 1:

                    boolean userMenu = true;

                    while (userMenu) {

                        System.out.println("\n---------- USER MANAGEMENT ----------");
                        System.out.println("1. Add User");
                        System.out.println("2. View All Users");
                        System.out.println("3. Find User");
                        System.out.println("4. Update User");
                        System.out.println("5. Delete User");
                        System.out.println("6. Login");
                        System.out.println("7. Back");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();

                        switch (ch) {

                            case 1:

                                User user = new User();

                                System.out.print("Enter User ID: ");
                                user.setUser_id(sc.nextInt());

                                sc.nextLine();

                                System.out.print("Enter Name: ");
                                user.setUser_name(sc.nextLine());

                                System.out.print("Enter Email: ");
                                user.setEmail(sc.nextLine());

                                System.out.print("Enter Password: ");
                                user.setPassword(sc.nextLine());

                                System.out.print("Enter Role: ");
                                user.setRole(sc.nextLine());

                                System.out.println(
                                        userDAO.addUser(user));

                                break;

                            case 2:

                                List<User> users =
                                        userDAO.getAllUsers();

                                for (User u : users) {

                                    System.out.println(
                                            "User ID: " + u.getUser_id());

                                    System.out.println(
                                            "Name: " + u.getUser_name());

                                    System.out.println(
                                            "Email: " + u.getEmail());

                                    System.out.println(
                                            "Role: " + u.getRole());

                                    System.out.println("-------------------");
                                }

                                break;

                            case 3:

                                System.out.print("Enter User ID: ");
                                int userId = sc.nextInt();

                                User foundUser =
                                        userDAO.getUserById(userId);

                                if (foundUser != null) {

                                    System.out.println("User Found");
                                    System.out.println(
                                            "ID: " + foundUser.getUser_id());
                                    System.out.println(
                                            "Name: " + foundUser.getUser_name());
                                    System.out.println(
                                            "Email: " + foundUser.getEmail());
                                    System.out.println(
                                            "Role: " + foundUser.getRole());

                                } else {

                                    System.out.println(
                                            "User Not Found");
                                }

                                break;

                            case 4:

                                User updateUser = new User();

                                System.out.print("Enter User ID: ");
                                updateUser.setUser_id(sc.nextInt());

                                sc.nextLine();

                                System.out.print("Enter Name: ");
                                updateUser.setUser_name(sc.nextLine());

                                System.out.print("Enter Email: ");
                                updateUser.setEmail(sc.nextLine());

                                System.out.print("Enter Password: ");
                                updateUser.setPassword(sc.nextLine());

                                System.out.print("Enter Role: ");
                                updateUser.setRole(sc.nextLine());

                                System.out.println(
                                        userDAO.updateUser(updateUser));

                                break;

                            case 5:

                                System.out.print("Enter User ID: ");
                                int deleteUserId = sc.nextInt();

                                System.out.println(
                                        userDAO.deleteUser(deleteUserId));

                                break;

                            case 6:

                                sc.nextLine();

                                System.out.print("Email: ");
                                String email = sc.nextLine();

                                System.out.print("Password: ");
                                String password = sc.nextLine();

                                User loginUser =
                                        userDAO.login(email, password);

                                if (loginUser != null) {

                                    System.out.println(
                                            "Login Successful");

                                    System.out.println(
                                            "Welcome "
                                                    + loginUser.getUser_name());

                                    System.out.println(
                                            "Role: "
                                                    + loginUser.getRole());

                                } else {

                                    System.out.println(
                                            "Invalid Email or Password");
                                }

                                break;

                            case 7:
                                userMenu = false;
                                break;

                            default:
                                System.out.println("Invalid Choice");
                        }
                    }

                    break;



                case 2:

                    boolean categoryMenu = true;

                    while (categoryMenu) {

                        System.out.println("\n---------- CATEGORY MANAGEMENT ----------");
                        System.out.println("1. Add Category");
                        System.out.println("2. View All Categories");
                        System.out.println("3. Find Category");
                        System.out.println("4. Update Category");
                        System.out.println("5. Delete Category");
                        System.out.println("6. Back");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();

                        switch (ch) {

                            case 1:

                                Category category = new Category();

                                System.out.print("Category ID: ");
                                category.setCatogory_id(sc.nextInt());

                                sc.nextLine();

                                System.out.print("Category Name: ");
                                category.setCatogory_name(sc.nextLine());

                                System.out.println(
                                        categoryDAO.addCategory(category));

                                break;

                            case 2:

                                List<Category> categories =
                                        categoryDAO.getAllCategories();

                                for (Category c : categories) {

                                    System.out.println(
                                            "Category ID: "
                                                    + c.getCatogory_id());

                                    System.out.println(
                                            "Category Name: "
                                                    + c.getCatogory_name());

                                    System.out.println("-------------------");
                                }

                                break;

                            case 3:

                                System.out.print("Category ID: ");
                                int categoryId = sc.nextInt();

                                Category foundCategory =
                                        categoryDAO.getCategoryById(categoryId);

                                if (foundCategory != null) {

                                    System.out.println("Category Found");
                                    System.out.println(
                                            foundCategory.getCatogory_id());
                                    System.out.println(
                                            foundCategory.getCatogory_name());

                                } else {

                                    System.out.println(
                                            "Category Not Found");
                                }

                                break;

                            case 4:

                                Category updateCategory =
                                        new Category();

                                System.out.print("Category ID: ");
                                updateCategory.setCatogory_id(
                                        sc.nextInt());

                                sc.nextLine();

                                System.out.print("New Category Name: ");
                                updateCategory.setCatogory_name(
                                        sc.nextLine());

                                System.out.println(
                                        categoryDAO.updateCategory(
                                                updateCategory));

                                break;

                            case 5:

                                System.out.print("Category ID: ");
                                int deleteCategoryId = sc.nextInt();

                                System.out.println(
                                        categoryDAO.deleteCategory(
                                                deleteCategoryId));

                                break;

                            case 6:
                                categoryMenu = false;
                                break;

                            default:
                                System.out.println("Invalid Choice");
                        }
                    }

                    break;



                case 3:

                    boolean productMenu = true;

                    while (productMenu) {

                        System.out.println("\n---------- PRODUCT MANAGEMENT ----------");
                        System.out.println("1. Add Product");
                        System.out.println("2. View All Products");
                        System.out.println("3. Find Product");
                        System.out.println("4. Update Product");
                        System.out.println("5. Delete Product");
                        System.out.println("6. Products With Category");
                        System.out.println("7. Search By Category");
                        System.out.println("8. Search By Name");
                        System.out.println("9. Search By Price");
                        System.out.println("10. Update Stock");
                        System.out.println("11. Back");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();

                        switch (ch) {

                            case 1:

                                Product product = new Product();

                                System.out.print("Product ID: ");
                                product.setProduct_id(sc.nextInt());

                                sc.nextLine();

                                System.out.print("Product Name: ");
                                product.setProduct_name(sc.nextLine());

                                System.out.print("Price: ");
                                product.setPrice(sc.nextDouble());

                                System.out.print("Stock Quantity: ");
                                product.setStock_quantity(sc.nextInt());

                                System.out.print("Category ID: ");
                                product.setCategory_id(sc.nextInt());

                                System.out.println(
                                        productDAO.addProduct(product));

                                break;

                            case 2:

                                List<Product> products =
                                        productDAO.getAllProducts();

                                for (Product p : products) {

                                    System.out.println(
                                            "Product ID: "
                                                    + p.getProduct_id());

                                    System.out.println(
                                            "Name: "
                                                    + p.getProduct_name());

                                    System.out.println(
                                            "Price: "
                                                    + p.getPrice());

                                    System.out.println(
                                            "Stock: "
                                                    + p.getStock_quantity());

                                    System.out.println(
                                            "Category ID: "
                                                    + p.getCategory_id());

                                    System.out.println("-------------------");
                                }

                                break;

                            case 3:

                                System.out.print("Product ID: ");
                                int productId = sc.nextInt();

                                Product foundProduct =
                                        productDAO.getProductById(productId);

                                if (foundProduct != null) {

                                    System.out.println("Product Found");
                                    System.out.println(
                                            "ID: "
                                                    + foundProduct.getProduct_id());
                                    System.out.println(
                                            "Name: "
                                                    + foundProduct.getProduct_name());
                                    System.out.println(
                                            "Price: "
                                                    + foundProduct.getPrice());
                                    System.out.println(
                                            "Stock: "
                                                    + foundProduct.getStock_quantity());
                                    System.out.println(
                                            "Category ID: "
                                                    + foundProduct.getCategory_id());

                                } else {

                                    System.out.println(
                                            "Product Not Found");
                                }

                                break;

                            case 4:

                                Product updateProduct =
                                        new Product();

                                System.out.print("Product ID: ");
                                updateProduct.setProduct_id(
                                        sc.nextInt());

                                sc.nextLine();

                                System.out.print("Product Name: ");
                                updateProduct.setProduct_name(
                                        sc.nextLine());

                                System.out.print("Price: ");
                                updateProduct.setPrice(
                                        sc.nextDouble());

                                System.out.print("Stock: ");
                                updateProduct.setStock_quantity(
                                        sc.nextInt());

                                System.out.print("Category ID: ");
                                updateProduct.setCategory_id(
                                        sc.nextInt());

                                System.out.println(
                                        productDAO.updateProduct(
                                                updateProduct));

                                break;

                            case 5:

                                System.out.print("Product ID: ");
                                int deleteProductId = sc.nextInt();

                                System.out.println(
                                        productDAO.deleteProduct(
                                                deleteProductId));

                                break;

                            case 6:

                                List<Product> joinProducts =
                                        productDAO.getProductsWithCategory();

                                for (Product p : joinProducts) {

                                    System.out.println(
                                            "Product ID: "
                                                    + p.getProduct_id());

                                    System.out.println(
                                            "Product: "
                                                    + p.getProduct_name());

                                    System.out.println(
                                            "Category: "
                                                    + p.getCategory_name());

                                    System.out.println("-------------------");
                                }

                                break;

                            case 7:

                                System.out.print("Category ID: ");
                                int catId = sc.nextInt();

                                List<Product> categoryProducts =
                                        productDAO.getProductsByCategory(catId);

                                for (Product p : categoryProducts) {

                                    System.out.println(
                                            p.getProduct_name()
                                                    + " - "
                                                    + p.getPrice());
                                }

                                break;

                            case 8:

                                sc.nextLine();

                                System.out.print("Product Name: ");
                                String name = sc.nextLine();

                                List<Product> nameProducts =
                                        productDAO.searchProductsByName(name);

                                for (Product p : nameProducts) {

                                    System.out.println(
                                            p.getProduct_id()
                                                    + " - "
                                                    + p.getProduct_name()
                                                    + " - "
                                                    + p.getPrice());
                                }

                                break;

                            case 9:

                                System.out.print("Minimum Price: ");
                                double min = sc.nextDouble();

                                System.out.print("Maximum Price: ");
                                double max = sc.nextDouble();

                                List<Product> priceProducts =
                                        productDAO.searchProductsByPrice(
                                                min, max);

                                for (Product p : priceProducts) {

                                    System.out.println(
                                            p.getProduct_name()
                                                    + " - "
                                                    + p.getPrice());
                                }

                                break;

                            case 10:

                                System.out.print("Product ID: ");
                                int stockProductId = sc.nextInt();

                                System.out.print("New Quantity: ");
                                int quantity = sc.nextInt();

                                System.out.println(
                                        productDAO.updateStock(
                                                stockProductId,
                                                quantity));

                                break;

                            case 11:
                                productMenu = false;
                                break;

                            default:
                                System.out.println("Invalid Choice");
                        }
                    }

                    break;


                // ================= CART =================

                case 4:

                    boolean cartMenu = true;

                    while (cartMenu) {

                        System.out.println("\n---------- CART MANAGEMENT ----------");
                        System.out.println("1. Add Cart");
                        System.out.println("2. Find Cart By User");
                        System.out.println("3. Delete Cart");
                        System.out.println("4. Check Cart Exists");
                        System.out.println("5. Back");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();

                        switch (ch) {

                            case 1:

                                Cart cart = new Cart();

                                System.out.print("Cart ID: ");
                                cart.setCart_id(sc.nextInt());

                                System.out.print("User ID: ");
                                cart.setUser_id(sc.nextInt());

                                System.out.println(
                                        cartDAO.addCart(cart));

                                break;

                            case 2:

                                System.out.print("User ID: ");
                                int cartUserId = sc.nextInt();

                                Cart foundCart =
                                        cartDAO.getCartByUserId(
                                                cartUserId);

                                if (foundCart != null) {

                                    System.out.println("Cart Found");
                                    System.out.println(
                                            "Cart ID: "
                                                    + foundCart.getCart_id());
                                    System.out.println(
                                            "User ID: "
                                                    + foundCart.getUser_id());

                                } else {

                                    System.out.println(
                                            "Cart Not Found");
                                }

                                break;

                            case 3:

                                System.out.print("Cart ID: ");
                                int deleteCartId = sc.nextInt();

                                System.out.println(
                                        cartDAO.deleteCart(
                                                deleteCartId));

                                break;

                            case 4:

                                System.out.print("Cart ID: ");
                                int checkCartId = sc.nextInt();

                                System.out.println(
                                        cartDAO.cartExists(
                                                checkCartId));

                                break;

                            case 5:
                                cartMenu = false;
                                break;

                            default:
                                System.out.println("Invalid Choice");
                        }
                    }

                    break;




                case 5:

                    boolean cartItemMenu = true;

                    while (cartItemMenu) {

                        System.out.println("\n---------- CART ITEM MANAGEMENT ----------");
                        System.out.println("1. Add Cart Item");
                        System.out.println("2. View Cart Items");
                        System.out.println("3. Find Cart Item");
                        System.out.println("4. Update Quantity");
                        System.out.println("5. Delete Cart Item");
                        System.out.println("6. Check Exists");
                        System.out.println("7. Back");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();

                        switch (ch) {

                            case 1:

                                CartItem cartItem =
                                        new CartItem();

                                System.out.print("Cart Item ID: ");
                                cartItem.setCart_item_id(
                                        sc.nextInt());

                                System.out.print("Cart ID: ");
                                cartItem.setCart_id(
                                        sc.nextInt());

                                System.out.print("Product ID: ");
                                cartItem.setProduct_id(
                                        sc.nextInt());

                                System.out.print("Quantity: ");
                                cartItem.setQuantity(
                                        sc.nextInt());

                                System.out.println(
                                        cartItemDAO.addCartItem(
                                                cartItem));

                                break;

                            case 2:

                                System.out.print("Cart ID: ");
                                int viewCartId = sc.nextInt();

                                List<CartItem> cartItems =
                                        cartItemDAO.getCartItemsByCartId(
                                                viewCartId);

                                for (CartItem item : cartItems) {

                                    System.out.println(
                                            "Cart Item ID: "
                                                    + item.getCart_item_id());

                                    System.out.println(
                                            "Product ID: "
                                                    + item.getProduct_id());

                                    System.out.println(
                                            "Quantity: "
                                                    + item.getQuantity());

                                    System.out.println("-------------------");
                                }

                                break;

                            case 3:

                                System.out.print("Cart Item ID: ");
                                int cartItemId = sc.nextInt();

                                CartItem foundItem =
                                        cartItemDAO.getCartItemById(
                                                cartItemId);

                                if (foundItem != null) {

                                    System.out.println("Cart Item Found");
                                    System.out.println(
                                            "Cart Item ID: "
                                                    + foundItem.getCart_item_id());
                                    System.out.println(
                                            "Cart ID: "
                                                    + foundItem.getCart_id());
                                    System.out.println(
                                            "Product ID: "
                                                    + foundItem.getProduct_id());
                                    System.out.println(
                                            "Quantity: "
                                                    + foundItem.getQuantity());

                                } else {

                                    System.out.println(
                                            "Cart Item Not Found");
                                }

                                break;

                            case 4:

                                System.out.print("Cart Item ID: ");
                                int updateItemId = sc.nextInt();

                                System.out.print("New Quantity: ");
                                int newQuantity = sc.nextInt();

                                System.out.println(
                                        cartItemDAO.updateQuantity(
                                                updateItemId,
                                                newQuantity));

                                break;

                            case 5:

                                System.out.print("Cart Item ID: ");
                                int deleteItemId = sc.nextInt();

                                System.out.println(
                                        cartItemDAO.deleteCartItem(
                                                deleteItemId));

                                break;

                            case 6:

                                System.out.print("Cart Item ID: ");
                                int checkItemId = sc.nextInt();

                                System.out.println(
                                        cartItemDAO.cartItemExists(
                                                checkItemId));

                                break;

                            case 7:
                                cartItemMenu = false;
                                break;

                            default:
                                System.out.println("Invalid Choice");
                        }
                    }

                    break;




                case 6:

                    boolean orderMenu = true;

                    while (orderMenu) {

                        System.out.println("\n---------- ORDER MANAGEMENT ----------");
                        System.out.println("1. Add Order");
                        System.out.println("2. View All Orders");
                        System.out.println("3. Find Order");
                        System.out.println("4. Find Orders By User");
                        System.out.println("5. Update Order Status");
                        System.out.println("6. Delete Order");
                        System.out.println("7. Check Order Exists");
                        System.out.println("8. Back");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();

                        switch (ch) {

                            case 1:

                                Order order = new Order();

                                System.out.print("Order ID: ");
                                order.setOrder_id(
                                        sc.nextInt());

                                System.out.print("User ID: ");
                                order.setUser_id(
                                        sc.nextInt());

                                sc.nextLine();

                                System.out.print("Order Date (YYYY-MM-DD): ");
                                order.setOrder_date(
                                        sc.nextLine());

                                System.out.print("Total Amount: ");
                                order.setTotal_amount(
                                        sc.nextDouble());

                                sc.nextLine();

                                System.out.print("Status: ");
                                order.setStatus(
                                        sc.nextLine());

                                System.out.println(
                                        orderDAO.addOrder(order));

                                break;

                            case 2:

                                List<Order> orders =
                                        orderDAO.getAllOrders();

                                for (Order o : orders) {

                                    System.out.println(
                                            "Order ID: "
                                                    + o.getOrder_id());

                                    System.out.println(
                                            "User ID: "
                                                    + o.getUser_id());

                                    System.out.println(
                                            "Order Date: "
                                                    + o.getOrder_date());

                                    System.out.println(
                                            "Total: "
                                                    + o.getTotal_amount());

                                    System.out.println(
                                            "Status: "
                                                    + o.getStatus());

                                    System.out.println("-------------------");
                                }

                                break;

                            case 3:

                                System.out.print("Order ID: ");
                                int orderId = sc.nextInt();

                                Order foundOrder =
                                        orderDAO.getOrderById(
                                                orderId);

                                if (foundOrder != null) {

                                    System.out.println("Order Found");
                                    System.out.println(
                                            "Order ID: "
                                                    + foundOrder.getOrder_id());
                                    System.out.println(
                                            "User ID: "
                                                    + foundOrder.getUser_id());
                                    System.out.println(
                                            "Date: "
                                                    + foundOrder.getOrder_date());
                                    System.out.println(
                                            "Total: "
                                                    + foundOrder.getTotal_amount());
                                    System.out.println(
                                            "Status: "
                                                    + foundOrder.getStatus());

                                } else {

                                    System.out.println(
                                            "Order Not Found");
                                }

                                break;

                            case 4:

                                System.out.print("User ID: ");
                                int orderUserId = sc.nextInt();

                                List<Order> userOrders =
                                        orderDAO.getOrdersByUserId(
                                                orderUserId);

                                for (Order o : userOrders) {

                                    System.out.println(
                                            "Order ID: "
                                                    + o.getOrder_id());

                                    System.out.println(
                                            "Total: "
                                                    + o.getTotal_amount());

                                    System.out.println(
                                            "Status: "
                                                    + o.getStatus());

                                    System.out.println("-------------------");
                                }

                                break;

                            case 5:

                                System.out.print("Order ID: ");
                                int updateOrderId = sc.nextInt();

                                sc.nextLine();

                                System.out.print("New Status: ");
                                String status = sc.nextLine();

                                System.out.println(
                                        orderDAO.updateOrderStatus(
                                                updateOrderId,
                                                status));

                                break;

                            case 6:

                                System.out.print("Order ID: ");
                                int deleteOrderId = sc.nextInt();

                                System.out.println(
                                        orderDAO.deleteOrder(
                                                deleteOrderId));

                                break;

                            case 7:

                                System.out.print("Order ID: ");
                                int checkOrderId = sc.nextInt();

                                System.out.println(
                                        orderDAO.orderExists(
                                                checkOrderId));

                                break;

                            case 8:
                                orderMenu = false;
                                break;

                            default:
                                System.out.println("Invalid Choice");
                        }
                    }

                    break;




                case 7:

                    boolean orderItemMenu = true;

                    while (orderItemMenu) {

                        System.out.println("\n---------- ORDER ITEM MANAGEMENT ----------");
                        System.out.println("1. Add Order Item");
                        System.out.println("2. View Order Items");
                        System.out.println("3. Find Order Item");
                        System.out.println("4. Update Quantity");
                        System.out.println("5. Delete Order Item");
                        System.out.println("6. Check Exists");
                        System.out.println("7. Back");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();

                        switch (ch) {

                            case 1:

                                OrderItem orderItem =
                                        new OrderItem();

                                System.out.print("Order Item ID: ");
                                orderItem.setOrder_item_id(
                                        sc.nextInt());

                                System.out.print("Order ID: ");
                                orderItem.setOrder_id(
                                        sc.nextInt());

                                System.out.print("Product ID: ");
                                orderItem.setProduct_id(
                                        sc.nextInt());

                                System.out.print("Quantity: ");
                                orderItem.setQuantity(
                                        sc.nextInt());

                                System.out.print("Price: ");
                                orderItem.setPrice(
                                        sc.nextDouble());

                                System.out.println(
                                        orderItemDAO.addOrderItem(
                                                orderItem));

                                break;

                            case 2:

                                System.out.print("Order ID: ");
                                int viewOrderId = sc.nextInt();

                                List<OrderItem> orderItems =
                                        orderItemDAO
                                                .getOrderItemsByOrderId(
                                                        viewOrderId);

                                for (OrderItem item : orderItems) {

                                    System.out.println(
                                            "Order Item ID: "
                                                    + item.getOrder_item_id());

                                    System.out.println(
                                            "Product ID: "
                                                    + item.getProduct_id());

                                    System.out.println(
                                            "Quantity: "
                                                    + item.getQuantity());

                                    System.out.println(
                                            "Price: "
                                                    + item.getPrice());

                                    System.out.println("-------------------");
                                }

                                break;

                            case 3:

                                System.out.print("Order Item ID: ");
                                int findOrderItemId =
                                        sc.nextInt();

                                OrderItem foundOrderItem =
                                        orderItemDAO
                                                .getOrderItemById(
                                                        findOrderItemId);

                                if (foundOrderItem != null) {

                                    System.out.println(
                                            "Order Item Found");

                                    System.out.println(
                                            "Order Item ID: "
                                                    + foundOrderItem
                                                    .getOrder_item_id());

                                    System.out.println(
                                            "Order ID: "
                                                    + foundOrderItem
                                                    .getOrder_id());

                                    System.out.println(
                                            "Product ID: "
                                                    + foundOrderItem
                                                    .getProduct_id());

                                    System.out.println(
                                            "Quantity: "
                                                    + foundOrderItem
                                                    .getQuantity());

                                    System.out.println(
                                            "Price: "
                                                    + foundOrderItem
                                                    .getPrice());

                                } else {

                                    System.out.println(
                                            "Order Item Not Found");
                                }

                                break;

                            case 4:

                                System.out.print("Order Item ID: ");
                                int updateOrderItemId =
                                        sc.nextInt();

                                System.out.print("New Quantity: ");
                                int orderQuantity =
                                        sc.nextInt();

                                System.out.println(
                                        orderItemDAO.updateQuantity(
                                                updateOrderItemId,
                                                orderQuantity));

                                break;

                            case 5:

                                System.out.print("Order Item ID: ");
                                int deleteOrderItemId =
                                        sc.nextInt();

                                System.out.println(
                                        orderItemDAO
                                                .deleteOrderItem(
                                                        deleteOrderItemId));

                                break;

                            case 6:

                                System.out.print("Order Item ID: ");
                                int checkOrderItemId =
                                        sc.nextInt();

                                System.out.println(
                                        orderItemDAO
                                                .orderItemExists(
                                                        checkOrderItemId));

                                break;

                            case 7:
                                orderItemMenu = false;
                                break;

                            default:
                                System.out.println("Invalid Choice");
                        }
                    }

                    break;




                case 8:

                    running = false;

                    System.out.println(
                            "Thank you for using E-Commerce System!");

                    break;

                default:

                    System.out.println(
                            "Invalid Choice");
            }
        }

        sc.close();
    }
}