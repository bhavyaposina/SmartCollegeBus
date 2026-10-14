
package com.example.smartcollegebus;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class SeatActivity extends AppCompatActivity {

    private GridLayout seatGrid;
    private TextView txtSeatCount;
    private TextView txtBusTitle;

    private final int totalSeats = 40;
    private final Set<Integer> bookedSeats = new HashSet<>();

    private String busId;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        seatGrid = findViewById(R.id.seatGrid);
        txtSeatCount = findViewById(R.id.txtSeatCount);

        txtBusTitle = findViewById(R.id.txtBusTitle);

        busId = getIntent().getStringExtra("BUS_ID");

        if (busId == null) {
            busId = "Bus101";
        }

        prefs = getSharedPreferences(
                "BusSeatData", MODE_PRIVATE
        );

        txtBusTitle.setText(
                busId.replace("Bus", "BUS ")
        );

        loadBookings();
        displaySeats();
    }


    private void loadBookings() {
        bookedSeats.clear();

        String storageKey = "bookedSeats_" + busId;

        Set<String> saved = prefs.getStringSet(
                storageKey, null
        );

        if (saved != null) {
            bookedSeats.addAll(saved.stream()
                    .map(Integer::parseInt)
                    .collect(java.util.stream.Collectors.toSet()));
        }
    }


    private void saveBookings() {
        Set<String> saved = new HashSet<>();

        for (int seat : bookedSeats) {
            saved.add(String.valueOf(seat));
        }

        prefs.edit()
                .putStringSet(
                        "bookedSeats_" + busId, saved
                )
                .apply();
    }

    private void displaySeats() {
        seatGrid.removeAllViews();

        txtSeatCount.setText(
                "Available seats: "
                        + (totalSeats - bookedSeats.size())
                        + " / " + totalSeats
        );

        for (int i = 1; i <= totalSeats; i++) {
            final int seatNumber = i;

            Button seatButton = new Button(this);
            seatButton.setText("S" + seatNumber);
            seatButton.setTextSize(11);
            seatButton.setPadding(0, 4, 0, 4);

            GridLayout.LayoutParams params =
                    new GridLayout.LayoutParams();

            params.width = 0;
            params.height = 52;
            params.columnSpec =
                    GridLayout.spec((i - 1) % 4, 1f);
            params.rowSpec =
                    GridLayout.spec((i - 1) / 4);
            params.setMargins(3, 3, 3, 3);

            seatButton.setLayoutParams(params);

            if (bookedSeats.contains(seatNumber)) {
                seatButton.setBackgroundTintList(
                        android.content.res.ColorStateList
                                .valueOf(Color.RED)
                );
                seatButton.setTextColor(Color.WHITE);
                seatButton.setEnabled(false);
            } else {
                seatButton.setBackgroundTintList(
                        android.content.res.ColorStateList
                                .valueOf(Color.rgb(46, 160, 90))
                );
                seatButton.setTextColor(Color.WHITE);

                seatButton.setOnClickListener(v -> {
                    bookedSeats.add(seatNumber);
                    saveBookings();
                    displaySeats();

                    Toast.makeText(
                            this,
                            busId.replace("Bus", "BUS ")
                                    + " - Seat " + seatNumber
                                    + " booked!",
                            Toast.LENGTH_SHORT
                    ).show();
                });
            }

            seatGrid.addView(seatButton);
        }
    }
}
