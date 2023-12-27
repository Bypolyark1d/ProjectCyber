package com.example.projectcyber;

import com.example.projectcyber.Recycler.ItemProduct;
import com.example.projectcyber.Recycler.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApi
{
    @POST("login")
    Call<User> getUser(@Body ResponseRequster body);
    @GET("/category/{id}")
    Call<List<ItemProduct>> getProduct(@Path("id") Integer category_id);
    @POST("/user/{userId}/cart/product/{productId}")
    Call<Void> saveProduct(@Path("userId")Integer userId, @Path("productId") Integer productId);
    @GET("/user/{id}/cart")
    Call<Cart> getCartProduct(@Path("id")Integer id);
    @DELETE("/cart/{cartId}/product/{productId}")
    Call<Void> deleteProduct(@Path("cartId")Integer cartId,@Path("productId")Integer productId);
    @GET("/user/{id}/order/")
    Call<List<Order>> getAllOrder(@Path("id")Integer id);
    @POST("/user/{id}/order/")
    Call<Void> saveOrder(@Path("id")Integer id, @Query("price")Integer price);

    @POST("/sign")
    Call<User> addUser(@Body ResponseRequster body);
}
