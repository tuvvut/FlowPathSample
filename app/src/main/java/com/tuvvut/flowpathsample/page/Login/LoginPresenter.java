package com.tuvvut.flowpathsample.page.Login;

import android.content.Context;
import android.text.TextUtils;

import com.tuvvut.flowpathsample.model.API;
import com.tuvvut.flowpathsample.page.Home.HomePath;
import com.tuvvut.flowpathsample.page.Home.HomePath2;

import flow.Flow;
import flow.History;
import flow.path.Path;

/**
 * Created by wu on 2015/08/25
 */
public class LoginPresenter implements API.ResultListener<String> {
    private Context context;
    private LoginPage page;
    private API api;

    public LoginPresenter(Context context, LoginPage page) {
        this.context = context;
        this.page = page;
        api = new API(this);
    }

    public void onLoginClick(String userName, String password) {
        if (TextUtils.isEmpty(userName)) {
            page.showErrorMessage("Please check your user name.");
        } else if (TextUtils.isEmpty(password)) {
            page.showErrorMessage("Please check your password.");
        } else {
            api.login(userName, password);
        }
    }

    @Override
    public void onResult(boolean isSuccess, String userName) {
        if (isSuccess) {
            Path path;
            if (!page.isToHomePage2()) {
                path = new HomePath(userName);
            } else {
                path = new HomePath2();
            }
            History history = History.single(path);
            Flow.get(context).setHistory(history, Flow.Direction.FORWARD);
        } else {
            page.showErrorMessage("Login Failed.");
        }
    }
}
