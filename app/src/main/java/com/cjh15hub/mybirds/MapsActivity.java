package com.cjh15hub.mybirds;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.security.Permissions;
import java.security.acl.Permission;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {

    private GoogleMap mMap;
    boolean mLocationPermissionGranted;

    private static final LatLng[] ATHS_Points = new LatLng[]{
            new LatLng(41.234123,-77.026297),
            new LatLng(41.234135,-77.025983),
            new LatLng(41.234208,-77.025989),
            new LatLng(41.234252,-77.025004),
            new LatLng(41.233829,-77.024975),
            new LatLng(41.233792,-77.026016),
            new LatLng(41.233847,-77.026010),
            new LatLng(41.233841,-77.026294),
            new LatLng(41.233772,-77.026292),
            new LatLng(41.233742,-77.026997),
            new LatLng(41.234169,-77.027032),
            new LatLng(41.234208,-77.026308),
            new LatLng(41.234119,-77.026300)
    };

    private  static final LatLng[] College_points = new LatLng[]{
            new LatLng(41.237399,-77.029288),
            new LatLng(41.237608,-77.023022),
            new LatLng(41.233768,-77.022657),
            new LatLng(41.233235,-77.024353),
            new LatLng(41.233478,-77.027099),
            new LatLng(41.234301,-77.030575),
            new LatLng(41.237189,-77.030854)
    };

    private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private static final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);

    private Marker mPerth;
    private Marker mSydney;
    private Marker mBrisbane;
    private PolygonOptions ATHS_Poly;
    private PolygonOptions College_Poly;

    private GoogleApiClient mGoogleApiClient;

    PolylineOptions arbb = new PolylineOptions().add(
            PERTH,SYDNEY,BRISBANE
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        ATHS_Poly = new PolygonOptions();
        College_Poly = new PolygonOptions();

        for(LatLng l: ATHS_Points)ATHS_Poly.add(l);
        for(LatLng l: College_points)College_Poly.add(l);

        ATHS_Poly.strokeColor(Color.BLUE);
        ATHS_Poly.fillColor(Color.MAGENTA);

        College_Poly.strokeColor(Color.BLUE);
        College_Poly.fillColor(Color.argb(50,255,20,255));
    }

    private void getDeviceLocation() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            //ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
        // A step later in the tutorial adds the code to get the device location.
        //PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
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
//        mPerth = mMap.addMarker(new MarkerOptions()
//                .position(PERTH)
//                .title("Perth"));
//        mPerth.setTag(0);
//
//        mSydney = mMap.addMarker(new MarkerOptions()
//                .position(SYDNEY)
//                .title("Sydney"));
//        mSydney.setTag(0);
//
//        mBrisbane = mMap.addMarker(new MarkerOptions()
//                .position(BRISBANE)
//                .title("Brisbane"));
//        mBrisbane.setTag(0);
//
//        mMap.addPolyline(arbb);

        mMap.addPolygon(ATHS_Poly);
        mMap.addPolygon(College_Poly);

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY));
        //mMap.addMarker(new MarkerOptions().position(ATHS_Points[0]));
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng marker : College_points) {
            builder.include(marker);
        }
        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 20);
        mMap.moveCamera(cu);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}
