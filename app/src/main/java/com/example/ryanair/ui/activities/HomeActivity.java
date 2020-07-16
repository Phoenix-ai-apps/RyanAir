package com.example.ryanair.ui.activities;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.ryanair.R;
import com.example.ryanair.databinding.ActivityHomeBinding;
import com.example.ryanair.interfaces.ToolbarListener;
import com.example.ryanair.ui.fragments.homeActivity.HomeFragment;

public class HomeActivity extends BaseActivity implements ToolbarListener, View.OnClickListener {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        initResources();
    }

    @Override
    protected void initResources() {
        binding.incToolbar.ivBack.setOnClickListener(this);
        replaceFragment(new HomeFragment(), HomeFragment.class.getSimpleName(),false);
    }

    @Override
    public void onClick(View v) {
        if(v == binding.incToolbar.ivBack){
            onBackPressed();
        }
    }

    @Override
    public void setToolbar(String headerText, String fromFragment) {
        if(!TextUtils.isEmpty(headerText)){
            binding.incToolbar.tvMainText.setText(headerText);
            if(fromFragment.equalsIgnoreCase(FRAGMENT_HOME)){
                binding.incToolbar.ivBack.setVisibility(View.GONE);
                binding.incToolbar.tvMainText.setVisibility(View.GONE);
                binding.incToolbar.ivLogo.setVisibility(View.VISIBLE);
            }
        }
    }
}