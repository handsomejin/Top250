package me.aaron.top250.presenter;

import me.aaron.top250.contract.InfoContarct;
import me.aaron.top250.model.InfoModel;
import me.aaron.top250.model.bean.InfoBean;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by aaron on 17-9-19.
 */

public class InfoPresenter implements InfoContarct.IInfoPresenter {

    private InfoContarct.IInfoView infoView;
    private InfoModel infoModel;

    public InfoPresenter(InfoContarct.IInfoView infoView1){
        this.infoView = checkNotNull(infoView1);
        infoModel = new InfoModel(this);
        infoView.setPresenter(this);
    }


    @Override
    public void start() {
    }

    @Override
    public void askSubject(String id) {
        infoModel.getSubject(id);

    }

    @Override
    public void returnSubject(InfoBean infoBean) {
        infoView.showItems(infoBean);
    }
}
