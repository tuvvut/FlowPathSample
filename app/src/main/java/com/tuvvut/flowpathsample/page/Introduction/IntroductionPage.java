package com.tuvvut.flowpathsample.page.Introduction;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.tuvvut.flowpathsample.R;
import com.tuvvut.flowpathsample.flow.pathview.PageListener;

import flow.path.Path;

/**
 * Created by wu on 2015/08/25
 */
public class IntroductionPage extends LinearLayout implements PageListener{
    private Button register;
    private Button login;
    private IntroductionPresenter presenter;

    public IntroductionPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        Path.get(context);
        presenter = new IntroductionPresenter(context);
    }

    @Override
    public void onPageStart(Activity activity) {
        assignViews();
        assignEvent();
    }

    @Override
    public void onCreateOptionsMenu(Activity activity, Menu menu) {
        activity.setTitle(this.getClass().getSimpleName());
    }

    private void assignViews() {
        register = (Button) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);
    }

    private void assignEvent() {
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRegisterClick();
            }
        });

        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLogin();
            }
        });
    }
}
