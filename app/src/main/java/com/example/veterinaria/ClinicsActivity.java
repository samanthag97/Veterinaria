package com.example.veterinaria;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.Manifest;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.L;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.veterinaria.databinding.ActivityClinicsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

<<<<<<< Updated upstream
<<<<<<< HEAD
=======

>>>>>>> Stashed changes
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

=======
>>>>>>> parent of 1c2ee45 (find neaby places not working)
public class ClinicsActivity extends DrawerBaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityClinicsBinding activityClinicsBinding;

    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    Location currentLocation;

    private static final int REQUEST_CODE = 101;
    private static final int ZOOM = 10;
<<<<<<< Updated upstream
=======
    String veterinario = "veterinarian";
>>>>>>> Stashed changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityClinicsBinding = ActivityClinicsBinding.inflate(getLayoutInflater());
        setContentView(activityClinicsBinding.getRoot());
        allocateActivityTitle(getString(R.string.clinics));

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getCurrentLocation();

<<<<<<< Updated upstream
<<<<<<< HEAD
=======
        //still not working but at least it won't crush


>>>>>>> Stashed changes
        //part for nearby places
        String url = "https://maps.googleapis.com/maps/place/nearbysearch/json?"+
                "location=" + currentLat + "," + currentLong +
                "&radius=1000" + "&types=" + veterinario + "&sensor=true"  +
                "&key=" + getResources().getString(R.string.google_maps_key);

        Object dataFetch[] = new Object[2];
        dataFetch[0] = mMap;
        dataFetch[1] = url;

<<<<<<< Updated upstream
        new PlaceTask().execute(url);

=======
>>>>>>> parent of 1c2ee45 (find neaby places not working)
=======
>>>>>>> Stashed changes

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng current = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(current));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current, ZOOM));

    }

    private void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    currentLocation = location;
                    //Toast.makeText(getApplicationContext(), currentLocation.getLatitude()+""+currentLocation.getLongitude(),Toast.LENGTH_LONG).show();
                    SupportMapFragment supportMapFragment=(SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    supportMapFragment.getMapAsync(ClinicsActivity.this);
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    getCurrentLocation();
                }
                break;
        }
    }


<<<<<<< HEAD
    private class PlaceTask extends AsyncTask<String, Integer, String>{


        @Override
        protected String doInBackground(String... strings) {

            String data = null;

            try {
                data = downloadUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {

            new ParserTask().execute(s);
        }
    }

    private String downloadUrl(String string) throws  IOException{

        URL url = new URL(string);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.connect();
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = "";

        while ((line  = bufferedReader.readLine()) != null){
            stringBuilder.append(line);
        }

        String data = stringBuilder.toString();
        bufferedReader.close();

        return data;
    }


    private class ParserTask extends AsyncTask<String,Integer,List<HashMap<String,String>>>{

        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {

            JsonParser jsonParser = new JsonParser();
            List<HashMap<String,String>> mapList = null;
            JSONObject object = null;
            try {
                object = new JSONObject(strings[0]);
                mapList = jsonParser.parseResult(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return mapList;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps) {

            mMap.clear();
            for(int i=0; i<hashMaps.size(); i++){
                HashMap<String,String> hashMapList = hashMaps.get(i);
                double lat = Double.parseDouble(hashMapList.get("lat"));
                double lng = Double.parseDouble(hashMapList.get("lng"));
                String name = hashMapList.get("name");
                LatLng latLng = new LatLng(lat,lng);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(name);
                mMap.addMarker(markerOptions);

            }

        }


    }


<<<<<<< Updated upstream

=======
>>>>>>> parent of 1c2ee45 (find neaby places not working)
=======
>>>>>>> Stashed changes
}