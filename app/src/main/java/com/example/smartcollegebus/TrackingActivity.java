
package com.example.smartcollegebus;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TrackingActivity extends AppCompatActivity {

    private TextView txtBusStatus;
    private TextView txtNextStop;
    private TextView txtRoute;
    private Button btnNextStop;

    private final String[] stops = {
            "Shamshabad",
            "Aramghar",
            "Attapur",
            "Mehdipatnam",
            "College"
    };

    private int currentStop = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        txtBusStatus = findViewById(R.id.txtBusStatus);
        txtNextStop = findViewById(R.id.txtNextStop);
        txtRoute = findViewById(R.id.txtRoute);
        btnNextStop = findViewById(R.id.btnNextStop);

        updateTrackingScreen();

        btnNextStop.setOnClickListener(v -> {
            if (currentStop < stops.length - 1) {
                currentStop++;
                updateTrackingScreen();
            } else {
                Toast.makeText(
                        this,
                        "Bus has reached the college!",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void updateTrackingScreen() {

        txtBusStatus.setText(
                "Bus is at " + stops[currentStop]
        );

        if (currentStop < stops.length - 1) {
            txtNextStop.setText(
                    "Next stop: " + stops[currentStop + 1]
            );
            btnNextStop.setText("Simulate Next Stop");
        } else {
            txtNextStop.setText("Destination reached!");
            btnNextStop.setText("Journey Completed");
        }

        StringBuilder route = new StringBuilder();

        for (int i = 0; i < stops.length; i++) {
            if (i < currentStop) {
                route.append("✓ ");
            } else if (i == currentStop) {
                route.append("🚌 ");
            } else {
                route.append("○ ");
            }

            route.append(stops[i]);

            if (i < stops.length - 1) {
                route.append("\n│\n");
            }
        }

        txtRoute.setText(route.toString());
    }
}
