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
    private HomePage page;
    private HomePath path;
    private API api;

    public HomePresenter(Context context, HomePage page) {
        this.context = context;
        this.page = page;
        api = new API(this);
    }

    public String getUserName() {
        return path.getUserName();
    }

    public void setPath(HomePath path) {
        this.path = path;
    }

    @Override
    public void onResult(boolean isSuccess, ArrayList<String> result) {
        page.setAdapterData(result);
    }

    public void loadNews() {
        api.loadNews();
    }

    public void onLogoutClick() {
        History history = History.single(new IntroductionPath());
        Flow.get(context).setHistory(history, Flow.Direction.BACKWARD);
    }
}
