package org.kevoreetp.android.demo;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import org.kevoree.android.framework.helper.UIServiceHandler;
import org.kevoree.android.framework.service.KevoreeAndroidService;
import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;

/**
 * Created with IntelliJ IDEA.
 * User: 11007452
 * Date: 26/02/13
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 */
@Library(name = "Android")
@ComponentType
public class AndroidLightCouchInsert extends AbstractComponentType {

    private KevoreeAndroidService uiService;
    private LocationManager lm;
    private LocationListener ll;

    @Start
    public void start() {
        uiService = UIServiceHandler.getUIService();
        lm = (LocationManager) uiService.getRootActivity().getSystemService(Context.LOCATION_SERVICE);
        ll = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    TextView tv = new TextView(uiService.getRootActivity().getBaseContext());
                    tv.setText("GPS: " + location.getLatitude() + " | " + location.getLongitude());
                    uiService.addToGroup("toast", tv);
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onProviderEnabled(String s) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onProviderDisabled(String s) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
    }

    @Stop
    public void stop() {

    }

    @Update
    public void update() {

    }
}
