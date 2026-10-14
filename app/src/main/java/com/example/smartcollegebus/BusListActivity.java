
package com.example.smartcollegebus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class BusListActivity extends AppCompatActivity {

    private TextView bus101Seats;
    private TextView bus102Seats;
    private TextView bus103Seats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);

        bus101Seats = findViewById(R.id.bus101Seats);
        bus102Seats = findViewById(R.id.bus102Seats);
        bus103Seats = findViewById(R.id.bus103Seats);

        Button btn101 = findViewById(R.id.btnBus101Seats);
        Button btn102 = findViewById(R.id.btnBus102Seats);
        Button btn103 = findViewById(R.id.btnBus103Seats);

        btn101.setOnClickListener(v -> openSeats("Bus101"));
        btn102.setOnClickListener(v -> openSeats("Bus102"));
        btn103.setOnClickListener(v -> openSeats("Bus103"));

        updateSeatCounts();
    }

    private void openSeats(String busId) {
        Intent intent = new Intent(
                BusListActivity.this,
                SeatActivity.class
        );

        intent.putExtra("BUS_ID", busId);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (bus101Seats != null) {
            updateSeatCounts();
        }
    }

    private int getAvailableSeats(String busId) {
        SharedPreferences prefs = getSharedPreferences(
                "BusSeatData", MODE_PRIVATE
        );

        Set<String> booked = prefs.getStringSet(
                "bookedSeats_" + busId,
                new HashSet<>()
        );

        return 40 - booked.size();
    }

    private void updateSeatCounts() {
        bus101Seats.setText(
                "Available seats: "
                        + getAvailableSeats("Bus101") + " / 40"
        );

        bus102Seats.setText(
                "Available seats: "
                        + getAvailableSeats("Bus102") + " / 40"
        );

        bus103Seats.setText(
                "Available seats: "
                        + getAvailableSeats("Bus103") + " / 40"
        );
    }
}
