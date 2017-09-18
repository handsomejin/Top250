package me.aaron.top250.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.aaron.top250.R;

/**
 * Created by aaron on 17-9-18.
 */

public class MainVH extends RecyclerView.ViewHolder {

    View itemview;
    TextView tvTopNum;
    ImageView ivMoviePic;
    TextView tvTitle;
    ImageView ivStar;
    TextView tvRating;
    TextView tvGenra;
    TextView tvYear;
    View footView;

    public MainVH(View itemView) {
        super(itemView);
        this.itemview = itemView;
        tvTopNum = (TextView) itemView.findViewById(R.id.tv_renking_number);
        tvRating = (TextView) itemView.findViewById(R.id.tv_rating_number);
        tvGenra = (TextView) itemView.findViewById(R.id.tv_movie_genras);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_movie_name);
        tvYear = (TextView) itemView.findViewById(R.id.tv_movie_year);
        ivMoviePic = (ImageView) itemView.findViewById(R.id.iv_movie_pic);
        ivStar = (ImageView) itemView.findViewById(R.id.iv_the_last_star);
    }

    public MainVH(View itemView,boolean isFooter){
        super(itemView);
        footView = itemView;
    }

}
