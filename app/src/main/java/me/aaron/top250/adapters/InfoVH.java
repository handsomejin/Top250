package me.aaron.top250.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import me.aaron.top250.R;

/**
 * Created by aaron on 17-9-20.
 */

public class InfoVH extends RecyclerView.ViewHolder {

    TextView tvTitle;
    TextView tvRating;
    TextView tvYear;
    TextView tvGenar;
    TextView tvCollect;

    TextView tvInfo;

    RecyclerView recyclerView;

    public InfoVH(View itemView,int viewType) {
        super(itemView);
        if (viewType == 0){
            tvCollect = (TextView) itemView.findViewById(R.id.tv_collect_num);
            tvGenar = (TextView) itemView.findViewById(R.id.tv_genar);
            tvYear = (TextView) itemView.findViewById(R.id.tv_year);
            tvRating = (TextView) itemView.findViewById(R.id.tv_info_rating);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_info_movie_name);
        }else if (viewType == 1){
            tvInfo = (TextView) itemView.findViewById(R.id.tv_info_movie_info);
        }else {
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_people);
        }
    }
}
