package DTO;

public class Product {

    private int product_id;
    private String product_name;
    private double price;
    private int stock_quantity;
    private int category_id;
    private String category_name;




    public Product() {
        super();
    }


    public Product(int product_id, String product_name, double price, int stock_quantity, int category_id, String category_name) {
        super();
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.category_id = category_id;
        this.category_name = category_name;
    }


    public int getProduct_id() {
        return product_id;
    }


    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }


    public String getProduct_name() {
        return product_name;
    }


    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public int getStock_quantity() {
        return stock_quantity;
    }


    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }


    public int getCategory_id() {
        return category_id;
    }


    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

}
