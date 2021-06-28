package com.example.main;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.libraries.places.api.Places;

public class ListaMaps extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private static final int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    String placeName;
    private Button opc, opc1, opc2, opc3, opc4 = null;
    private GoogleApiClient mGoogleApiClient;
    Location mylocation;
    double lat, lng;

    public static final int DEFAULT_ZOOM = 15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_maps);

        String apiKey = "AIzaSyD_ttY3z0PJqnQnsQt-dUuAc3Cbjhmxlwo";

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }


        opc = (Button) findViewById(R.id.btnOpc);
        opc1 = (Button) findViewById(R.id.btnOpc1);
        opc2 = (Button) findViewById(R.id.btnOpc2);
        opc3 = (Button) findViewById(R.id.btnOpc3);
        opc4 = (Button) findViewById(R.id.btnOpc4);


        Bundle bundle = getIntent().getExtras();


        if(bundle.containsKey("LOCAL"))
        {
            placeName = "Supermercado Poços de Caldas";
            //Toast.makeText(getApplicationContext(), mSearchAddresss, Toast.LENGTH_LONG).show();
            getMyLocation();
            lat = mylocation.getLatitude();
            lng = mylocation.getLongitude();
            getNearByPlaces();
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    private void getMyLocation(){
        if(mGoogleApiClient!=null) {
            if (mGoogleApiClient.isConnected()) {
                int permissionLocation = ContextCompat.checkSelfPermission(ListaMaps.this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                    mylocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                    LocationRequest locationRequest = new LocationRequest();
                    locationRequest.setInterval(3000);
                    locationRequest.setFastestInterval(3000);
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                            .addLocationRequest(locationRequest);
                    builder.setAlwaysShow(true);
                    LocationServices.FusedLocationApi
                            .requestLocationUpdates(mGoogleApiClient, locationRequest, (LocationListener) this);
                    PendingResult<LocationSettingsResult> result =
                            LocationServices.SettingsApi
                                    .checkLocationSettings(mGoogleApiClient, builder.build());
                    result.setResultCallback(new ResultCallback<LocationSettingsResult>() {

                        @Override
                        public void onResult(LocationSettingsResult result) {
                            final Status status = result.getStatus();
                            switch (status.getStatusCode()) {
                                case LocationSettingsStatusCodes.SUCCESS:
                                    // All location settings are satisfied.
                                    // You can initialize location requests here.
                                    int permissionLocation;
                                    permissionLocation = ContextCompat
                                            .checkSelfPermission(ListaMaps.this,
                                                    Manifest.permission.ACCESS_FINE_LOCATION);
                                    if (permissionLocation == PackageManager.PERMISSION_GRANTED) {


                                        mylocation = LocationServices.FusedLocationApi
                                                .getLastLocation(mGoogleApiClient);


                                    }
                                    break;
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                    // Location settings are not satisfied.
                                    // But could be fixed by showing the user a dialog.
                                    try {
                                        // Show the dialog by calling startResolutionForResult(),
                                        // and check the result in onActivityResult().
                                        // Ask to turn on GPS automatically
                                        status.startResolutionForResult(ListaMaps.this,
                                                REQUEST_CHECK_SETTINGS_GPS);


                                    } catch (IntentSender.SendIntentException e) {
                                        // Ignore the error.
                                    }


                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    // Location settings are not satisfied.
                                    // However, we have no way
                                    // to fix the
                                    // settings so we won't show the dialog.
                                    // finish();
                                    break;
                                default:
                                    throw new IllegalStateException("Unexpected value: " + status.getStatusCode());
                            }
                        }
                    });

                }
            }
        }
    }


    private void getNearByPlaces() {
        StringBuilder stringBuilder =
                new StringBuilder("https://maps.googleapi.com/maps/api/place/nearbysearch/json?");
        stringBuilder.append("location="+String.valueOf(lat)+","+String.valueOf(lng));
        stringBuilder.append("&radius=1000");
        stringBuilder.append("&type="+placeName);
        stringBuilder.append("&key=AIzaSyD_ttY3z0PJqnQnsQt-dUuAc3Cbjhmxlwo");

        String url = stringBuilder.toString();

        Object dataTransfer[] = new Object[2];
        dataTransfer[1] = url;

        Toast.makeText(getApplicationContext(),""+lat, Toast.LENGTH_LONG).show();
        opc.setText("oi");

        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
        getNearbyPlacesData.execute(dataTransfer);
    }
}
/*Directions*/