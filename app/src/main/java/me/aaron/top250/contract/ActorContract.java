package me.aaron.top250.contract;

/**
 * Created by aaron on 17-9-21.
 */

public interface ActorContract {

    /**
     * 此处接口可有可无，原因参看对应的Activity,项目demo不再深入
     */

    interface IActorView{
        void showWebView();
        void setTbTilte();
    }
}
