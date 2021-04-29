package com.example.devmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private EditText villeInput;
    private Button submit;
    public String villeState = "Grenoble";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView=findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new SunFragment(villeState)).commit();


        this.villeInput = (EditText) findViewById(R.id.inputCity);
        this.submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (villeInput.getText() != null){
                    villeState = villeInput.getText().toString();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new SunFragment(villeInput.getText().toString())).commit();

                }
                /*else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new SunFragment("grenoble")).commit();
                }*/
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
                    fragment = new StarFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            return true;
        }
    };


}