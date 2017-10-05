package me.aaron.top250.contract;


import me.aaron.top250.BasePresenter;
import me.aaron.top250.BaseView;
import me.aaron.top250.model.bean.ItemsBean;

/**
 * Created by aaron on 17-9-18.
 */

public interface MainContract {
    interface IMainView extends BaseView<IMainPresenter>{
        void showItems();
        void stopRefresh();
        void showMore();
    }

    interface IMainPresenter extends BasePresenter{
        void askMoreItems(int startNum);
        ItemsBean returnMoreItems();
        ItemsBean returnStartItems();
        void startRefresh();
        void callShowStart();
        void callShowMore();
    }

    interface IMainModel{
        void startRefresh();
        void getItems(int startNum);
        ItemsBean setStartItems();
        ItemsBean setMoreItems();
    }

}
