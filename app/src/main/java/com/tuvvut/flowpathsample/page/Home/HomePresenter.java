package com.tuvvut.flowpathsample.page.Home;

import android.content.Context;

import com.tuvvut.flowpathsample.model.API;
import com.tuvvut.flowpathsample.page.Introduction.IntroductionPath;

import java.util.ArrayList;

import flow.Flow;
import flow.History;

/**
 * Created by wu on 2015/08/25
 */
public class HomePresenter implements API.ResultListener<ArrayList<String>> {
    private Context context;
    private IHomePage page;
    private API api;

    public HomePresenter(Context context, IHomePage page) {
        this.context = context;
        this.page = page;
        api = new API(this);
    }

    @Override
    public void onResult(boolean isSuccess, ArrayList<String> result) {
        page.setNewsData(result);
    }

    public void loadNews() {
        api.loadNews();
    }

    public void onLogoutClick() {
        History history = History.single(new IntroductionPath());
        Flow.get(context).setHistory(history, Flow.Direction.BACKWARD);
    }
}
