package me.aaron.top250.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.aaron.top250.R;
import me.aaron.top250.model.bean.InfoBean;

/**
 * Created by aaron on 17-9-20.
 */

public class InfoRecyAdapters extends RecyclerView.Adapter<InfoVH> {

    private InfoBean infoBean;
    private Context context;

    private final int TYPE_FIRST = 0;
    private final int TYPE_SECOND = 1;
    private final int TYPE_THIRD = 2;

    public InfoRecyAdapters(InfoBean infoBean,Context context){
        System.out.println(infoBean.getSummary());
        this.infoBean = infoBean;
        this.context = context;
    }



    @Override
    public InfoVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_FIRST){
            view = LayoutInflater.from(context).inflate(R.layout.cv_info_first_item,parent,false);
            return new InfoVH(view,TYPE_FIRST);
        }else if (viewType == TYPE_SECOND){
            view = LayoutInflater.from(context).inflate(R.layout.cv_info_second_item,parent,false);
            return new InfoVH(view,TYPE_SECOND);
        }else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(InfoVH holder, int position) {
        switch (getItemViewType(position)){
            case TYPE_FIRST:
                holder.tvTitle.setText(infoBean.getTitle());
                holder.tvRating.setText(String.valueOf(infoBean.getRating().getAverage()));
                holder.tvCollect.setText(String.valueOf(infoBean.getCollect_count()));
                holder.tvGenar.setText("类型: " + TextUtils.join(",",infoBean.getGenres()));
                holder.tvYear.setText("上映时间: " + infoBean.getYear());
                break;
            case TYPE_SECOND:
                holder.tvInfo.setText(infoBean.getSummary());
                break;
            default:
                break;
        }


    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_FIRST;
        }else if (position == 1){
            return TYPE_SECOND;
        }else {
            return TYPE_THIRD;
        }
    }
}
