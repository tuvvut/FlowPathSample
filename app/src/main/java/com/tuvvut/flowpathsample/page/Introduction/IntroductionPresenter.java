package com.tuvvut.flowpathsample.page.Introduction;

import android.content.Context;

import com.tuvvut.flowpathsample.page.Login.LoginPath;
import com.tuvvut.flowpathsample.page.Register.RegisterPath;

import flow.Flow;

/**
 * Created by wu on 2015/08/25
 */
public class IntroductionPresenter {
    Context context;

    public IntroductionPresenter(Context context) {
        this.context = context;
    }

    public void onRegisterClick() {
        Flow.get(context).set(new RegisterPath());
    }

    public void onLogin() {
        Flow.get(context).set(new LoginPath());
    }
}
