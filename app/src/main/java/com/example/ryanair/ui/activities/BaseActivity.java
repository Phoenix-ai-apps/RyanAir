package com.example.ryanair.ui.activities;

import android.app.Dialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.ryanair.AppExecutors;
import com.example.ryanair.R;
import com.example.ryanair.helper.ApplicationHelper;
import com.example.ryanair.helper.HelperInterface;
import com.example.ryanair.interfaces.DataRefreshListener;
import com.example.ryanair.models.StationResponse;
import com.example.ryanair.network.ApiInterface;
import com.example.ryanair.network.ApiUtils;
import com.example.ryanair.utils.DialogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseActivity extends AppCompatActivity implements HelperInterface {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private Dialog dialog;
    private ApiInterface apiInterface;
    AppExecutors appExecutors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    protected abstract void initResources();

    private void injectDependencies() {
        if(appExecutors == null){
            appExecutors = new AppExecutors();
        }
        if (apiInterface == null){
            apiInterface  = ApiUtils.getApiService(false);
        }
    }

    public void replaceFragment(Fragment fragment, String fragmentName, boolean add) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_main, fragment, fragmentName);
        if(add){
            transaction.addToBackStack(fragmentName);
        }
        transaction.commitAllowingStateLoss();
    }

    public void getAllStation(DataRefreshListener listener){
        apiInterface.getAllStations()
                .enqueue(new Callback<StationResponse>() {
                    @Override
                    public void onResponse(Call<StationResponse> call, Response<StationResponse> response) {
                        if(response != null && response.isSuccessful()
                                && response.body() != null
                                && response.body().getStations() != null
                                && response.body().getStations().size() > 0){
                            getHelper().setStations(response.body().getStations());
                            listener.dataRefresh(true);
                        }else {
                            listener.dataRefresh(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<StationResponse> call, Throwable t) {
                        listener.dataRefresh(false);
                    }
                });
    }

    public void showLoadingDialog(String msg){
        dialog = DialogUtils.showLoadingDialog(this, msg);
    }

    public void hideDialog(){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    @Override
    public ApplicationHelper getHelper() {
        return ApplicationHelper.getInstance();
    }
}
