package com.hjc.printer;

import android.os.Bundle;


import com.hjc.basemodule.ui.BaseMvvmActivity;
import com.hjc.printer.databinding.ActivityMainBinding;

public class MainActivity extends BaseMvvmActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}