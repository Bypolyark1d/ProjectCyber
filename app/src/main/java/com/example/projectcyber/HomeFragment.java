package com.example.projectcyber;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.projectcyber.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment{
        private FragmentHomeBinding binding;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            binding = FragmentHomeBinding.inflate(inflater, container, false);
            View root = binding.getRoot();
            root.findViewById(R.id.mausepad).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer Category_id = Integer.parseInt(view.getTag().toString());
                    DashboardFragment fragment = new DashboardFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID", Category_id);
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_home_to_navigation_dashboard, bundle);
                }
            });
            root.findViewById(R.id.keyboard).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer Category_id = Integer.parseInt(view.getTag().toString());
                    DashboardFragment fragment = new DashboardFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID", Category_id);
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_home_to_navigation_dashboard, bundle);
                }
            });
            root.findViewById(R.id.Controller).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer Category_id = Integer.parseInt(view.getTag().toString());
                    DashboardFragment fragment = new DashboardFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID", Category_id);
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_home_to_navigation_dashboard, bundle);
                }
            });
            root.findViewById(R.id.Mause).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer Category_id = Integer.parseInt(view.getTag().toString());
                    DashboardFragment fragment = new DashboardFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID", Category_id);
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_home_to_navigation_dashboard, bundle);
                }
            });
            root.findViewById(R.id.headphone).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer Category_id = Integer.parseInt(view.getTag().toString());
                    DashboardFragment fragment = new DashboardFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID", Category_id);
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_home_to_navigation_dashboard, bundle);
                }
            });
            root.findViewById(R.id.microphone).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer Category_id = Integer.parseInt(view.getTag().toString());
                    DashboardFragment fragment = new DashboardFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID", Category_id);
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_home_to_navigation_dashboard, bundle);
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
