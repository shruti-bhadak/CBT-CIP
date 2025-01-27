 package com.example.unit_converter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText inputValue;
    private Spinner unitSpinner;
    private TextView resultText;
    private Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.input_value);
        unitSpinner = findViewById(R.id.unit_spinner);
        resultText = findViewById(R.id.result_text);
        convertButton = findViewById(R.id.convert_button);

        // Populate the spinner with unit options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.unit_options, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);

        // Handle button click to perform conversion
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputValue.getText().toString();
                String selectedUnit = unitSpinner.getSelectedItem().toString();

                if (!input.isEmpty()) {
                    double value = Double.parseDouble(input);
                    double result = convertUnits(selectedUnit, value);
                    resultText.setText(String.format("Result: %.2f", result));
                } else {
                    resultText.setText("Please enter a value.");
                }
            }
        });
    }

    private double convertUnits(String unit, double value) {
        switch (unit) {
            case "Centimeters to Meters":
                return value / 100;
            case "Meters to Centimeters":
                return value * 100;
            case "Grams to Kilograms":
                return value / 1000;
            case "Kilograms to Grams":
                return value * 1000;
            default:
                return 0;
        }
    }
}