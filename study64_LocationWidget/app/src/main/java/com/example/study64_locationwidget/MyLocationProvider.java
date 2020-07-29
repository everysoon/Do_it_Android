package com.example.study64_locationwidget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.RemoteViews;

public class MyLocationProvider extends AppWidgetProvider {

    static double xcoord;
    static double ycoord;
    static  LocationListener listener=new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            ycoord=location.getLatitude();
            xcoord=location.getLongitude();

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
    };

    @Override //주기적으로 업데이트 계속 호출됨
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for(int i=0; i<appWidgetIds.length; i++){
            int appWidgetId=appWidgetIds[i];

            String url="geo:"+ycoord+","+xcoord;
            String query=ycoord+","+xcoord+"(내위치)";
            String encodeQuery= Uri.encode(query);
            String urlStr=url+"?q="+encodeQuery+"&z=15";
            Uri uri=Uri.parse(urlStr);

            Intent intent=new Intent(Intent.ACTION_VIEW,uri);

            PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);
            RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.mylocation);
            views.setOnClickPendingIntent(R.id.txtinfo,pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId,views);
        }
        context.startService(new Intent(context,GPSLocationService.class));

    }
    public static class GPSLocationService extends Service{
        LocationManager manager;

        @Override
        public void onCreate() {
            super.onCreate();
            manager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startListening();

            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public void onDestroy() {
            stopListening();

            super.onDestroy();
        }

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        public void startListening(){

            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    10000,
                    0,
                    listener
            );
        }
        public void stopListening(){
            try {
                if (manager != null && listener != null) {
                    manager.removeUpdates(listener);
                }

                manager = null;
            } catch (final Exception ex) {

            }
        }

    }
       
}

