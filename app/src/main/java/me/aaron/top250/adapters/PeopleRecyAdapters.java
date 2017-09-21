package me.aaron.top250.adapters;

import android.content.Context;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import me.aaron.top250.R;
import me.aaron.top250.model.bean.InfoBean;
import me.aaron.top250.ui.activities.ActorActivity;

/**
 * Created by aaron on 17-9-20.
 */

public class PeopleRecyAdapters extends RecyclerView.Adapter<PeopleVH> {

    private class People{
        String name;
        String pic;
        String id;

        public People(String name , String pic, String id){
            this.name = name;
            this.pic = pic;
            this.id = id;
        }

        public String getId() {
            return id;
        }


        public String getName() {
            return name;
        }

        public String getPic() {
            return pic;
        }
    }

    List<People> peopleList = new ArrayList<>();
    private Context context;



    public PeopleRecyAdapters(Context context , List<InfoBean.CastsBean> casts , List<InfoBean.DirectorsBean> directors){
        this.context = context;
        for (int i = 0 ; i < directors.size() ; i++){
            People people = new People(directors.get(i).getName()
                    ,directors.get(i).getAvatars().getMedium()
                    ,directors.get(i).getId());
            peopleList.add(people);
        }
        for (int i = 0 ; i < casts.size() ; i++){
            People people = new People(casts.get(i).getName()
                    ,casts.get(i).getAvatars().getMedium()
                    ,casts.get(i).getId());
            peopleList.add(people);
        }
    }



    @Override
    public PeopleVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cv_peple_item,parent,false);
        return new PeopleVH(view);
    }

    @Override
    public void onBindViewHolder(PeopleVH holder, int position) {
        final String name = peopleList.get(position).getName();
        final String id = peopleList.get(position).getId();
        holder.name.setText(name);
        Glide.with(context).load(peopleList.get(position).getPic()).into(holder.headPic);
        holder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActorActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }
}
