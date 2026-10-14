
package com.example.smartcollegebus;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MyBookingsActivity extends AppCompatActivity {

    private TextView txtMyBookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        txtMyBookings = findViewById(R.id.txtMyBookings);
        showBookings();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (txtMyBookings != null) {
            showBookings();
        }
    }

    private void showBookings() {
        SharedPreferences prefs = getSharedPreferences(
                "BusSeatData", MODE_PRIVATE
        );

        StringBuilder result = new StringBuilder();
        String[] buses = {"Bus101", "Bus102", "Bus103"};

        for (String bus : buses) {
            Set<String> saved = prefs.getStringSet(
                    "bookedSeats_" + bus,
                    Collections.emptySet()
            );

            List<Integer> seats = new ArrayList<>();

            for (String seat : saved) {
                try {
                    seats.add(Integer.parseInt(seat));
                } catch (NumberFormatException ignored) {
                    // Ignore invalid stored seat values.
                }
            }

            Collections.sort(seats);

            result.append(bus.replace("Bus", "BUS "))
                    .append("\n");

            if (seats.isEmpty()) {
                result.append("No seats booked yet.");
            } else {
                result.append("Booked seats: ");

                for (int i = 0; i < seats.size(); i++) {
                    if (i > 0) {
                        result.append(", ");
                    }

                    result.append("S").append(seats.get(i));
                }
            }

            result.append("\n\n");
        }

        txtMyBookings.setText(result.toString());
    }
}
