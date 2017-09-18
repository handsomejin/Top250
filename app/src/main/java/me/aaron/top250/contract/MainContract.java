package me.aaron.top250.contract;


import java.util.List;

import me.aaron.top250.BasePresenter;
import me.aaron.top250.BaseView;
import me.aaron.top250.model.bean.ItemBean;

/**
 * Created by aaron on 17-9-18.
 */

public interface MainContract {
    interface IMainView extends BaseView<IMainPresenter>{
        void showItems(List<ItemBean> items);
        void stopRefresh();
        void showMore();
    }

    interface IMainPresenter extends BasePresenter{
        void askMoreItems(int startNum);
        void returnItems(List<ItemBean> items);
        void startRefresh();
    }

    interface IMainModel{
        void startRefresh();
        void getItems(int startNum);
        void setItems();
    }

}
