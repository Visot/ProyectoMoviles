package com.example.moviles.proyectomoviles.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviles.proyectomoviles.R;
import com.google.android.gms.maps.model.LatLng;

public class Orientacion extends AppCompatActivity implements SensorEventListener {
    LocationManager locationManager;
    Context mContext;
    TextView mLatitudeText;
    TextView mLongitudeText;
    TextView distancia;
    private SensorManager mSensorManager;
    private final float[] RotationMatrix = new float[9];
    private final float[] OrientationAngles = new float[3];
    private final float[] mAccelerometerReading = new float[3];
    private final float[] mMagnetometerReading = new float[3];
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientacion);
        mContext = this;
        mLatitudeText = (TextView) this.findViewById((R.id.Latitud));
        mLongitudeText = (TextView) this.findViewById((R.id.Longitud));
        distancia = (TextView) this.findViewById((R.id.Distancia));
        mImageView = (ImageView) findViewById(R.id.mImageView);

        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListenerGPS);
        isLocationEnabled();

    }

    LocationListener locationListenerGPS = new LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {
            double latitud = location.getLatitude();
            double longitud = location.getLongitude();
            double bearing,compass;
            Location lugar = new Location("");
            lugar.setLatitude(-12.016858);
            lugar.setLongitude(-77.049769);
            String msg = "Latitud " + latitud + "Longitud " + longitud;
            Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
            mLatitudeText.setText("Latitud  " + location.getLatitude());
            mLongitudeText.setText("Longitud  " + location.getLongitude());
            bearing = location.getBearing();
            mSensorManager.getOrientation(RotationMatrix, OrientationAngles);
            compass=OrientationAngles[0];
            Bitmap bmpOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.flecha);
            Bitmap bmResult = Bitmap.createBitmap(bmpOriginal.getWidth(), bmpOriginal.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas tempCanvas = new Canvas(bmResult);
            tempCanvas.rotate((float)(70), bmpOriginal.getWidth()/2, bmpOriginal.getHeight()/2);
            tempCanvas.drawBitmap(bmpOriginal, 0, 0, null);

            mImageView.setImageBitmap(bmResult);
            distancia.setText("Distancia  " + location.distanceTo(lugar) + "\n" + "angle " + (float)(bearing));

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    protected void onResume() {
        super.onResume();
        //mSensorManager.registerListener(this, Sensor.TYPE_ACCELEROMETER, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
        //mSensorManager.registerListener(this, Sensor.TYPE_MAGNETIC_FIELD, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);

        isLocationEnabled();
    }

    private void isLocationEnabled() {

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            String msg = "Location not enable ";
            Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
            /*
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(mContext);
            alertDialog.setTitle("Enable Location");
            alertDialog.setMessage("Your locations setting is not enabled. Please enabled it in settings menu.");
            alertDialog.setPositiveButton("Location Settings", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            AlertDialog alert=alertDialog.create();
            alert.show();*/
        } else {/*
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(mContext);
            alertDialog.setTitle("Confirm Location");
            alertDialog.setMessage("Your Location is enabled, please enjoy");
            alertDialog.setNegativeButton("Back to interface",new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            AlertDialog alert=alertDialog.create();
            alert.show();*/
            String msg = "Location enable ";
            Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, mAccelerometerReading,
                    0, mAccelerometerReading.length);
        }
        else if (event.sensor.getType()== Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, mMagnetometerReading,
                    0, mMagnetometerReading.length);
        }
        updateOrientationAngles();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void updateOrientationAngles() {
        mSensorManager.getRotationMatrix(RotationMatrix, null,
                mAccelerometerReading, mMagnetometerReading);

        mSensorManager.getOrientation(RotationMatrix, OrientationAngles);

    }
}