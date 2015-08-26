package com.tuvvut.flowpathsample.page.Register;

import android.content.Context;
import android.text.TextUtils;

import com.tuvvut.flowpathsample.model.API;

import flow.Flow;

/**
 * Created by wu on 2015/08/25
 */
public class RegisterPresenter implements API.ResultListener{
    private Context context;
    private RegisterPage page;
    private API api;

    public RegisterPresenter(Context context, RegisterPage page) {
        this.context = context;
        this.page = page;
        api = new API(this);
    }

    public void onRegisterClick(String userName, String password, String passwordAgain) {
        if (TextUtils.isEmpty(userName)) {
            page.showErrorMessage("Please check your user name.");
        } else if (TextUtils.isEmpty(password) || !password.equals(passwordAgain)) {
            page.showErrorMessage("Please check your password.");
        } else {
            api.register(userName, password, passwordAgain);
        }
    }

    @Override
    public void onResult(boolean isSuccess, Object result) {
        if (isSuccess) {
            page.showSuccessMessage();
            Flow.get(context).goBack();
        } else {
            page.showErrorMessage("Register Failed.");
        }
    }
}
