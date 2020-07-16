package com.example.ryanair.ui.fragments.homeActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.text.TextUtils;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.ryanair.R;
import com.example.ryanair.databinding.FragmentHomeBinding;
import com.example.ryanair.interfaces.DataRefreshListener;
import com.example.ryanair.interfaces.StationListener;
import com.example.ryanair.interfaces.ToolbarListener;
import com.example.ryanair.models.FlightEnquiry;
import com.example.ryanair.models.Station;
import com.example.ryanair.ui.activities.FlightBookingActivity;
import com.example.ryanair.ui.activities.FlightDetailsActivity;
import com.example.ryanair.ui.fragments.BaseFragment;
import com.example.ryanair.utils.ApplicationUtils;
import com.example.ryanair.utils.DateUtils;
import com.example.ryanair.utils.NetworkUtils;

import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends BaseFragment implements StationListener, View.OnClickListener, DataRefreshListener {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private FragmentHomeBinding binding;
    private int ORIGIN_CODE = 1234;
    private int DESTINATION_CODE = 4213;
    private int PASSENGER_CODE = 1123;
    private int adults=1, teens=0, children=0, infants=0;
    private String date="";
    private FlightEnquiry flightEnquiry;
    private DataRefreshListener listener;

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
       flightEnquiry = new FlightEnquiry();
       flightEnquiry.setAdult(1);// Default value
       listener = this::dataRefresh;
       if(getActivity() != null && isAdded()){
           ToolbarListener toolbarListener = (ToolbarListener) getActivity();
           toolbarListener.setToolbar("",FRAGMENT_HOME);
       }
       binding.rlFrom.setOnClickListener(this::onClick);
       binding.edtOrigin.setOnClickListener(this::onClick);
       binding.rlTo.setOnClickListener(this::onClick);
       binding.edtDestination.setOnClickListener(this::onClick);
       binding.tvDate.setOnClickListener(this::onClick);
       binding.tvPassengerValue.setOnClickListener(this::onClick);
       binding.brnGo.setOnClickListener(this::onClick);
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
        }else if (v == binding.tvDate){
            selectDate();
        }else if (v == binding.tvPassengerValue){
            if(getActivity() != null && isAdded()){
                Intent intent = new Intent(getActivity(), FlightBookingActivity.class);
                intent.putExtra(FRAGMENT_NAME, FRAGMENT_PASSENGER_SELECTION);
                Bundle bundle = new Bundle();
                bundle.putInt(ADULTS, adults);
                bundle.putInt(TEENS,  teens);
                bundle.putInt(CHILDREN, children);
                bundle.putInt(INFANT, infants);
                intent.putExtras(bundle);
                startActivityForResult(intent, PASSENGER_CODE);
            }
        }else if(v == binding.brnGo){
            if(getActivity() != null && isAdded()){
                if(NetworkUtils.isConnected(getActivity())){
                    validateStation();
                }else {
                    ApplicationUtils.showSnackBar(getActivity(), getString(R.string.no_internet_msg));
                }
            }
        }
    }

    private void validateStation() {
        if(getActivity() != null && isAdded()){
            if(!TextUtils.isEmpty(flightEnquiry.getOrigin())){
                if(!TextUtils.isEmpty(flightEnquiry.getDestination())){
                   validateDate();
                }else {
                    ApplicationUtils.showSnackBar(getActivity(), getString(R.string.select_destination));
                }
            }else {
               ApplicationUtils.showSnackBar(getActivity(), getString(R.string.select_origin));
            }
        }
    }

    private void validateDate() {
        if(!TextUtils.isEmpty(flightEnquiry.getDateOut())){
            getFlights(listener, flightEnquiry, (AppCompatActivity) getActivity());
        }else {
            ApplicationUtils.showSnackBar(getActivity(), getString(R.string.select_date));
        }
    }

    private void selectOrigin(){
        if(getActivity() != null && isAdded()){
            Intent intent = new Intent(getActivity(), FlightBookingActivity.class);
            intent.putExtra(IS_ORIGIN, true);
            intent.putExtra(FRAGMENT_NAME, FRAGMENT_STATION_SELECTION);
            if(!TextUtils.isEmpty(flightEnquiry.getDestinationCode())){
                Bundle bundle = new Bundle();
                bundle.putString(DESTINATION_CO, flightEnquiry.getDestinationCode());
                intent.putExtras(bundle);
            }
            startActivityForResult(intent, ORIGIN_CODE);
        }
    }

    private void selectDestination(){
        if(getActivity() != null && isAdded()){
            Intent intent = new Intent(getActivity(), FlightBookingActivity.class);
            intent.putExtra(IS_ORIGIN, false);
            intent.putExtra(FRAGMENT_NAME, FRAGMENT_STATION_SELECTION);
            if(!TextUtils.isEmpty(flightEnquiry.getOriginCode())){
                Bundle bundle = new Bundle();
                bundle.putString(ORIGIN_CO, flightEnquiry.getOriginCode());
                intent.putExtras(bundle);
            }
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
                flightEnquiry.setOrigin(station.getName());
                flightEnquiry.setOriginCode(station.getCode());
            }
        }else if (requestCode == DESTINATION_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK
                Station station = data.getParcelableExtra(STATION);
                binding.edtDestination.setText(station.getName());
                binding.tvCodeDs.setText(station.getCode());
                flightEnquiry.setDestination(station.getName());
                flightEnquiry.setDestinationCode(station.getCode());
            }
        }else if (requestCode == PASSENGER_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK
                adults   = data.getIntExtra(ADULTS,0);
                teens    = data.getIntExtra(TEENS,0);
                children = data.getIntExtra(CHILDREN,0);
                infants  = data.getIntExtra(INFANT,0);
                binding.tvPassengerValue.setText(ApplicationUtils.getPassengerText(adults,teens,children,infants));
                flightEnquiry.setAdult(adults);
                flightEnquiry.setTeen(teens);
                flightEnquiry.setChildren(children);
                flightEnquiry.setInfants(infants);
            }
        }
    }

    private void selectDate(){
        if(getActivity() != null && isAdded()){
            int mYear, mMonth, mDay;
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            date = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
                            binding.tvDate.setText(DateUtils.getFormattedDateWithoutT(date, false));
                            flightEnquiry.setDateOut(date);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMinDate( System.currentTimeMillis() - 1);
            datePickerDialog.show();
        }
    }

    @Override
    public void dataRefresh(boolean isRefreshed) {
        appExecutors.getExeMainThread().execute(()->{
            if (isRefreshed){
                // Flight Details Activity
                if(getActivity() != null && isAdded()){
                    ApplicationUtils.startActivityIntent(getActivity(), FlightDetailsActivity.class, null);
                }
            }
        });
    }
}