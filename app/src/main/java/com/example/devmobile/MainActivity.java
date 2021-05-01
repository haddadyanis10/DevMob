package com.example.devmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonObject;

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
                /*else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new SunFragment("grenoble")).commit();
                }*/
            }
        });

        this.favoris = (Button) findViewById(R.id.fav);
        favoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (villeInput.getText() != null){
                    listePrefs.add(villeInput.getText().toString());
                    saveData();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(villeInput.getWindowToken(), 0);
                    loadData();
                }
            }
        });

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
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            return true;
        }
    };


    private String JsonDataFromAsset(){
        String json = null;
        try {
            InputStream is = getAssets().open("fav.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


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
}