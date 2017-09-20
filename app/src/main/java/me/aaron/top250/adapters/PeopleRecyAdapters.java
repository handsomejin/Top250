package me.aaron.top250.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.aaron.top250.model.bean.InfoBean;

/**
 * Created by aaron on 17-9-20.
 */

public class PeopleRecyAdapters extends RecyclerView.Adapter<PeopleVH> {

    class People{
        String name;
        String pic;
    }

    List<People> peopleList = new ArrayList<>();
    private Context context;



    public PeopleRecyAdapters(Context context , InfoBean.CastsBean castsBean , InfoBean.DirectorsBean directorsBean){
    }



    @Override
    public PeopleVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PeopleVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }
}
