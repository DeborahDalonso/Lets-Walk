package com.example.main;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.main.databinding.MapsBinding;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

    public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
            GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

        private GoogleMap mMap;
        private MapsBinding binding;
        private GoogleApiClient mGoogleApiClient;
        private Polyline polyline;
        private Marker marker;
        private List<LatLng> list;
        private Address destino;
        String mSearchAddresss;
        private LatLng l;

        public static final int DEFAULT_ZOOM = 15;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            binding = MapsBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            Bundle bundle = getIntent().getExtras();

            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            if(bundle.containsKey("ENDERECO"))
            {
                mSearchAddresss = bundle.getString("ENDERECO");
                //Toast.makeText(getApplicationContext(), mSearchAddresss, Toast.LENGTH_LONG).show();

            }

        }

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            if (mGoogleApiClient == null) {
                mGoogleApiClient = new GoogleApiClient.Builder(this)
                        .addConnectionCallbacks(this)
                        .addOnConnectionFailedListener(this)
                        .addApi(LocationServices.API)
                        .build();
                mGoogleApiClient.connect();
                geoLocate();


            }
            else {
                LatLng sydney = new LatLng(-34, 151);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
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
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            LatLng eu = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().position(eu).title("Estou aqui"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(eu, 16));
        }

        @Override
        public void onConnectionSuspended(int i) {

        }

        @Override
        public void onConnectionFailed(ConnectionResult connectionResult) {

        }

        private void geoLocate(){

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> addressList = geocoder.getFromLocationName(mSearchAddresss,1);

                if(addressList.size()>0){
                    Address address = addressList.get(0);
                    gotoLocation(address.getLatitude(),address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(new LatLng(address.getLatitude(),address.getLongitude())));

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private void gotoLocation(double lat,double lng){

            LatLng latLng = new LatLng(lat,lng);
            l = latLng;
            list = new ArrayList<LatLng>();

            CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM);

            mMap.moveCamera(cameraUpdate);
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mMap.addMarker(new MarkerOptions().position(latLng).title("Destino"));

            list.add(latLng);
        }

    }    /*Directions*/