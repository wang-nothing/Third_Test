package com.example.admin.third_test.home.model;

import com.example.admin.third_test.bean.HomeBean;

public interface Imodel_Home {
    void Home_modelOnsuccess(HomeBean homeBean);

    void Home_modelOnFail(int code);
}
