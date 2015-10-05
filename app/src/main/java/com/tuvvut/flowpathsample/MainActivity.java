package com.tuvvut.flowpathsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.tuvvut.flowpathsample.flow.GsonParceler;
import com.tuvvut.flowpathsample.flow.pathview.HandlesBack;
import com.tuvvut.flowpathsample.flow.pathview.PageListener;
import com.tuvvut.flowpathsample.page.Introduction.IntroductionPath;

import flow.Flow;
import flow.FlowDelegate;
import flow.FlowDelegate.NonConfigurationInstance;
import flow.History;
import flow.path.Path;
import flow.path.PathContainerView;

public class MainActivity extends AppCompatActivity implements Flow.Dispatcher {
    private FlowDelegate flowSupport;
    private PathContainerView container;
    private PageListener pageListener;
    private HandlesBack containerAsBackTarget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarHomeEnabled(false);

        container = (PathContainerView) findViewById(R.id.container);
        containerAsBackTarget = (HandlesBack) container;
        pageListener = (PageListener) container;

        NonConfigurationInstance nonConfig = (NonConfigurationInstance) getLastCustomNonConfigurationInstance();
        GsonParceler parceler = new GsonParceler(new Gson());
        History history = History.single(getFirstPage());
        flowSupport = FlowDelegate.onCreate(nonConfig, getIntent(), savedInstanceState, parceler, history, this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        flowSupport.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        flowSupport.onResume();
    }

    @Override
    protected void onPause() {
        flowSupport.onPause();
        super.onPause();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return flowSupport.onRetainNonConfigurationInstance();
    }

    @Override
    public Object getSystemService(@NonNull String name) {
        Object service = null;
        if (flowSupport != null) {
            service = flowSupport.getSystemService(name);
        }
        return service != null ? service : super.getSystemService(name);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        flowSupport.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (containerAsBackTarget.onBackPressed()) return;
        if (flowSupport.onBackPressed()) return;
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        pageListener.onCreateOptionsMenu(this, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void dispatch(Flow.Traversal traversal, final Flow.TraversalCallback callback) {
        boolean canGoBack = traversal.destination.size() > 1;
        setActionBarHomeEnabled(canGoBack);
        invalidateOptionsMenu();
        container.dispatch(traversal, new Flow.TraversalCallback() {
            @Override
            public void onTraversalCompleted() {
                callback.onTraversalCompleted();
            }
        });
    }

    private void setActionBarHomeEnabled(boolean enabled){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(enabled);
        }
    }

    public Path getFirstPage() {
        //TODO Use your own path.
        return new IntroductionPath();
    }
}
