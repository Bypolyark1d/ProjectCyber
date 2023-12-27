package com.example.projectcyber;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcyber.Recycler.ItemProduct;
import com.example.projectcyber.Recycler.MyAdapter;
import com.example.projectcyber.databinding.FragmentDashboardBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment{
        private RecyclerView recyclerView;
        Integer Category_id;
        private FragmentDashboardBinding binding;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            binding = FragmentDashboardBinding.inflate(inflater, container, false);
            View root = binding.getRoot();
            root.findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_dashboard_to_navigation_home);
                }
            });
            return root;

        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            Bundle mBundle = new Bundle();
            mBundle = getArguments();
            if (getArguments() != null) {
                Category_id = mBundle.getInt("ID");
            }
            recyclerView = view.findViewById(R.id.recyclerViewProduct);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            //instance for interface
            MyApi myApi = RetrofitClientInstance.getInstance();
            Call<List<ItemProduct>> call = myApi.getProduct(Category_id);
            call.enqueue(new Callback<List<ItemProduct>>() {
                @Override
                public void onResponse(Call<List<ItemProduct>> call, Response<List<ItemProduct>> response) {
                    if (response.code() != 200) {
                        return;
                    }
                    List<ItemProduct> ListProduct = response.body();
                    MyAdapter myAdapter = new MyAdapter(getContext(), ListProduct, new MyAdapter.ItemClickListener() {
                        @Override
                        public void onItemClick(ItemProduct myItem) {
                            com.example.projectcyber.DashboardFragment fragment = new com.example.projectcyber.DashboardFragment();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("Key", myItem);
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_dashboard_to_product_nav, bundle);
                        }

                        @Override
                        public void onItemLongClick(ItemProduct myItem) {

                        }
                    }, getActivity());
                    recyclerView.setAdapter(myAdapter);
                }

                @Override
                public void onFailure(Call<List<ItemProduct>> call, Throwable t) {
                }
            });
        }
}
