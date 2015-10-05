package com.tuvvut.flowpathsample.page.Home;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tuvvut.flowpathsample.R;
import com.tuvvut.flowpathsample.flow.pathview.PageListener;

import java.util.ArrayList;

/**
 * Created by wu on 2015/08/25
 */
public class HomePage2 extends RelativeLayout implements PageListener, IHomePage {
    private HomePresenter presenter;
    private Button logout;
    private TextView newsTextView;

    public HomePage2(Context context, AttributeSet attrs) {
        super(context, attrs);
        presenter = new HomePresenter(context, this);
    }

    @Override
    public void onPageStart(Activity activity) {
        assignViews();
        assignEvent();
        presenter.loadNews();
    }

    @Override
    public void onCreateOptionsMenu(Activity activity, Menu menu) {
        activity.setTitle(this.getClass().getSimpleName());
    }

    private void assignViews() {
        logout = (Button) findViewById(R.id.logout);
        newsTextView = (TextView) findViewById(R.id.newsTextView);
    }

    private void assignEvent() {
        logout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLogoutClick();
            }
        });
    }

    @Override
    public void setNewsData(ArrayList<String> data) {
        String newsData = TextUtils.join("\n", data);
        newsTextView.setText(newsData);
    }
}
