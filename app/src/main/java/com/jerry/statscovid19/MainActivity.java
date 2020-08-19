package com.jerry.statscovid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.jerry.statscovid19.Pojo.Stats;
import com.skydoves.progressview.ProgressView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    ProgressView newco,totlco,newd,totld,newreco,totlreco;
    ApiClient apiClient;

    RecyclerView recyclerView;
    private static final String  b = "8 billion";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newco = findViewById(R.id.newConfr);
        totlco= findViewById(R.id.totlconfr);
        newd = findViewById(R.id.newd);
        totld = findViewById(R.id.totld);
        newreco = findViewById(R.id.newreco);
        totlreco = findViewById(R.id.totlreco);
        apiClient=new ApiClient();
        recyclerView=findViewById(R.id.recyler);


        Call<Stats> call = apiClient.getApiinterface().statslist();

      call.enqueue(new Callback<Stats>() {
          @Override
          public void onResponse(Call<Stats> call, Response<Stats> response) {
              if (response.isSuccessful()){
                  Log.d("manik",response.toString());

                  CountryAdapter countryAdapter=new CountryAdapter(getApplicationContext(),response.body());
                  recyclerView.setHasFixedSize(true);
                  recyclerView.setAdapter(countryAdapter);
                  recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


                  newco.setMax(response.body().getGlobal().getTotalConfirmed());
                  newco.setProgress(response.body().getGlobal().getNewConfirmed());
                  newco.setLabelText(response.body().getGlobal().getNewConfirmed().toString() + "/" + response.body().getGlobal().getTotalConfirmed().toString());

                  totlco.setMax(Float.parseFloat(b));
                  totlco.setProgress(response.body().getGlobal().getTotalConfirmed());
                  totlco.setLabelText(response.body().getGlobal().getTotalConfirmed().toString() + "/" + b.toString());

                  newd.setMax(response.body().getGlobal().getTotalDeaths());
                  newd.setProgress(response.body().getGlobal().getNewDeaths());
                  newd.setLabelText(response.body().getGlobal().getNewDeaths().toString() + "/" + response.body().getGlobal().getTotalDeaths().toString());

                  totld.setMax(response.body().getGlobal().getTotalConfirmed());
                  totld.setProgress(response.body().getGlobal().getTotalDeaths());
                  totld.setLabelText(response.body().getGlobal().getTotalDeaths().toString() + "/" + response.body().getGlobal().getTotalConfirmed().toString());

                  newreco.setMax(response.body().getGlobal().getTotalRecovered());
                  newreco.setProgress(response.body().getGlobal().getNewRecovered());
                  newreco.setLabelText(response.body().getGlobal().getNewRecovered().toString() + "/" + response.body().getGlobal().getTotalRecovered().toString());

                  totlreco.setMax(response.body().getGlobal().getTotalConfirmed());
                  totlreco.setProgress(response.body().getGlobal().getTotalRecovered());
                  totlreco.setLabelText(response.body().getGlobal().getTotalRecovered().toString() + "/" + response.body().getGlobal().getTotalConfirmed().toString());



              }
              else{
                  Log.d("manik",response.toString());
              }

          }

          @Override
          public void onFailure(Call<Stats> call, Throwable t) {

          }
      });



    }
}