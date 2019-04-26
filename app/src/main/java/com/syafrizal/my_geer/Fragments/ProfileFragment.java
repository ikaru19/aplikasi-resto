package com.syafrizal.my_geer.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.syafrizal.my_geer.Activities.EmptyActivity;
import com.syafrizal.my_geer.Model.Constant;
import com.syafrizal.my_geer.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    SharedPreferences preferences ;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        preferences = this.getActivity().getSharedPreferences(Constant.SHARED_PREF, Context.MODE_PRIVATE);
        Button btnLogout = view.findViewById(R.id.btnLogoutProfile);
        TextView txtName = view.findViewById(R.id.txtProfileName);
        TextView txtEmail = view.findViewById(R.id.txtProfileEmail);
        TextView txtPhone = view.findViewById(R.id.txtProfileNumber);

        txtName.setText(preferences.getString(Constant.NAME,null));
        txtEmail.setText("Email : "+ preferences.getString(Constant.EMAIL,null));
        txtPhone.setText("Phone : "+preferences.getString(Constant.PHONE,null));

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileLogoutClick();
            }
        });


        return view;
    }

    public void profileLogoutClick(){
        preferences.edit().clear().commit();
        Intent intent = new Intent(this.getActivity(), EmptyActivity.class);
        startActivity(intent);
        this.getActivity().finish();

    }

}
