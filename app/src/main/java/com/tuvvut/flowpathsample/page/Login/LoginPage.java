package com.tuvvut.flowpathsample.page.Login;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuvvut.flowpathsample.R;
import com.tuvvut.flowpathsample.flow.pathview.PageListener;

import flow.path.Path;

/**
 * Created by wu on 2015/08/25
 */
public class LoginPage extends LinearLayout implements PageListener {
    private LoginPresenter presenter;
    private TextView state;
    private EditText userName;
    private EditText password;
    private Button login;
    private CheckBox checkBox;

    public LoginPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        Path.get(context);
        presenter = new LoginPresenter(context, this);
    }

    @Override
    public void onPageStart(Activity activity) {
        assignViews();
        assignEvent();
    }

    @Override
    public void onPrepareOptionsMenu(Activity activity, Menu menu) {
        activity.setTitle(this.getClass().getSimpleName());
    }

    private void assignViews() {
        state = (TextView) findViewById(R.id.state);
        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
    }

    private void assignEvent() {
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginClick(getEditTextString(userName), //
                                       getEditTextString(password));
            }
        });
    }

    private String getEditTextString(EditText editText) {
        return editText.getText().toString();
    }

    public void showErrorMessage(String message) {
        state.setText(message);
    }

    public boolean isToHomePage2(){
        return checkBox.isChecked();
    }
}