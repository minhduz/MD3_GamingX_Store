package ra.model.entity;

public class OrderDetail {
    private int orderDetailOrderID;
    private int orderDetailGameID;
    private String orderDetailMainImage;
    private String orderDetailGameName;
    private float orderDetailGamePrice;
    private int orderDetailGameDiscount;
    private int orderDetailQuantity;
    private float orderDetailAmount;

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailOrderID, int orderDetailGameID, String orderDetailMainImage, String orderDetailGameName, float orderDetailGamePrice, int orderDetailGameDiscount, int orderDetailQuantity, float orderDetailAmount) {
        this.orderDetailOrderID = orderDetailOrderID;
        this.orderDetailGameID = orderDetailGameID;
        this.orderDetailMainImage = orderDetailMainImage;
        this.orderDetailGameName = orderDetailGameName;
        this.orderDetailGamePrice = orderDetailGamePrice;
        this.orderDetailGameDiscount = orderDetailGameDiscount;
        this.orderDetailQuantity = orderDetailQuantity;
        this.orderDetailAmount = orderDetailAmount;
    }

    public int getOrderDetailOrderID() {
        return orderDetailOrderID;
    }

    public void setOrderDetailOrderID(int orderDetailOrderID) {
        this.orderDetailOrderID = orderDetailOrderID;
    }

    public int getOrderDetailGameID() {
        return orderDetailGameID;
    }

    public void setOrderDetailGameID(int orderDetailGameID) {
        this.orderDetailGameID = orderDetailGameID;
    }

    public String getOrderDetailMainImage() {
        return orderDetailMainImage;
    }

    public void setOrderDetailMainImage(String orderDetailMainImage) {
        this.orderDetailMainImage = orderDetailMainImage;
    }

    public String getOrderDetailGameName() {
        return orderDetailGameName;
    }

    public void setOrderDetailGameName(String orderDetailGameName) {
        this.orderDetailGameName = orderDetailGameName;
    }

    public float getOrderDetailGamePrice() {
        return orderDetailGamePrice;
    }

    public void setOrderDetailGamePrice(float orderDetailGamePrice) {
        this.orderDetailGamePrice = orderDetailGamePrice;
    }

    public int getOrderDetailGameDiscount() {
        return orderDetailGameDiscount;
    }

    public void setOrderDetailGameDiscount(int orderDetailGameDiscount) {
        this.orderDetailGameDiscount = orderDetailGameDiscount;
    }

    public int getOrderDetailQuantity() {
        return orderDetailQuantity;
    }

    public void setOrderDetailQuantity(int orderDetailQuantity) {
        this.orderDetailQuantity = orderDetailQuantity;
    }

    public float getOrderDetailAmount() {
        return orderDetailAmount;
    }

    public void setOrderDetailAmount(float orderDetailAmount) {
        this.orderDetailAmount = orderDetailAmount;
    }
}
