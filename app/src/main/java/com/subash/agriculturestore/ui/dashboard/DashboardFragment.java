package com.subash.agriculturestore.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.subash.agriculturestore.R;

public class DashboardFragment extends Fragment {
    private RecyclerView recyclerView;
    private Button btn_gyro, btn_acc, btn_proxi;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        btn_gyro = root.findViewById(R.id.btn_gyro);
        btn_acc = root.findViewById(R.id.btn_acc);
        btn_proxi = root.findViewById(R.id.btn_proxi);

        btn_gyro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Gyro.class);
                getActivity().startActivity(intent);
            }
        });
        btn_proxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Proximity.class);
                getActivity().startActivity(intent);
            }
        });
        btn_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Accelerometer.class);
                getActivity().startActivity(intent);
            }
        });

        return root;
    }
}