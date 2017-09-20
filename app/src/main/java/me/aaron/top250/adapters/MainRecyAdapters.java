package me.aaron.top250.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import me.aaron.top250.R;
import me.aaron.top250.model.bean.ItemsBean;
import me.aaron.top250.ui.activities.InfoActivity;

/**
 * Created by aaron on 17-9-18.
 */

public class MainRecyAdapters extends RecyclerView.Adapter<MainVH>{

    private final int TYPE_NORMAL = 0;
    private final int TYPE_FOOTER = 1;


    private Context context;
    //private List<ItemBean> itemBeanList;
    private ItemsBean itemsBean;
    private List<ItemsBean.ItemBean> itemBeanList = new ArrayList<>();


    public MainRecyAdapters(Context context , ItemsBean itemsBean){
        this.context = context;
        this.itemsBean = itemsBean;
        initData(itemsBean);
    }


    @Override
    public MainVH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL){
            View view = LayoutInflater.from(context).inflate(R.layout.cv_movie_item,parent,false);
            return new MainVH(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.foot_item,parent,false);
            return new MainVH(view,true);
        }
    }

    @Override
    public void onBindViewHolder(MainVH holder, final int position) {
        switch (getItemViewType(position)){
            case TYPE_NORMAL:
                holder.tvTopNum.setText(String.valueOf(position+1)) ;
                double rating = itemBeanList.get(position).getRating().getAverage();
                holder.tvRating.setText(String.valueOf(rating));
                if (rating < 9){
                    holder.ivStar.setImageResource(R.drawable.half_star_32);
                }else if (rating >= 9){
                    holder.ivStar.setImageResource(R.drawable.star_32);
                }
                Glide.with(context).load(itemBeanList.get(position).getImages().getMedium()).into(holder.ivMoviePic);
                holder.tvTitle.setText(itemBeanList.get(position).getTitle());
                holder.tvGenra.setText(TextUtils.join(",",itemBeanList.get(position).getGenres()));
                holder.tvYear.setText(itemBeanList.get(position).getYear());
                holder.itemview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, InfoActivity.class);
                        intent.putExtra("id",itemBeanList.get(position).getId());
                        context.startActivity(intent);
                    }
                });
                break;
            case TYPE_FOOTER:
                break;
        }
    }



    @Override
    public int getItemCount() {
        return itemBeanList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position +1 ==getItemCount()){
            return TYPE_FOOTER;
        }else {
            return TYPE_NORMAL;
        }
    }

    public void initData(ItemsBean itemsBean){
        for (int i = 0 ; i < 25 ; i++){
            ItemsBean.ItemBean item;
            item = itemsBean.getSubjects().get(i);
            itemBeanList.add(item);
        }
    }

}
