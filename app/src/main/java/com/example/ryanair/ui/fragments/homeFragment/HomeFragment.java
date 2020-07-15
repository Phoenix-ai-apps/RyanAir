package com.example.ryanair.ui.fragments.homeFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryanair.R;
import com.example.ryanair.databinding.FragmentHomeBinding;
import com.example.ryanair.interfaces.StationListener;
import com.example.ryanair.interfaces.ToolbarListener;
import com.example.ryanair.models.Station;
import com.example.ryanair.ui.activities.StationSearchActivity;
import com.example.ryanair.ui.adapters.StationAdapter;
import com.example.ryanair.ui.fragments.BaseFragment;
import com.example.ryanair.utils.ApplicationUtils;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends BaseFragment implements StationListener, View.OnClickListener {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private FragmentHomeBinding binding;
    private int ORIGIN_CODE = 1234;
    private int DESTINATION_CODE = 4213;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, getFragmentLayout(), container, false);
        initResources();
        return binding.getRoot();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initResources() {
       if(getActivity() != null && isAdded()){
           ToolbarListener toolbarListener = (ToolbarListener) getActivity();
           toolbarListener.setToolbar("",FRAGMENT_HOME);
       }
       binding.rlFrom.setOnClickListener(this::onClick);
       binding.edtOrigin.setOnClickListener(this::onClick);
       binding.rlTo.setOnClickListener(this::onClick);
       binding.edtDestination.setOnClickListener(this::onClick);
    }

    @Override
    public void selectedStation(Station station) {
        ApplicationUtils.printLog(TAG, station.getCode());
    }

    @Override
    public void onClick(View v) {
        if (v == binding.rlFrom){
            selectOrigin();
        }else if (v == binding.edtOrigin){
            selectOrigin();
        }else if (v == binding.edtDestination){
            selectDestination();
        }else if (v == binding.rlTo){
            selectDestination();
        }
    }

    private void selectOrigin(){
        if(getActivity() != null && isAdded()){
            Intent intent = new Intent(getActivity(), StationSearchActivity.class);
            intent.putExtra(IS_ORIGIN, true);
            startActivityForResult(intent, ORIGIN_CODE);
        }
    }

    private void selectDestination(){
        if(getActivity() != null && isAdded()){
            Intent intent = new Intent(getActivity(), StationSearchActivity.class);
            intent.putExtra(IS_ORIGIN, false);
            startActivityForResult(intent, DESTINATION_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == ORIGIN_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK
                Station station = data.getParcelableExtra(STATION);
                binding.edtOrigin.setText(station.getName());
                binding.tvCodeOr.setText(station.getCode());
            }
        }else if (requestCode == DESTINATION_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK
                Station station = data.getParcelableExtra(STATION);
                binding.edtDestination.setText(station.getName());
                binding.tvCodeDs.setText(station.getCode());
            }
        }
    }
}