package com.example.ryanair.ui.fragments.flightBookingActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryanair.R;
import com.example.ryanair.databinding.FragmentHomeBinding;
import com.example.ryanair.databinding.FragmentPassengerBinding;
import com.example.ryanair.interfaces.ToolbarListener;
import com.example.ryanair.ui.fragments.BaseFragment;
import com.example.ryanair.ui.fragments.homeActivity.HomeFragment;
import com.example.ryanair.utils.ApplicationUtils;

import static android.app.Activity.RESULT_OK;

public class PassengerFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = PassengerFragment.class.getSimpleName();
    private FragmentPassengerBinding binding;
    private int adults =1, teens=0, children =0, infants=0;

    public PassengerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().containsKey(ADULTS)){
            adults = getArguments().getInt(ADULTS);
        }
        if(getArguments().containsKey(TEENS)){
            teens = getArguments().getInt(TEENS);
        }
        if(getArguments().containsKey(CHILDREN)){
            children = getArguments().getInt(CHILDREN);
        }
        if(getArguments().containsKey(INFANT)){
            infants = getArguments().getInt(INFANT);
        }
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
        return R.layout.fragment_passenger;
    }

    @Override
    protected void initResources() {
       binding.tvAdultCount.setText(""+adults);
       binding.tvTeensCount.setText(""+teens);
       binding.tvChildCount.setText(""+children);
       binding.tvInfantCount.setText(""+infants);
       binding.ivAdultAdd.setOnClickListener(this::onClick);
       binding.ivAdultRemove.setOnClickListener(this::onClick);
       binding.ivChildAdd.setOnClickListener(this::onClick);
       binding.ivChildRemove.setOnClickListener(this::onClick);
       binding.ivTeensAdd.setOnClickListener(this::onClick);
       binding.ivTeensRemove.setOnClickListener(this::onClick);
       binding.ivInfsntAdd.setOnClickListener(this::onClick);
       binding.ivInfantRemove.setOnClickListener(this::onClick);
       binding.btnConfirm.setOnClickListener(this::onClick);
       if(getActivity() != null && isAdded()){
           ToolbarListener toolbarListener =(ToolbarListener)getActivity();
           toolbarListener.setToolbar(getString(R.string.passengers), FRAGMENT_PASSENGER_SELECTION);
       }
    }

    @Override
    public void onClick(View v) {
        if(v == binding.ivAdultAdd){
            if(adults < 10){
                adults+=1;
                binding.tvAdultCount.setText(""+adults);
            }
        }else if(v == binding.ivAdultRemove){
            if(adults > 1){
                adults-=1;
                binding.tvAdultCount.setText(""+adults);
            }
        }else if(v == binding.ivTeensAdd){
            if(teens < 5){
                teens+=1;
                binding.tvTeensCount.setText(""+teens);
            }
        }else if(v == binding.ivTeensRemove){
            if(teens > 0){
                teens-=1;
                binding.tvTeensCount.setText(""+teens);
            }
        }else if(v == binding.ivChildAdd){
            if(children < 5){
                children+=1;
                binding.tvChildCount.setText(""+children);
            }
        }else if(v == binding.ivChildRemove){
            if(children > 0){
                children-=1;
                binding.tvChildCount.setText(""+children);
            }
        }else if(v == binding.ivInfsntAdd){
            if(infants < adults){
                infants+=1;
                binding.tvInfantCount.setText(""+infants);
            }else {
                if(getActivity() != null && isAdded()){
                    ApplicationUtils.showSnackBar(getActivity(), getString(R.string.infant_error_msg));
                }
            }
        }else if(v == binding.ivInfantRemove){
            if(infants > 0){
                infants-=1;
                binding.tvInfantCount.setText(""+infants);
            }
        }else if(v == binding.btnConfirm){
            if(getActivity() != null && isAdded()){
                Intent intent = new Intent();
                intent.putExtra(ADULTS, adults);
                intent.putExtra(TEENS,  teens);
                intent.putExtra(CHILDREN, children);
                intent.putExtra(INFANT, infants);
                getActivity().setResult(RESULT_OK, intent);
                getActivity().finish();
            }
        }
    }
}