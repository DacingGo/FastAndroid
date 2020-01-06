package com.hunter.fastandroid.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.hunter.fastandroid.R;
import com.hunter.fastandroid.widget.MultipleStatusView;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 *  Activity基类
 *
 *  @author Hunter
 */
public abstract class BaseActivity<T extends BasePresenter> extends RxAppCompatActivity implements IBaseView {

 //   @BindView(R2.id.multiple_status_view)
    MultipleStatusView mMultipleStatusView;

    private Toast toast;

    private ProgressDialog mProgressDialog;

    protected T mPresenter;

    private boolean mIsShowProgress = true;

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化控制中心
     */
    public abstract T initPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentResId());
        // 初始化View注入
        //ButterKnife.bind(this);
        mIsShowProgress = shouldShowProgress();
        mPresenter = initPresenter();
        mMultipleStatusView = (MultipleStatusView) findViewById(R.id.multiple_status_view);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
        if (mPresenter != null){
            mPresenter.attach(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    protected abstract int getContentResId();

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void showProgress(boolean flag, String message) {
        if (mIsShowProgress) {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setCancelable(flag);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setMessage(message);
            }
            mProgressDialog.show();
        }
    }

    @Override
    public void showProgress(String message) {
        showProgress(true, message);
    }

    @Override
    public void setProgressCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        if (mProgressDialog != null) {
            mProgressDialog.setOnCancelListener(onCancelListener);
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog == null)
            return;

        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showContentView() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showContent();
        }
    }

    @Override
    public void showLoadingView() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showLoading();
        }
    }

    @Override
    public void showNetworkErrorView() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showNoNetwork();
        }
    }

    @Override
    public void showToast(String msg) {
        if (!isFinishing()) {
            if (toast == null) {
                toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }

            toast.show();
        }
    }

    @Override
    public <T> LifecycleTransformer<T> bind() {
        return bindToLifecycle();
    }


    public boolean shouldShowProgress() {
        return true;
    }
}
