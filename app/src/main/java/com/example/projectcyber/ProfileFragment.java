package com.example.projectcyber;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcyber.Recycler.Order;
import com.example.projectcyber.Recycler.OrderAdapter;
import com.example.projectcyber.databinding.FragmentProfileBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewOrder);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MyApi myApi = RetrofitClientInstance.getInstance();
        Call<List<Order>> call = myApi.getAllOrder(JoinActivity.CurrentUser.getId());
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.code() != 200)
                {
                    return;
                }
                List<Order> orderList = response.body();
                OrderAdapter orderAdapter = new OrderAdapter(getContext(),orderList);
                recyclerView.setAdapter(orderAdapter);
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {

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