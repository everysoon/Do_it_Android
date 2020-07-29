package com.example.study63_locationmap_myicon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    SupportMapFragment mapFragment;
    GoogleMap map;
    MarkerOptions marker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("MainActivity","goolgeMap 객체 레디!");

                map=googleMap;
            }
        });
        MapsInitializer.initialize(this);

        Button button =(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestMyLocation();
                Log.d("MainActivity","onClick()작동");
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(map!=null){
            map.setMyLocationEnabled(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(map!=null){
            map.setMyLocationEnabled(true);
        }
    }

    public void requestMyLocation(){
        long minTime=10000;
        float minDistance=0;
        LocationManager manager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        manager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minTime,
                minDistance,
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        showCurrentLocation(location);
                        Log.d("MainActivity","onLocationChanged()작동");
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                }
        );
    }
    public  void showCurrentLocation(Location location){
        //LatLng =>경도위도줄임말
        LatLng curPoint=new LatLng(location.getLatitude(),location.getLongitude());
        //animateCamera 입체적으로 줌하면서 보여줌  (어느정도 확대할지 15)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint,15));
        Log.d("MainActivity","showCurrentLocation()작동");

        showMarker(location);
    }
    public void showMarker(Location location) {
        //Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.locationicon);
        if (marker == null) {

            marker = new MarkerOptions();
            marker.position(new LatLng(location.getLatitude()+0.01,location.getLongitude()-0.01));
            marker.title("커피숍");
            marker.snippet("커피숍에 대한 설명입니다.");
             marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.locationicon));
            Log.d("MainActivity","showMarker()작동");

           /* BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.locationicon);
            Log.d("MainActivity",bitmapdraw+"!!!!!");
            Bitmap b=bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, 200, 200, false);
            marker.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
*/
            map.addMarker(marker);
        }else {  //마커가 있으면 위치만 변경해주기
            marker.position(new LatLng(location.getLatitude()+0.01,location.getLongitude()-0.01));
        }
    }
}
