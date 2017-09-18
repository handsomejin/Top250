package me.aaron.top250.presenter;

import java.util.List;

import me.aaron.top250.model.ItemModel;
import me.aaron.top250.model.bean.ItemBean;
import me.aaron.top250.contract.MainContract;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by aaron on 17-9-18.
 */

public class MainPresenter implements MainContract.IMainPresenter {

    private MainContract.IMainView iMainView;
    private ItemModel itemModel;


    public MainPresenter(MainContract.IMainView iMainView){
        this.iMainView = checkNotNull(iMainView);
        itemModel = new ItemModel(this);
        iMainView.setPresenter(this);

    }

    @Override
    public void askMoreItems(int startNum) {
        itemModel.getItems(startNum);
    }

    @Override
    public void returnItems(List<ItemBean> items) {
        iMainView.showItems(items);
        iMainView.stopRefresh();
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
