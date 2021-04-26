package com.arr.bookpoi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {

    TextView mUserDetails,mCapital,mTotal;
    EditText mNoOfTickets;
    ImageView mFlag;
    ListView mListView;
    Button mCalculate;
    Spinner mSpinner;

    String loggedUser;
    ArrayList<Poi> mPois = new ArrayList<>();
    ArrayList<Poi> mSelectedPois = new ArrayList<>();
    ArrayList<String > countries = new ArrayList<>();
    ArrayList<String > capitals = new ArrayList<>();
    Poi mSelectedPoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mUserDetails = findViewById(R.id.txtUserDetails);
        mCapital = findViewById(R.id.txtCapital);
        mTotal = findViewById(R.id.txtTotal);
        mNoOfTickets = findViewById(R.id.etxtTickets);
        mFlag = findViewById(R.id.imgFlag);
        mListView = findViewById(R.id.listView);
        mCalculate = findViewById(R.id.button);
        mSpinner = findViewById(R.id.spnCountry);
        mTotal.setText("");

        loggedUser = MainActivity.userLogged;
        fillData();

        mUserDetails.setText(new StringBuilder().append("Welcome ").append(loggedUser).toString());

        ArrayAdapter spinnerAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,countries);
        mSpinner.setAdapter(spinnerAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String countrySelected = countries.get(i);
                String capital = capitals.get(i);
                mCapital.setText(capital);
                String imgName = countrySelected.toLowerCase();
                int res = getResources().getIdentifier(imgName,"drawable",getPackageName());
                mFlag.setImageResource(res);
                mSelectedPois.clear();
                for(Poi poi:mPois){
                    if(poi.getCountry().equalsIgnoreCase(countrySelected))
                        mSelectedPois.add(poi);
                }
                CustomAdapter listAdapter = new CustomAdapter(getApplicationContext(),mSelectedPois);
                mListView.setAdapter(listAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSelectedPoi = mSelectedPois.get(i);
            }
        });

        mCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSelectedPoi != null){
                    if(!mNoOfTickets.getText().toString().trim().isEmpty()){
                        String tics = mNoOfTickets.getText().toString().trim();
                        try {
                            int noOfTics = Integer.parseInt(tics);
                            if(noOfTics > 0){
                                if(noOfTics <= 15){
                                    double total = noOfTics * mSelectedPoi.getPrice();
                                    mTotal.setText(String.format("%.2f",total));
                                }else{
                                    double total = noOfTics * mSelectedPoi.getPrice();
                                    total -= total*0.05;
                                    mTotal.setText(String.format("%.2f",total));
                                }
                            }else{
                                Toast.makeText(getBaseContext(),"Tickets Must be greater than 0",Toast.LENGTH_SHORT).show();
                                mTotal.setText("");
                            }

                        } catch (Exception e) {

                            Toast.makeText(getBaseContext(),"Please Enter Number Only",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        mTotal.setText("");
                        Toast.makeText(getBaseContext(),"Enter No Tickets",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    mTotal.setText("");
                    Toast.makeText(getBaseContext(),"Please Select Place of Interest",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public void fillData(){
        mPois.add(new Poi("Niagara falls",100,"Canada"));
        mPois.add(new Poi("CN Tower",30,"Canada"));
        mPois.add(new Poi("The Butchart Gardens",30,"Canada"));
        mPois.add(new Poi("Notre Dame Basilica",50,"Canada"));
        mPois.add(new Poi("The Statue of Liberty",90,"USA"));
        mPois.add(new Poi("The White House",60,"USA"));
        mPois.add(new Poi("Times Square",75,"USA"));
        mPois.add(new Poi("Big Ben",30,"England"));
        mPois.add(new Poi("Westminster Abbey",25,"England"));
        mPois.add(new Poi("Hyde Park",15,"England"));
        countries.add("Canada");
        countries.add("USA");
        countries.add("England");
        capitals.add("Ottawa");
        capitals.add("Washington");
        capitals.add("London");
    }
}