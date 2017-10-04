package me.aaron.top250.presenter;

import me.aaron.top250.model.ItmesModel;
import me.aaron.top250.model.bean.ItemsBean;
import me.aaron.top250.contract.MainContract;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by aaron on 17-9-18.
 */

public class MainPresenter implements MainContract.IMainPresenter {

    private MainContract.IMainView iMainView;
    private ItmesModel itemModel;


    public MainPresenter(MainContract.IMainView iMainView){
        this.iMainView = checkNotNull(iMainView);
        itemModel = new ItmesModel(this);
        iMainView.setPresenter(this);

    }

    //请求更多
    @Override
    public void askMoreItems(int startNumber) {
        itemModel.getItems(startNumber);
    }

    //将最开始请求到的数据返回给view层
    @Override
    public void returnStartItems(ItemsBean items) {
        iMainView.showItems(items);
        iMainView.stopRefresh();
    }

    @Override
    public void returnMoreItems(ItemsBean items) {
        iMainView.showMore(items);
    }

    @Override
    public void startRefresh() {
        itemModel.startRefresh();
    }

    //第一次请求数据
    @Override
    public void start() {
        itemModel.getItems(0);
    }
}
