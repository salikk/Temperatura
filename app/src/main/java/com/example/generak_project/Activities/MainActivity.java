package com.example.generak_project.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.generak_project.R;

import java.io.IOException;

import packege.Parser;

public class MainActivity extends AppCompatActivity {
//    private Button toCelsius;
//    private Button toFahrenheit;
    private Button updateData;
    private TextView dataTemperature;
    private TextView dataHumidity;
    private TextView dataDewPoint;
    private final Parser parser = new Parser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeById();

        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    parser.updateData();
                    setData();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        /*toCelsius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataTemperature.setText(parser.getTemperature());
                dataDewPoint.setText(parser.getDewPoint());
            }
        });
        toFahrenheit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataTemperature.setText(parser.getTemperature());//изменить
                dataDewPoint.setText(parser.getDewPoint());//изменить
            }
        });*/
    }
    public void setData(){
        dataTemperature.setText(parser.getTemperature());
        dataHumidity.setText(parser.getHumidity());
        dataDewPoint.setText(parser.getDewPoint());
    }

    public void initializeById(){
//        toCelsius = findViewById(R.id.toCelsius);
//        toFahrenheit = findViewById(R.id.toFahrenheit);
        dataTemperature = findViewById(R.id.dataTemperature);
        dataHumidity = findViewById(R.id.dataHumidity);
        dataDewPoint = findViewById(R.id.dataDewPoint);
        updateData = findViewById(R.id.updateData);
    }
}