package ra.model.entity;

import java.time.LocalDate;
import java.util.List;


public class Order {
    private int orderID;
    private int orderUserID;
    private String orderUserName;
    private String orderUserEmail;
    private LocalDate orderDate;
    private float orderTotalAmount;
    private boolean orderStatus;
    private List<Cart> listOrderCart;

    public Order() {
    }
    public Order(int orderID, int orderUserID, String orderUserName, String orderUserEmail, LocalDate orderDate, float orderTotalAmount, boolean orderStatus, List<Cart> listOrderCart) {
        this.orderID = orderID;
        this.orderUserID = orderUserID;
        this.orderUserName = orderUserName;
        this.orderUserEmail = orderUserEmail;
        this.orderDate = orderDate;
        this.orderTotalAmount = orderTotalAmount;
        this.orderStatus = orderStatus;
        this.listOrderCart = listOrderCart;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getOrderUserID() {
        return orderUserID;
    }

    public void setOrderUserID(int orderUserID) {
        this.orderUserID = orderUserID;
    }

    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }

    public String getOrderUserEmail() {
        return orderUserEmail;
    }

    public void setOrderUserEmail(String orderUserEmail) {
        this.orderUserEmail = orderUserEmail;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public float getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(float orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Cart> getListOrderCart() {
        return listOrderCart;
    }

    public void setListOrderCart(List<Cart> listOrderCart) {
        this.listOrderCart = listOrderCart;
    }
}
