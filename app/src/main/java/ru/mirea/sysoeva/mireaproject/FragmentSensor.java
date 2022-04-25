package ru.mirea.sysoeva.mireaproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentSensor extends Fragment implements SensorEventListener{

    private TextView azimuthTextView;
    private TextView pitchTextView;
    private TextView rollTextView;
    private TextView textPressureView;
    private TextView textTemperatureView;
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private Sensor defPressureSensor;
    private Sensor temperatureSensor;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sensor, container, false);

        sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);

        //акселерометр
        azimuthTextView = view.findViewById(R.id.azimuthTextView);
        pitchTextView = view.findViewById(R.id.pitchTextView);
        rollTextView = view.findViewById(R.id.rollTextView);

        // барометр
        textPressureView = textTemperatureView.findViewById(R.id.textPressureView);
        //температура
        textTemperatureView = textTemperatureView.findViewById(R.id.textTemperatureView);

        if (getActivity() != null){
            sensorManager = (SensorManager) getActivity()
                    .getSystemService(Context.SENSOR_SERVICE);

            temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            defPressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        }
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, defPressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float valueAzimuth = event.values[0];
            float valuePitch = event.values[1];
            float valueRoll = event.values[2];
            azimuthTextView.setText("Azimuth: " + valueAzimuth);
            pitchTextView.setText("Pitch: " + valuePitch);
            rollTextView.setText("Roll: " + valueRoll);
        }
        if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            float valuePressure = event.values[0];
            textPressureView.setText("Pressure: " + valuePressure);
        }
        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            float valueTemperature = event.values[0];
            textTemperatureView.setText("Temperature: " + valueTemperature);
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}