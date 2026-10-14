
package com.example.smartcollegebus;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;



import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnViewBuses, btnSeats, btnTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnMyBookings = findViewById(R.id.btnMyBookings);

        btnMyBookings.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    MyBookingsActivity.class
            );
            startActivity(intent);
        });

        btnViewBuses = findViewById(R.id.btnViewBuses);
        btnSeats = findViewById(R.id.btnSeats);
        btnTrack = findViewById(R.id.btnTrack);

        btnViewBuses.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    BusListActivity.class
            );
            startActivity(intent);
        });

        btnSeats.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    SeatActivity.class
            );
            startActivity(intent);
        });


        btnTrack.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    TrackingActivity.class
            );
            startActivity(intent);
        });

    }
}
