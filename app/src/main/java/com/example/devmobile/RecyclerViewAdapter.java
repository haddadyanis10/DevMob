package com.example.devmobile;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Weather> weatherList;
    Context context;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public RecyclerViewAdapter(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    @Override
    public int getItemCount() {
        return this.weatherList.size();
    }

    public Weather getItem(int position) {
        return weatherList.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new RecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Weather weather = weatherList.get(position);
        String urlImage = "https://openweathermap.org/img/wn/"+weather.getWeather().get(0).getIcon()+"@2x.png";
        holder.itemTemp.setText(Float.toString(weather.getMain().getTemp()));
        Picasso.with(context).load(urlImage).resize(200,200).into(holder.listeicon);
    }

    public void addWeatherList(List<Weather> weatherList) {
        this.weatherList.addAll(weatherList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView itemTemp;
        final ImageView listeicon;

        ViewHolder(View view) {
            super(view);
            itemTemp = view.findViewById(R.id.item_temp);
            listeicon = view.findViewById(R.id.listeicon);
        }
    }

}
