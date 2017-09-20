package me.aaron.top250.contract;

import me.aaron.top250.BasePresenter;
import me.aaron.top250.BaseView;
import me.aaron.top250.model.bean.InfoBean;

/**
 * Created by aaron on 17-9-19.
 */

public interface InfoContarct {

    interface IInfoView extends BaseView<IInfoPresenter>{
        void showTopPic(String imageString);//顶部图片展示
        void showItems(InfoBean infoBean);//展示列表
    }

    interface IInfoPresenter extends BasePresenter{
        void askSubject(String id);
        void returnSubject(InfoBean infoBean);
    }

    interface IInfoModel{
        void getSubject(String id);
        void setSubject();
    }
}
