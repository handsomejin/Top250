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

    @Override
    public void askMoreItems(int startNumber) {
        itemModel.getItems(startNumber);
    }

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

    @Override
    public void start() {
        itemModel.getItems(0);
    }
}
