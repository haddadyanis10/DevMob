package com.example.devmobile;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    String villeRecherche;
    List<Weather> listeWeather;
    //TextView meteoDesc;

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListFragment(String city) {
        // Required empty public constructor
        this.villeRecherche = city;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment(this.villeRecherche);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fetchWeather(this.villeRecherche);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        //this.meteoDesc = (TextView) v.findViewById(R.id.meteoDesc);
        //to use the recycler view
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayout.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerAdapter = new RecyclerViewAdapter(new ArrayList());
        recyclerView.setAdapter(recyclerAdapter);
        fetchWeather(this.villeRecherche);
        return v;
    }

    private void fetchWeather(String ville){
        CurrentWeatherService currentweatherservice = RetrofitClient.getInstance().create(CurrentWeatherService.class);
        currentweatherservice.getForFiveD(ville,"9387d7732a59e17de90e4c91d32b1936","metric","fr").enqueue(new Callback<ResponseWeatherFiveD>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<ResponseWeatherFiveD> call, Response<ResponseWeatherFiveD> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //To get Temp
                    ResponseWeatherFiveD meteo=response.body();
                    /*meteoDesc.setText(meteo.getListWeather().toString());
                    for(Weather w:meteo.getListWeatherFiveDays()){
                        meteoDesc.setText(w.getDt_txt());
                    }*/
                    listeWeather = meteo.getListWeatherFiveDays();
                    recyclerAdapter.addWeatherList(listeWeather);

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Veuillez introduire le nom d'une ville", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseWeatherFiveD> call, Throwable t) {
                t.getMessage();
            }
        });
    }
}