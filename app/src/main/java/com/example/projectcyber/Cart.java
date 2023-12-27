package com.example.projectcyber;

import com.example.projectcyber.Recycler.ItemProduct;

import java.util.List;

public class Cart
{
    private Integer id;
    private List<ItemProduct> listproduct;

    public Cart(Integer id, List<ItemProduct> listproduct) {
        this.id = id;
        this.listproduct = listproduct;
    }
    public Cart() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ItemProduct> getListproduct() {
        return listproduct;
    }

    public void setListproduct(List<ItemProduct> listproduct) {
        this.listproduct = listproduct;
    }
}
