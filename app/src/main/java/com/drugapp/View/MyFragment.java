package com.drugapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.drugapp.Activity.LoginActivity;
import com.drugapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyFragment extends Fragment {

    View view;
    @BindView(R.id.linear_user)
    RelativeLayout linearUser;
    @BindView(R.id.line_password)
    LinearLayout linePassword;
    @BindView(R.id.line_nopay)
    LinearLayout lineNopay;
    @BindView(R.id.line_supay)
    LinearLayout lineSupay;
    @BindView(R.id.line_address)
    LinearLayout lineAddress;
    @BindView(R.id.user_loginout)
    Button userLoginout;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.from(getActivity()).inflate(R.layout.fragment_my, null);
            initView();
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.linear_user, R.id.line_password, R.id.line_nopay, R.id.line_supay, R.id.line_address, R.id.user_loginout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear_user:
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.line_password:
                break;
            case R.id.line_nopay:
                break;
            case R.id.line_supay:
                break;
            case R.id.line_address:
                break;
            case R.id.user_loginout:
                break;
        }
    }
}
