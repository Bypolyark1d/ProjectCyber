package com.example.projectcyber.Recycler;

public class Order
{
    private Integer id;
    private Integer priceorder;
    private String status;

    public Order(Integer id, Integer priceOrder, String status) {
        this.id = id;
        this.priceorder = priceOrder;
        this.status = status;
    }
    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPriceorder() {
        return priceorder;
    }

    public void setPriceorder(Integer priceorder) {
        this.priceorder = priceorder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
