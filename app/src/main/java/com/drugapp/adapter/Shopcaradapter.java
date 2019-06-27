package com.drugapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.drugapp.R;
import com.drugapp.View.CicleAddAndSubView;
import com.drugapp.been.Drugbeen;

import java.util.Iterator;
import java.util.List;

public class Shopcaradapter extends RecyclerView.Adapter<Shopcaradapter.Viewholder> {

    List<Drugbeen> list;
    Context context;

    public Shopcaradapter(List<Drugbeen> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Shopcaradapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(context==null){
            context = viewGroup.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_drug, viewGroup, false);
        final Viewholder holder = new Viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Shopcaradapter.Viewholder viewholder, int i) {
        final Drugbeen drugbeen = list.get(i);
        viewholder.text_drug_name.setText(drugbeen.getDrugname());
        viewholder.cicleAddAndSubView.setNum(Integer.valueOf(drugbeen.getNum()));
        viewholder.cicleAddAndSubView.setOnNumChangeListener(new CicleAddAndSubView.OnNumChangeListener() {
            @Override
            public void onNumChange(View view, int stype, int num) {
                    drugbeen.setNum(String.valueOf(num));
            }
        });
        if(drugbeen.isIscheck()){
            viewholder.check_not_itme.setChecked(true);
        }else {
            viewholder.check_not_itme.setChecked(false);
        }



        viewholder.check_not_itme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewholder.check_not_itme.isChecked()){
                    drugbeen.setIscheck(true);
                }else {
                    drugbeen.setIscheck(false);
                }
            }
        });

        viewholder.check_not_itme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        viewholder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(viewholder.check_not_itme.isChecked()){
                        drugbeen.setIscheck(false);
                        viewholder.check_not_itme.setChecked(false);
                    }else {
                        drugbeen.setIscheck(true);
                        viewholder.check_not_itme.setChecked(true);
                    }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public void deleteItem(){
        Iterator<Drugbeen> iterator = list.iterator();
        while (iterator.hasNext()){
            Drugbeen notificationEntity = iterator.next();
            if(notificationEntity.isIscheck()){
                iterator.remove();
            }
        }
        notifyDataSetChanged();
    }



    public void chechall(boolean t){
        Iterator<Drugbeen> iterator = list.iterator();
        while (iterator.hasNext()){
            Drugbeen notificationEntity = iterator.next();
            if(t){
                if(!notificationEntity.isIscheck()){
                    notificationEntity.setIscheck(true);
                }
            }else {
                if (notificationEntity.isIscheck()){
                    notificationEntity.setIscheck(false);
                }
            }
        }
        notifyDataSetChanged();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView img_drug;
        TextView text_drug_name;
        CicleAddAndSubView cicleAddAndSubView;
        CheckBox check_not_itme;
        RelativeLayout relativeLayout;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView;
            img_drug = itemView.findViewById(R.id.img_drug);
            text_drug_name= itemView.findViewById(R.id.text_drug_name);
            cicleAddAndSubView = itemView.findViewById(R.id.cicle_add);
            check_not_itme = itemView.findViewById(R.id.check_not_itme);
        }
    }
}
