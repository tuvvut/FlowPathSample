package com.tuvvut.flowpathsample.model;

/**
 * Created by wu on 2015/08/26
 */
public class API {
    ResultListener listener;

    public API(ResultListener listener) {
        this.listener = listener;
    }

    public void register(String userName, String password, String passwordAgain) {
        listener.onResult(Server.register(userName, password, passwordAgain), null);
    }

    public void login(String userName, String password) {
        listener.onResult(Server.login(userName, password), userName);
    }

    public void loadNews() {
        listener.onResult(true, Server.loadNews());
    }

    public interface ResultListener<T> {
        void onResult(boolean isSuccess, T result);
    }
}
