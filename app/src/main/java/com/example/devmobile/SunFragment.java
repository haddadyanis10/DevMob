package com.example.devmobile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SunFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SunFragment extends Fragment {

    String villeRecherche;
    TextView nomVille;
    TextView description;
    TextView temperature;

    ImageView icon;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SunFragment(String city) {
        // Required empty public constructor
        this.villeRecherche=city;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SunFragment.
     */
    // TODO: Rename and change types and number of parameters
    public SunFragment newInstance(String param1, String param2) {
        SunFragment fragment = new SunFragment(this.villeRecherche);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchWeather(this.villeRecherche);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sun, container, false);
        this.nomVille = (TextView) v.findViewById(R.id.nomVille);
        this.icon = (ImageView) v.findViewById(R.id.icon);
        this.description = (TextView) v.findViewById(R.id.description);
        this.temperature = (TextView) v.findViewById(R.id.temperature);
        return v;
    }

    private void fetchWeather(String ville){
        CurrentWeatherService currentweatherservice = RetrofitClient.getInstance().create(CurrentWeatherService.class);
        currentweatherservice.getByCity(ville,"9387d7732a59e17de90e4c91d32b1936","metric","fr").enqueue(new Callback<ResponseWeather>() {
            @Override
            public void onResponse(Call<ResponseWeather> call, Response<ResponseWeather> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //To get Temp
                    ResponseWeather meteo=response.body();
                    nomVille.setText(meteo.getName());
                    String urlImage = "https://openweathermap.org/img/wn/"+meteo.getWeather().get(0).getIcon()+"@2x.png";
                    Picasso.with(requireContext()).load(urlImage).into(icon);
                    description.setText(meteo.getWeather().get(0).getDescription());
                    temperature.setText(Float.toString(meteo.getMain().getTemp())+" C°");
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Veuillez introduire le nom d'une ville", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseWeather> call, Throwable t) {
                t.getMessage();
            }
        });
    }

}