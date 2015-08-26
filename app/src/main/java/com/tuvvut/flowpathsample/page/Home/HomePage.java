package com.tuvvut.flowpathsample.page.Home;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tuvvut.flowpathsample.R;
import com.tuvvut.flowpathsample.flow.pathview.PageListener;

import java.util.ArrayList;

import flow.path.Path;

/**
 * Created by wu on 2015/08/25
 */
public class HomePage extends RelativeLayout implements PageListener {
    private HomePresenter presenter;
    private TextView userName;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private Toast toast;

    public HomePage(Context context, AttributeSet attrs) {
        super(context, attrs);
        presenter = new HomePresenter(context, this);
        HomePath path = Path.get(context);
        presenter.setPath(path);
    }

    @Override
    public void onPageStart(Activity activity) {
        assignViews();
        assignEvent();
        presenter.loadNews();
    }

    @Override
    public void onPrepareOptionsMenu(Activity activity, Menu menu) {
        activity.setTitle(this.getClass().getSimpleName());
        activity.getMenuInflater().inflate(R.menu.page_home_menu, menu);

        MenuItem logout = menu.findItem(R.id.action_logout);
        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                presenter.onLogoutClick();
                return true;
            }
        });
    }

    private void assignViews() {
        userName = (TextView) findViewById(R.id.userName);
        list = (ListView) findViewById(R.id.list);
        TextView emptyView = (TextView) findViewById(android.R.id.empty);

        setUserName(presenter.getUserName());
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1, new ArrayList<String>());
        list.setAdapter(adapter);
        list.setEmptyView(emptyView);
    }

    private void assignEvent() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String news = (String) parent.getAdapter().getItem(position);
                showToast("You Clicked The " + news);
            }
        });
    }

    public void showToast(String string) {
        if (toast == null) {
            toast = Toast.makeText(getContext(), string, Toast.LENGTH_SHORT);
        } else {
            toast.setText(string);
        }
        toast.show();
    }

    public void setAdapterData(ArrayList<String> data) {
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    public void setUserName(String name) {
        userName.setText("User Name: " + name);
    }
}
