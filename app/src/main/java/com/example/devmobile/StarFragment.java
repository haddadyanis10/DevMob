package com.example.devmobile;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StarFragment extends Fragment {
    
    ArrayList<String> mesfavoris = new ArrayList<String>();
    ListView listview;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StarFragment(String ville) {
        // Required empty public constructor
        if(ville != null){
            mesfavoris.add(ville);
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public StarFragment newInstance(String param1, String param2) {
        StarFragment fragment = new StarFragment(null);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //mesfavoris = ((MainActivity) getActivity()).read_json();
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_star, container, false);
        this.listview = (ListView) v.findViewById(R.id.listView);
        mesfavoris = ((MainActivity) getActivity()).readFavoris();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.item_layout_favoris,R.id.item_ville, mesfavoris);
        this.listview.setAdapter(adapter);
        return v;
    }
}