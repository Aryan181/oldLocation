package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.text.Html;
import android.util.FloatProperty;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity<sensorManager> extends AppCompatActivity implements SensorEventListener {











    //Starting variable
    Button btLocation;
    TextView textView1,textView2,textView3,textView4,textView5;
    FusedLocationProviderClient fusedLocationProviderClient;
    DatabaseReference reff;
    Member member;
    SensorManager sensorManager;
    Boolean running = false;
    String latitude;
    String longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //setting values with variables
        btLocation = findViewById(R.id.bt_location);
        textView1=findViewById(R.id.text_view1);
        textView2=findViewById(R.id.text_view2);
        textView3=findViewById(R.id.text_view3);
        textView4=findViewById(R.id.text_view4);
        textView5=findViewById(R.id.text_view5);


        // Start gaining fusedLocationProvider


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //sensor stuff

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);






        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if allowed to get location
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
                {
                    //after the permission is granted





                }else
                {
                    //if permisssion not given
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                }
            }
        });














    }

    private <datainsert> void getLocation() {

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                //Initialize location
                Location location = task.getResult();
                if(location != null)
                {

                    try {
                        // Initialize geoCoder
                        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                        // Initialize address list

                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                        //Set Latitude on TextView

                            textView1.setText(Html.fromHtml("<font color='#6200EE'><b>Latitude : </b><br></font>"
                                    + addresses.get(0).getLatitude()));
                            latitude = "" + addresses.get(0).getLatitude();
                            //Set Longitude on testView
                            textView2.setText(Html.fromHtml("<font color='#6200EE'><b>Longitude : </b><br></font>"
                                    + addresses.get(0).getLongitude()));
                            longitude = "" + addresses.get(0).getLongitude();
                            textView3.setText(Html.fromHtml("<font color='#6200EE'><b>Country Name : </b><br></font>"
                                    + addresses.get(0).getCountryName()));

                            textView4.setText(Html.fromHtml("<font color='#6200EE'><b>Locality Name : </b><br></font>"
                                    + addresses.get(0).getLocality()));

                            textView5.setText(Html.fromHtml("<font color='#6200EE'><b>Address Name : </b><br></font>"
                                    + addresses.get(0).getAddressLine(0)));







                    } catch (IOException e) {
                        e.printStackTrace();
                    }





                }





            }







        });


               /* reff= FirebaseDatabase.getInstance().getReference().child("Member");
                reff.push().setValue(textView1.getText().toString());
                reff.push().setValue( textView2.getText().toString());
                reff.push().setValue(textView4.getText().toString());*/



    }



































    @Override
    protected void onResume()
    {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor!=null)
        {
            sensorManager.registerListener(this, countSensor,SensorManager.SENSOR_DELAY_UI);
        }
        else
        {
            Toast.makeText((this),"not found",Toast.LENGTH_SHORT).show();
        }
    }


    protected void onPause()
    {
        super.onPause();
        running = false;

    }








    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running)
        {

            getLocation();
            getLocation();
            reff= FirebaseDatabase.getInstance().getReference().child("Member");
            reff.push().setValue(latitude);
            reff.push().setValue(longitude);

            reff.push().setValue("*********next value**************");

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
