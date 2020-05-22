package by.lifetech.ishop.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class Order implements Serializable {
    public static final long serialVersionUID = 8603189047168085956L;

    private int id;
    private Map<Item, Integer> itemMap;
    private String state;
    private Date createDate;
    private String paymentType;
    private String comment;
    private String address;

    public Order(int id, Map<Item, Integer> itemMap, String state, Date createDate, String paymentType, String comment, String address) {
        this.id = id;
        this.itemMap = itemMap;
        this.state = state;
        this.createDate = createDate;
        this.paymentType = paymentType;
        this.comment = comment;
        this.address = address;
    }

    public Order() {}

    public int getId() {
        return id;
    }

    public Map<Item, Integer> getItemMap() {
        return itemMap;
    }

    public String getState() {
        return state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getComment() {
        return comment;
    }

    public String getAddress() {
        return address;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                itemMap.equals(order.itemMap) &&
                state.equals(order.state) &&
                createDate.equals(order.createDate) &&
                Objects.equals(paymentType, order.paymentType) &&
                Objects.equals(comment, order.comment) &&
                Objects.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemMap, state, createDate, paymentType, comment, address);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", itemMap=" + itemMap +
                ", state='" + state + '\'' +
                ", createDate=" + createDate +
                ", paymentType='" + paymentType + '\'' +
                ", comment='" + comment + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
