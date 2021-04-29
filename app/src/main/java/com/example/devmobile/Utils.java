package com.example.devmobile;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)

public class Utils {

    public static ArrayList<String> datesFiveDays(){
        ArrayList<String> liste = new ArrayList<String>();
        LocalDateTime today = LocalDateTime.now();

        String plusDay = today.plusDays(1).toString();
        String [] split = plusDay.split("T");
        String datePlus1 = split[0];
        liste.add(datePlus1);

        String plus2Day = today.plusDays(2).toString();
        split = plus2Day.split("T");
        String datePlus2 = split[0];
        liste.add(datePlus2);

        String plus3Day = today.plusDays(3).toString();
        split = plus3Day.split("T");
        String datePlus3 = split[0];
        liste.add(datePlus3);

        String plus4Day = today.plusDays(4).toString();
        split = plus4Day.split("T");
        String datePlus4 = split[0];
        liste.add(datePlus4);

        String plus5Day = today.plusDays(5).toString();
        split = plus5Day.split("T");
        String datePlus5 = split[0];
        liste.add(datePlus5);

        return liste;
    }
}
