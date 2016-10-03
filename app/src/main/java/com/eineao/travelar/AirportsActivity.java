package com.eineao.travelar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AirportsActivity extends AppCompatActivity {

    private String[] mAirportsNames, mAirportsLocations;
    private ArrayAdapter<String> mAirportsAdapter;
    private ListView mAirportsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airports);

        mAirportsNames = new String[]{
                "Dubai International Airport", "Hamad International Airport",
                "King Abdulaziz International Airport", "Abu Dhabi International Airport",
                "King Khalid International Airport", "Kuwait International Airport",
                "Bahrain International Airport", "Muscat International Airport",
                "Sharjah International Airport", "King Fahd International Airport"
        };
        mAirportsLocations = new String[]{
                "United Arab Emirates, Dubai", "Qatar, Doha", "Saudi Arabia, Jeddah",
                "United Arab Emirates, Abu Dhabi", "Saudi Arabia, Riyadh",
                "Kuwait, Kuwait City", "Bahrain, Muharraq", "Oman, Muscat",
                "United Arab Emirates, Sharjah", "Saudi Arabia, Dammam"
        };

        mAirportsAdapter = new ArrayAdapter<String>(this, R.layout.item_view, R.id.title, mAirportsNames) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(R.id.title);
                TextView text2 = (TextView) view.findViewById(R.id.subtitle);
                text1.setText(mAirportsNames[position]);
                text2.setText(mAirportsLocations[position]);
                return view;
            }
        };

        mAirportsList = (ListView) findViewById(R.id.airports);
        mAirportsList.setAdapter(mAirportsAdapter);
        mAirportsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent next = new Intent(AirportsActivity.this, NavigateActivity.class);
                next.putExtra("AirportName", mAirportsNames[position]);
                startActivity(next);
            }
        });
    }
}
