package com.jerry.statscovid19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jerry.statscovid19.Pojo.Stats;
import com.skydoves.progressview.ProgressView;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryHolder> {

    Context context;
   Stats response;

    public CountryAdapter(Context context, Stats response) {
        this.context = context;
        this.response = response;
    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.country_item, parent, false);
        return new CountryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {

        holder.country.setText(response.getCountries().get(position).getCountry().toString());
        holder.newcoto.setMax(response.getCountries().get(position).getTotalConfirmed());
        holder.newcoto.setProgress(response.getCountries().get(position).getNewConfirmed());
        holder.newcoto.setLabelText(response.getCountries().get(position).getNewConfirmed().toString() + "/" + response.getCountries().get(position).getTotalConfirmed().toString());

        holder.newrecto.setMax(response.getCountries().get(position).getTotalRecovered());
        holder.newrecto.setProgress(response.getCountries().get(position).getNewRecovered());
        holder.newrecto.setLabelText(response.getCountries().get(position).getNewRecovered().toString() + "/" + response.getCountries().get(position).getTotalRecovered().toString());

        holder.newdto.setMax(response.getCountries().get(position).getTotalDeaths());
        holder.newdto.setProgress(response.getCountries().get(position).getNewDeaths());
        holder.newdto.setLabelText(response.getCountries().get(position).getNewDeaths().toString() + "/" + response.getCountries().get(position).getTotalDeaths().toString());


    }

    @Override
    public int getItemCount() {
        return response.getCountries().size();
    }

    class CountryHolder extends RecyclerView.ViewHolder {

        TextView country;
        ProgressView newrecto,newdto,newcoto;

        public CountryHolder(View itemView) {
            super(itemView);

            country = itemView.findViewById(R.id.country);
            newrecto = itemView.findViewById(R.id.newrecoto);
            newdto = itemView.findViewById(R.id.newdto);
            newcoto = itemView.findViewById(R.id.newconto);

        }
    }
}
