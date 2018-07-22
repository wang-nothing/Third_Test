package com.example.admin.third_test.home.presenter;

import com.example.admin.third_test.bean.HomeBean;
import com.example.admin.third_test.home.model.Imodel_Home;
import com.example.admin.third_test.home.model.Model_Home;
import com.example.admin.third_test.home.view.Iview_Home;

public class Presenter_Home implements Ipresenter_Home {
    private Iview_Home iview_home;
    private Model_Home model_home;

    public Presenter_Home(Iview_Home iview_home) {
        this.iview_home = iview_home;
        this.model_home = new Model_Home();
    }
    public void getDatas(int page){
        model_home.getData(new Imodel_Home() {
            @Override
            public void Home_modelOnsuccess(HomeBean homeBean) {
                iview_home.Home_viewOnsuccess(homeBean);
            }

            @Override
            public void Home_modelOnFail(int code) {
                iview_home.Home_viewOnFail(code);
            }
        },page);
    }
    @Override
    public void Home_presenterOnDestroys() {
        if (iview_home != null){
            iview_home = null;
        }
    }
}
