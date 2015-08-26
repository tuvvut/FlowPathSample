package com.tuvvut.flowpathsample.page.Home;

import com.tuvvut.flowpathsample.R;
import com.tuvvut.flowpathsample.flow.pathview.Layout;

import flow.path.Path;

/**
 * Created by wu on 2015/08/25
 */
@Layout(R.layout.page_home)
public class HomePath extends Path {
    private String userName = "";

    public HomePath(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
