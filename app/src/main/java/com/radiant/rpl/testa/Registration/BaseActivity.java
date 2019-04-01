package com.radiant.rpl.testa.Registration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

import dmax.dialog.SpotsDialog;
import radiant.rpl.radiantrpl.R;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private android.app.AlertDialog progressDialog;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        progressDialog = new SpotsDialog(BaseActivity.this, R.style.Custom);
        //setupToolbar();
        //bindViews();
        initView();
    }

    public void show_progressbar(){
        progressDialog.show();

    }

    public void hide_progressbar(){
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /* *//**
     * Use this method to initialize view components. This method is called after {@link
     * BaseActivity#()}
     */
    public void initView() {
    }

    /**
     * Its common use a toolbar within activity, if it exists in the
     * layout this will be configured
     */
   /* public void setupToolbar() {
        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }*/
    /*
     *//**
     * Every object annotated with {@link butterknife.Bind} its gonna injected trough butterknife
     *//*
    private void bindViews() {
        ButterKnife.bind(this);
    }

    @Nullable public Toolbar getToolbar() {
        return mToolbar;
    }*/

    /**
     * @return The layout id that's gonna be the activity view.
     */
    protected abstract int getLayoutId();
}
