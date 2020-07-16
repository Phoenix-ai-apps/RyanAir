package com.example.ryanair.ui.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.ryanair.AppExecutors;
import com.example.ryanair.R;
import com.example.ryanair.RyanAirApp;
import com.example.ryanair.helper.ApplicationHelper;
import com.example.ryanair.helper.HelperInterface;
import com.example.ryanair.interfaces.DataRefreshListener;
import com.example.ryanair.models.Flight;
import com.example.ryanair.models.FlightEnquiry;
import com.example.ryanair.models.StationResponse;
import com.example.ryanair.network.ApiInterface;
import com.example.ryanair.network.ApiUtils;
import com.example.ryanair.ui.activities.BaseActivity;
import com.example.ryanair.utils.ApplicationUtils;
import com.example.ryanair.utils.DialogUtils;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseFragment extends Fragment implements HelperInterface {

    private static final String TAG = BaseFragment.class.getSimpleName();
    private Dialog              dialog;
    private ApiInterface        apiInterface;
    protected AppExecutors      appExecutors;
   // private Call<GlobalResponse>      genOTP;
   // private Call<ValidateOTPResponse> valOTP;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        injectDependencies();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectDependencies();
    }

    /**
     * Every fragment has to inflate a layout in the onCreateView method. We have added this method to
     * avoid duplicate all the inflate code in every fragment. You only have to return the layout to
     * inflate in this method when extends BaseFragment.
     */
    protected abstract int getFragmentLayout();

    protected abstract void initResources();

    /**
     * Replace every field annotated using @Inject annotation with the provided dependency specified
     * inside a Dagger module value.
     */
    private void injectDependencies() {
       initVar();
    }

    public void replaceFragment(Fragment fragment, String fragmentName, boolean add) {
        if((BaseActivity)getActivity() != null && isAdded()){
          FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
          transaction.replace(R.id.frame_layout_main, fragment, fragmentName);
          if(add){
              transaction.addToBackStack(fragmentName);
          }
          transaction.commitAllowingStateLoss();
        }
    }

    public void addFragment(Fragment fragment, String fragmentName, boolean add) {
        if((BaseActivity)getActivity() != null && isAdded()){
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frame_layout_main, fragment, fragmentName);
            if(add){
                transaction.addToBackStack(fragmentName);
            }
            transaction.commitAllowingStateLoss();
        }
    }

    public void showLoadingDialog(String msg){
        if(appExecutors == null){
            appExecutors = new AppExecutors();
        }
        appExecutors.getExeMainThread().execute(()->{
            if(getActivity() != null && isAdded()){
                if(dialog == null || (dialog != null && !dialog.isShowing())){
                    dialog = DialogUtils.showLoadingDialog(getActivity(), msg);
                }
            }
        });
    }

    public void hideDialog(){
        if(appExecutors == null){
            appExecutors = new AppExecutors();
        }
        appExecutors.getExeMainThread().execute(()->{
            if(dialog != null && dialog.isShowing()){
                dialog.dismiss();
            }
        });
    }

    private void initVar(){
        apiInterface = ApiUtils.getApiService(false);
        appExecutors = new AppExecutors();
    }

    //API'S

    public void getFlights(DataRefreshListener listener, FlightEnquiry flightEnquiry, AppCompatActivity activity){
        showLoadingDialog("");
        apiInterface = ApiUtils.getApiService(true);
        apiInterface.getFlights(flightEnquiry.getDateOut(),
                                false,
                                 flightEnquiry.getOriginCode(),
                                 flightEnquiry.getDestinationCode(),
                                 3,
                                 3,
                                 3,
                                 3,
                                  flightEnquiry.getAdult(),
                                  flightEnquiry.getChildren(),
                                  flightEnquiry.getTeen(),
                                  flightEnquiry.getInfants(),
                                  "AGREED",
                                   0)
                    .enqueue(new Callback<Flight>() {
                    @Override
                    public void onResponse(Call<Flight> call, Response<Flight> response) {
                        hideDialog();
                        if(response != null && response.isSuccessful()
                                && response.body() != null){
                            getHelper().setFlight(response.body());
                            listener.dataRefresh(true);
                        }else {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                ApplicationUtils.showSnackBar(activity, jObjError.getString("message"));
                             } catch (Exception e) {
                                ApplicationUtils.showSnackBar(activity, getString(R.string.something_went_wrong));
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                            listener.dataRefresh(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<Flight> call, Throwable t) {
                        hideDialog();
                        ApplicationUtils.showSnackBar(activity, getString(R.string.something_went_wrong));
                        listener.dataRefresh(false);
                    }
                });
    }

    @Override
    public ApplicationHelper getHelper() {
        return ApplicationHelper.getInstance();
    }
}
