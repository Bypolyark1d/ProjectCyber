package com.example.projectcyber;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcyber.Recycler.ItemProduct;
import com.example.projectcyber.Recycler.MyAdapter;
import com.example.projectcyber.databinding.FragmentNotificationsBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {
    private FragmentNotificationsBinding binding;
    static Cart CurrentCart = new Cart();
    TextView priceOrer;
    int priceCount = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        root.findViewById(R.id.buttonCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApi myApi = RetrofitClientInstance.getInstance();

                Call<Void> call = myApi.saveOrder(JoinActivity.CurrentUser.getId(),priceCount);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response)
                    {
                        if (response.code() != 200)
                        {

                            return;
                        }
                        Navigation.findNavController(root).navigate(R.id.action_navigation_notifications_to_navigation_profile);
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t)
                    {

                    }
                });
            }
        });
        priceOrer = root.findViewById(R.id.priceOrder_Product);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MyApi myApi = RetrofitClientInstance.getInstance();
        Call<Cart> call = myApi.getCartProduct(JoinActivity.CurrentUser.getId());
                call.enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response)
                    {
                        if (response.code() != 200)
                        {
                            return;
                        }
                        CurrentCart = response.body();
                        List<ItemProduct> ListProduct = CurrentCart.getListproduct();
                        for(ItemProduct item:ListProduct)
                        {
                            priceCount+=item.getPrice();
                        }
                        priceOrer.setText("Цена Заказа: "+priceCount);
                        MyAdapter myAdapter =new MyAdapter(getContext(),ListProduct,null,getActivity());
                        MyAdapter.ItemClickListener itemClickListener = new MyAdapter.ItemClickListener()
                        {
                            @Override
                            public void onItemClick(ItemProduct myItem)
                            {
                                DashboardFragment fragment =new DashboardFragment();
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("Key",myItem);
                                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_notifications_to_product_nav,bundle);
                            }
                            @Override
                            public void onItemLongClick(ItemProduct myItem)
                            {

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setCancelable(true)
                                        .setMessage("Удалить Товар?")
                                        .setIcon(R.drawable.icon_proj)
                                        .setTitle(""+myItem.getName())
                                        .setView(R.layout.alert_dialog);
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                Button buttonDelete = alertDialog.getWindow().findViewById(R.id.delete_button);
                                Button buttonCancel = alertDialog.getWindow().findViewById(R.id.cancel_button);
                                buttonDelete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view)
                                    {
                                        Thread thread = new Thread(new Runnable()
                                        {
                                            @Override
                                            public void run()
                                            {
                                                MyApi myApi = RetrofitClientInstance.getInstance();
                                                Call<Void> call = myApi.deleteProduct(CurrentCart.getId(),myItem.getId());
                                                call.enqueue(new Callback<Void>() {
                                                    @Override
                                                    public void onResponse(Call<Void> call, Response<Void> response)
                                                    {
                                                        if (response.code() != 204)
                                                        {
                                                            return;
                                                        }
                                                        ListProduct.remove(myItem);
                                                        priceCount-=myItem.getPrice();
                                                        priceOrer.setText("Цена Заказа: "+priceCount);
                                                        myAdapter.notifyDataSetChanged();
                                                    }
                                                    @Override
                                                    public void onFailure(Call<Void> call, Throwable t) {

                                                    }
                                                });
                                                alertDialog.cancel();
                                            }
                                        });
                                        thread.start();
                                        try
                                        {
                                            thread.join();
                                        }
                                        catch (InterruptedException e)
                                        {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                });
                                buttonCancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        alertDialog.cancel();
                                    }
                                });
                            }
                        };
                        myAdapter.setMyItemListener(itemClickListener);
                        recyclerView.setAdapter(myAdapter);
                    }
                    @Override
                    public void onFailure(Call<Cart> call, Throwable t) {

                    }
                });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}