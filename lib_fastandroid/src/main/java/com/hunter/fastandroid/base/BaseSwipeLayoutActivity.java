package com.hunter.fastandroid.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.hunter.fastandroid.R;

import butterknife.BindView;

/**
 * Created by Ryan on 2017/8/2.
 */
public abstract class BaseSwipeLayoutActivity<T extends BasePresenter>  extends BaseActivity <T>{

    //@BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private SwipeRefreshLayout.OnRefreshListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        initSwipeRreshLayout();
    }

    private void initSwipeRreshLayout() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mListener = getOnRefreshListener();
        mSwipeRefreshLayout.setOnRefreshListener(mListener);
    }

    public void setRefreshing(boolean flag) {
        mSwipeRefreshLayout.setRefreshing(flag);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mSwipeRefreshLayout.setRefreshing(true);
        if (mListener != null) {
            mListener.onRefresh();
        }
    }

    public abstract SwipeRefreshLayout.OnRefreshListener getOnRefreshListener();

}
