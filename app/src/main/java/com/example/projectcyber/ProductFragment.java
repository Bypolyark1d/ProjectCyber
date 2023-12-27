package com.example.projectcyber;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.projectcyber.Recycler.ItemProduct;
import com.example.projectcyber.databinding.FragmentProductBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFragment extends Fragment {

    ItemProduct CurrentProduct;
    TextView details;
    TextView name;
    Activity activity;
    TextView price;
    ImageView image;

    private FragmentProductBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        activity =getActivity();
        root.findViewById(R.id.backButtonOnprod).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_product_nav_to_navigation_home);
            }
        });
        root.findViewById(R.id.buttonCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApi myApi = RetrofitClientInstance.getInstance();
                myApi.saveProduct(JoinActivity.CurrentUser.getId(),CurrentProduct.getId())
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.code()!=200)
                                {

                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle mBundle = new Bundle();
        mBundle = getArguments();
        if(getArguments()!=null)
        {
            CurrentProduct = (ItemProduct) mBundle.getSerializable("Key");
        }
        name = view.findViewById(R.id.text_NameProduct);
        image = view.findViewById(R.id.ImageProduct);
        price = view.findViewById(R.id.priceTextproduct_det);
        details = view.findViewById(R.id.detailsProductText);
        name.setText(CurrentProduct.getName().toString());
        price.setText("" + CurrentProduct.getPrice());
        details.setMovementMethod(new ScrollingMovementMethod());
        details.setText(CurrentProduct.getDetails().toString());
        new RetrofitClientInstance.ImageBitmapUriTask(getActivity(),image).execute(RetrofitClientInstance.BASE_URL+"/product/" + CurrentProduct.getId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}