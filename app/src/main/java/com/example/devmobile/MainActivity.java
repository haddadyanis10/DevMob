package com.example.devmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private EditText villeInput;
    //valider button
    private Button submit;
    public String villeState = "Grenoble";
    //favoris button
    private Button favoris;

    //SharedPref
    public static String SHARED_PREFS = "sharedPrefs";
    public static final String FAVORIS = "favoris";
    ArrayList<String> listePrefs= new ArrayList<String>();

    //geolocalisation
    FusedLocationProviderClient fusedLocationProviderClient;
    private String villeGeoLocalisation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new SunFragment(villeState)).commit();


        //to load favoris
        loadData();

        this.villeInput = (EditText) findViewById(R.id.inputCity);
        this.submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (villeInput.getText() != null){
                    villeState = villeInput.getText().toString();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new SunFragment(villeInput.getText().toString())).commit();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(villeInput.getWindowToken(), 0);
                }
            }
        });

        this.favoris = (Button) findViewById(R.id.fav);
        favoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!villeInput.getText().toString().equals("")){
                    listePrefs.add(villeInput.getText().toString());
                    saveData();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(villeInput.getWindowToken(), 0);
                    loadData();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Veuillez introduire le nom d'une ville", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Initialize fusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        //check permission
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            //when permission granted
            getLocation();
        }
        else{
            //when permission denied
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment = null;
            switch (menuItem.getItemId()){
                case R.id.sun:
                    fragment = new SunFragment(villeState);
                    break;
                case R.id.list:
                    fragment = new ListFragment(villeState);
                    break;
                case R.id.star:
                    fragment = new StarFragment(null);
                    break;
                case R.id.geolocalisation:
                    fragment = new SunFragment(villeGeoLocalisation);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            return true;
        }
    };


    //Shared pref
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> setPref = new HashSet<String>(this.listePrefs);
        editor.putStringSet(FAVORIS,setPref);
        editor.commit();
    }

    public void loadData(){
        ArrayList<String> listeloc ;
        Set<String> listSet;
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        listSet = sharedPreferences.getStringSet(FAVORIS, Collections.emptySet());
        listeloc = new ArrayList<>(listSet);
        this.listePrefs = listeloc;
    }

    //method to get localisation
    @SuppressLint("MissingPermission")
    private void getLocation (){
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //initialize location
                Location location = task.getResult();
                if(location != null){
                    try {
                        //Initialize geoCoder
                        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                        //Initialize adress List
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                        getCityName(String.valueOf(addresses.get(0).getLatitude()),String.valueOf(addresses.get(0).getLongitude()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    //to get cityName
    public void getCityName (String lat,String lon){
        CurrentWeatherService currentweatherservice = RetrofitClient.getInstance().create(CurrentWeatherService.class);
        currentweatherservice.getByCityCoord(lat,lon,"9387d7732a59e17de90e4c91d32b1936","metric","fr").enqueue(new Callback<ResponseWeather>() {
            @Override
            public void onResponse(Call<ResponseWeather> call, Response<ResponseWeather> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //To get Temp
                    ResponseWeather meteo=response.body();
                    villeGeoLocalisation = meteo.getName();
                } else {
                    Toast.makeText(getApplicationContext(), "Erreur récuperation coordonnées GPS", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseWeather> call, Throwable t) {
                t.getMessage();
            }
        });
    }
}