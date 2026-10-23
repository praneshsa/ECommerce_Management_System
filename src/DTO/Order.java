package DTO;

public class Order {

    private int order_id;
    private int user_id;
    private String order_date;
    private double total_amount;
    private String status;

    public Order() {
    }

    public Order(int order_id, int user_id, String order_date,
                 double total_amount, String status) {

        this.order_id = order_id;
        this.user_id = user_id;
        this.order_date = order_date;
        this.total_amount = total_amount;
        this.status = status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}