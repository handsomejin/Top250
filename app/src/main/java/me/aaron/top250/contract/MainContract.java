package me.aaron.top250.contract;


import me.aaron.top250.BasePresenter;
import me.aaron.top250.BaseView;
import me.aaron.top250.model.bean.ItemsBean;

/**
 * Created by aaron on 17-9-18.
 */

public interface MainContract {
    interface IMainView extends BaseView<IMainPresenter>{
        void showItems(ItemsBean itemsBean);
        void stopRefresh();
        void showMore(ItemsBean items);
    }

    interface IMainPresenter extends BasePresenter{
        void askMoreItems(int startNum);
        void returnMoreItems(ItemsBean items);
        void returnStartItems(ItemsBean items);
        void startRefresh();
    }

    interface IMainModel{
        void startRefresh();
        void getItems(int startNum);
        void setStartItems();
        void setMoreItems();
    }

}
