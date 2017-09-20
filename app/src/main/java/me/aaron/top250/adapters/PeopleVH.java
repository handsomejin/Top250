package me.aaron.top250.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.aaron.top250.R;


/**
 * Created by aaron on 17-9-20.
 */

public class PeopleVH extends RecyclerView.ViewHolder {

    ImageView headPic;
    TextView name;
    View itemview;
    public PeopleVH(View itemView) {
        super(itemView);
        this.itemview = itemView;
        headPic = (ImageView) itemView.findViewById(R.id.iv_people_pic);
        name = (TextView) itemView.findViewById(R.id.tv_people_name);
    }
}
