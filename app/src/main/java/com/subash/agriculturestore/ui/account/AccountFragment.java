package com.subash.agriculturestore.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.subash.agriculturestore.LoginRegisterActivity;
import com.subash.agriculturestore.R;

public class AccountFragment extends Fragment {

    private AccountViewModel accountViewModel;
    private Button btnOpenLoginSignupFragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountViewModel =
                ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        //final TextView textView = root.findViewById(R.id.text_account);
        btnOpenLoginSignupFragment = root.findViewById(R.id.btnOpenLoginSignupFragment);

        btnOpenLoginSignupFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getActivity(), LoginRegisterActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}