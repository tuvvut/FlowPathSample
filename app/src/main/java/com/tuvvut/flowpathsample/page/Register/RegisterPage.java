package com.tuvvut.flowpathsample.page.Register;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tuvvut.flowpathsample.R;
import com.tuvvut.flowpathsample.flow.pathview.PageListener;

import flow.path.Path;

/**
 * Created by wu on 2015/08/25
 */
public class RegisterPage extends LinearLayout implements PageListener {
    private RegisterPresenter presenter;
    private TextView state;
    private EditText userName;
    private EditText password;
    private EditText passwordAgain;
    private Button register;

    public RegisterPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        Path.get(context);
        presenter = new RegisterPresenter(context, this);
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
        passwordAgain = (EditText) findViewById(R.id.passwordAgain);
        register = (Button) findViewById(R.id.register);
    }

    private void assignEvent() {
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRegisterClick(getEditTextString(userName), //
                                          getEditTextString(password), //
                                          getEditTextString(passwordAgain));
            }
        });
    }

    private String getEditTextString(EditText editText) {
        return editText.getText().toString();
    }

    public void showErrorMessage(String message) {
        state.setText(message);
    }

    public void showSuccessMessage() {
        Toast.makeText(getContext(), "Register success.", Toast.LENGTH_SHORT).show();
    }
}
