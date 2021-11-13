package com.hjc.basemodule.ui;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.hjc.basemodule.R;
import com.hjc.basemodule.vm.BaseViewModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Describe:
 * @Author: hjc
 * @Email: 252431193@qq.com
 * @Date: 2021/11/13
 */
public abstract class BaseMvvmActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

    /**
     * ViewDataBinding
     */
    protected V mViewDataBinding;

    /**
     * ViewModel
     */
    private VM mViewModel;

    /**
     * 沉浸式状态栏
     */
    private ImmersionBar immersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mViewDataBinding.setLifecycleOwner(this);

        immersionBar = ImmersionBar.with(this);
        customImmersionBar();

        mViewModel = initViewModel();

        bindViewModelToBinding(mViewDataBinding, mViewModel);

        init(savedInstanceState);
        initViews(savedInstanceState);
        initListener();
        initData(savedInstanceState);

    }

    /**
     * 初始化
     */
    protected void init(Bundle savedInstanceState) {
    }

    /**
     * 初始化view
     */
    protected void initViews(Bundle savedInstanceState) {
    }

    /**
     * 初始化数据
     */
    protected void initData(Bundle savedInstanceState) {
    }

    /**
     * 设置监听器
     */
    protected void initListener() {
    }

    /**
     * 布局layout id
     *
     * @return layout id
     */
    protected abstract int getLayoutId();


    protected void customImmersionBar() {
        //是否全屏
        if (applyFullScreen()) {
            setFullScreenModel();
        }
        //是否设置沉浸式状态栏
        else if (applyImmersionBar()) {
            setImmersionBar(getStatusBarColor());
        }
    }

    /**
     * 是否设置沉浸式状态栏
     *
     * @return 是否使用沉浸状态栏
     */
    protected boolean applyImmersionBar() {
        return true;
    }

    /**
     * 系统StatusBar颜色
     *
     * @return 系统颜色
     */
    protected int getStatusBarColor() {
        View actionBar = findViewById(R.id.base_actionbar);
        if (null != actionBar) {
            Drawable drawable = actionBar.getBackground();
            if (drawable instanceof ColorDrawable) {
                ColorDrawable colorDrawable = (ColorDrawable) drawable;
                return colorDrawable.getColor();
            }
        }
        return getResources().getColor(R.color.colorPrimary);
    }

    /**
     * 设置系统statusBar颜色
     *
     * @param statusBarColor 状态栏颜色
     */
    protected void setImmersionBar(int statusBarColor) {
        immersionBar.statusBarColorInt(statusBarColor)
                .keyboardEnable(true)
                .fitsSystemWindows(true)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    /**
     * 全屏App内容填充状态栏
     */
    protected void setFullScreenModel() {
        immersionBar.keyboardEnable(false)
                .fullScreen(true)
                .transparentNavigationBar()
                .hideBar(BarHide.FLAG_HIDE_BAR)
                .init();
    }

    /**
     * 是否设置全屏显示
     *
     * @return 是否设置全屏
     */
    protected boolean applyFullScreen() {
        return false;
    }


    /**
     * 初始化ViewModel, 默认将使用泛型中的ViewModel类创建
     *
     * @return ViewModel
     */
    protected VM initViewModel() {
        VM viewModel;
        Class modelClass;
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
        } else {
            //如果没有指定泛型参数，则默认使用BaseViewModel
            modelClass = BaseViewModel.class;
        }
        viewModel = (VM) createViewModel(this, modelClass);
        return viewModel;
    }

    public int initViewModelID() {
        return -1;
    }

    private void bindViewModelToBinding(V viewDataBinding, VM viewModel) {
        int vmID = initViewModelID();
        if (-1 != vmID) {
            viewDataBinding.setVariable(vmID, viewModel.getClass());
            return;
        }
        try {
            Method setViewModel = viewDataBinding.getClass().getMethod("setViewModel", viewModel.getClass());
            setViewModel.invoke(viewDataBinding, viewModel);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建ViewModel
     *
     * @param activity   an activity, in whose scope ViewModels should be retained
     * @param modelClass The class of the ViewModel to create an instance of it if it is not
     *                   present.
     * @param <T>        The type parameter for the ViewModel.
     * @return A ViewModel that is an instance of the given type {@code T}.
     */
    protected <T extends ViewModel> T createViewModel(FragmentActivity activity, Class<T> modelClass) {
        return ViewModelProviders.of(activity).get(modelClass);
    }

    protected V getBinding() {
        return (V) mViewDataBinding;
    }

    /**
     * 获取Presenter
     *
     * @return P
     */
    protected VM getViewModel() {
        return mViewModel;
    }


    @Override
    protected void onDestroy() {
        if (null != immersionBar) {
            immersionBar.destroy();
        }
        super.onDestroy();
    }
}
