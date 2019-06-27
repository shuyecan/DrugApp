package com.drugapp.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.drugapp.R;
import com.drugapp.adapter.Shopcaradapter;
import com.drugapp.been.Drugbeen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ShopFragment extends Fragment {
    View view;
    @BindView(R.id.text_not_compile)
    TextView textNotCompile;
    @BindView(R.id.line_not_compile)
    LinearLayout lineNotCompile;
    @BindView(R.id.recy_not_list)
    RecyclerView recyNotList;
    @BindView(R.id.swf_not_getdata)
    SwipeRefreshLayout swfNotGetdata;
    @BindView(R.id.checkbox_not_checkall)
    CheckBox checkboxNotCheckall;
    @BindView(R.id.text_not_delete)
    TextView textNotDelete;
    @BindView(R.id.Rel_notbottom)
    RelativeLayout RelNotbottom;
    boolean isdel = false;
    Shopcaradapter adapter;
    List<Drugbeen> drugbeenList =new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.from(getActivity()).inflate(R.layout.fragment_shopcar, null);
            ButterKnife.bind(this, view);
            initView();
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }

        return view;
    }

    private void initView() {

        for (int i =0;i<15;i++){
            Drugbeen drugbeen = new Drugbeen();
            drugbeen.setId(i);
            drugbeen.setDrugname("999感冒灵");
            drugbeen.setIscheck(false);
            drugbeen.setNum("1");
            drugbeenList.add(drugbeen);
        }

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyNotList.setLayoutManager(layoutManager);
        adapter = new  Shopcaradapter(drugbeenList,getActivity());
        recyNotList.setAdapter(adapter);


        checkboxNotCheckall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    adapter.chechall(true);
                } else {
                    adapter.chechall(false);
                }
            }
        });


        swfNotGetdata.setColorSchemeResources(R.color.view_blue);
        swfNotGetdata.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                drugbeenList.clear();
                for (int i =0;i<15;i++){
                    Drugbeen drugbeen = new Drugbeen();
                    drugbeen.setId(i);
                    drugbeen.setDrugname("999感冒灵");
                    drugbeen.setIscheck(false);
                    drugbeen.setNum("1");
                    drugbeenList.add(drugbeen);
                }
                swfNotGetdata.setRefreshing(false);
                adapter.notifyDataSetChanged();
            }
        });
    }


    @OnClick({R.id.line_not_compile, R.id.text_not_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_not_compile:
                if (isdel) {
                    isdel = false;
                    textNotCompile.setText("编辑");
                    textNotDelete.setText("结算");
                } else {
                    isdel = true;
                    textNotCompile.setText("取消");
                    textNotDelete.setText("删除");
                }

                break;
            case R.id.text_not_delete:
                if(isdel) {
                    final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                    builder.setMessage("是否清除已选中的消息通知？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.deleteItem();
                            checkboxNotCheckall.setChecked(false);
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }else {
                    Toast.makeText(getActivity(), "点击了结算", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
